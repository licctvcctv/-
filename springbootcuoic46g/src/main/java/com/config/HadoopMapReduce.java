package com.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HadoopMapReduce类提供了两个MapReduce作业的Mapper和Reducer实现：
 * 一个是valueMapper和valueReducer，用于处理特定列名的数据；
 * 另一个是groupMapper和groupReducer，用于进行简单的分组计数。
 */
@Component
public class HadoopMapReduce {

    /**
     * valueMapper类继承了Mapper，用于映射输入数据到(key, value)对。
     * 它特别关注于xColumnName和yColumnNames指定的列。
     */
    public static class valueMapper extends Mapper<Object, Text, Text, Text> {

        // 初始化key和value对象以供后续使用
        private final Text key = new Text();
        private final Text value = new Text();
        private String xColumnName;
        private String[] yColumnNames;

        /**
         * 在映射任务开始前执行，确保xColumnName和yColumnNames已被初始化。
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            xColumnName = conf.get("xColumnName");
            yColumnNames = conf.getStrings("yColumnNames");
            if (xColumnName == null || yColumnNames == null || yColumnNames.length == 0) {
                throw new IllegalArgumentException("xColumnName and yColumnNames must be initialized before use.");
            }
        }

        /**
         * map函数处理输入的每一行数据，提取出关键列的值并构建新的(key, value)对。
         */
        @Override
        protected void map(Object object, Text text, Context context) throws IOException, InterruptedException {
            // 处理每一行数据，转换等号为冒号以统一格式
            String line = new String(text.getBytes(), 0, text.getLength(), StandardCharsets.UTF_8).trim().replaceAll("=", ":");
            StringBuilder valueBuilder = new StringBuilder();

            // 分割每一行数据为多个字段，并根据字段名填充valueBuilder
            for (String t : line.split(",")) {
                String[] t1 = t.split(":");
                if (t1.length != 2) continue; // 忽略格式不正确的字段

                // 根据列名填充key或valueBuilder
                if (t1[0].equals(xColumnName)) {
                    key.set(t);
                } else {
                    for (String yColumnName : yColumnNames) {
                        if (t1[0].equals(yColumnName)) {
                            if (valueBuilder.length() > 0) {
                                valueBuilder.append(",");
                            }
                            valueBuilder.append(t);
                        }
                    }
                }
            }

            // 如果key和value都有有效数据，则写入到上下文中
            if (key.getLength() > 0 && valueBuilder.length() > 0) {
                value.set(valueBuilder.toString());
                context.write(key, value);
            }
        }
    }

    /**
     * valueReducer类继承了Reducer，用于将多条具有相同key的value合并。
     * 它将输入的value按列名累加，生成新的(key, value)对。
     */
    public static class valueReducer extends Reducer<Text, Text, Text, Text> {

        /**
         * reduce函数处理与特定key关联的所有value，将它们按列名累加。
         */
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            Map<String, Double> sumMap = new HashMap<>();

            // 遍历每个value，按列名累加数值
            for (Text value : values) {
                for (String pair : value.toString().split(",")) {
                    String[] countValue = pair.split(":");
                    if (countValue.length != 2) continue; // 忽略格式不正确的字段

                    try {
                        String countKey = countValue[0];
                        double countValueNum = Double.parseDouble(countValue[1]);
                        //double countValueNum = Double.parseDouble("1");
                        sumMap.put(countKey, sumMap.getOrDefault(countKey, 0.0) + countValueNum);
                    } catch (NumberFormatException e) {
                        // 记录错误日志并跳过非法数据
                        System.err.println("Invalid number format: " + pair);
                    }
                }
            }

            // 构建最终的value字符串，格式为"列名:累加值,列名:累加值..."
            StringBuilder resultBuilder = new StringBuilder();
            for (Map.Entry<String, Double> entry : sumMap.entrySet()) {
                if (resultBuilder.length() > 0) {
                    resultBuilder.append(",");
                }
                resultBuilder.append(entry.getKey()).append(":").append(entry.getValue());
            }

            // 将最终的(key, value)对写入到上下文中
            context.write(key, new Text(resultBuilder.toString()));
        }
    }

    /**
     * groupMapper类继承了Mapper，用于对输入数据进行简单的分组。
     * 它特别关注于等号分隔的键值对数据格式。
     */
    public static class groupMapper extends Mapper<Object, Text, Text, Text> {

        // 初始化key和value对象以供后续使用
        private final Text key = new Text();
        private final Text value = new Text();

        /**
         * map函数处理输入的每一行数据，提取出键值对并构建新的(key, value)对。
         */
        @Override
        protected void map(Object keyObj, Text valueText, Context context) throws IOException, InterruptedException {
            // 处理每一行数据，提取键值对
            String line = new String(valueText.getBytes(), 0, valueText.getLength(), StandardCharsets.UTF_8).trim();
            String[] keyValue = line.split("=");
            if (keyValue.length != 2) return; // 忽略格式不正确的字段

            this.key.set(keyValue[1].trim());
            this.value.set("1");
            context.write(this.key, this.value);
        }
    }

    /**
     * groupReducer类继承了Reducer，用于对分组后的数据进行计数。
     * 它将每个分组的计数累加，生成新的(key, value)对。
     */
    public static class groupReducer extends Reducer<Text, Text, Text, Text> {

        /**
         * reduce函数处理与特定key关联的所有value，将它们累加。
         */
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            double sum = 0.0;
            for (Text text : values) {
                try {
                    sum += Double.parseDouble(text.toString());
                } catch (NumberFormatException e) {
                    // 记录错误日志并跳过非法数据
                    System.err.println("Invalid number format: " + text.toString());
                }
            }

            // 将最终的(key, value)对写入到上下文中
            context.write(key, new Text(String.valueOf(sum)));
        }
    }
}
