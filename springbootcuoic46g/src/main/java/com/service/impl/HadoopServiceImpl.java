package com.service.impl;

import com.config.HadoopConfig;
import com.config.HadoopMapReduce;
import com.service.HadoopService;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Hadoop 服务实现类，提供 MapReduce 作业的执行及相关操作
 */
@Service
public class HadoopServiceImpl implements HadoopService {
    // MapReduce 作业输出的默认文件名
    private static final String PART_R_00000 = "part-r-00000";

    /**
     * 运行一个针对特定值的MapReduce作业
     * 该方法主要用于处理需要根据时间类型对一个或多个列进行分析的数据列表
     *
     * @param dataList     含有待处理数据的列表，每个元素是一个Map，键为列名，值为对应的数据项
     * @param className    MapReduce作业的主类名称
     * @param xColumnName  X轴列名，通常代表时间或其他自变量列
     * @param timeType     时间类型，用于指定时间序列的处理方式
     * @param yColumnNames Y轴列名数组，代表需要进行分析的一个或多个列
     * @throws Exception 如果作业运行过程中出现错误，则抛出异常
     */
    @Override
    public void runMapReduceJobForValue(List<Map<String, Object>> dataList, String className, String xColumnName, String timeType, String... yColumnNames) throws Exception {
        // 检查 yColumnNames 是否为空
        if (yColumnNames == null || yColumnNames.length == 0) {
            throw new IllegalArgumentException("yColumnNames cannot be empty");
        }

        // 上传数据到 HDFS
        Path inputPath = HadoopConfig.uploadHdfs(dataList, "input", "value", className, xColumnName, String.join("_", yColumnNames), (StringUtils.isEmpty(timeType) || timeType.equals("无") ? "timeType" : timeType) + ".json");
        // 创建输出路径
        Path outputPath = HadoopConfig.createPath("output", "value", className, xColumnName, String.join("_", yColumnNames), (StringUtils.isEmpty(timeType) || timeType.equals("无") ? "timeType" : timeType));

        // 配置并运行 MapReduce 任务
        boolean result = configureAndRunJob(inputPath, outputPath, String.format("runMapReduceJobForValue_%s_%s_%s_%S", className, xColumnName, String.join("_", yColumnNames), (StringUtils.isEmpty(timeType) || timeType.equals("无") ? "timeType" : timeType)), HadoopMapReduce.valueMapper.class, HadoopMapReduce.valueReducer.class, xColumnName, yColumnNames);

        if (result) {
            // 下载 HDFS 文件内容
            List<String> lineList = HadoopConfig.downloadHdfsFilesContent(outputPath, PART_R_00000);
            // 处理文件内容
            List<List<Map<String, Object>>> resultList = processLines(lineList, xColumnName, yColumnNames);

            // 将结果写入本地文件
            HadoopConfig.writeLocal((yColumnNames.length == 1) ? resultList.get(0) : resultList, "value", className, xColumnName, String.join("_", yColumnNames), (StringUtils.isEmpty(timeType) || timeType.equals("无") ? "timeType" : timeType));
        }
    }

    /**
     * 运行一个针对分组数据的MapReduce作业
     * 该方法主要用于处理需要根据时间类型对某一列进行分组分析的数据列表
     *
     * @param dataList   含有待处理数据的列表，每个元素是一个Map，键为列名，值为对应的数据项
     * @param className  MapReduce作业的主类名称
     * @param columnName 需要进行分组分析的列名
     * @param timeType   时间类型，用于指定时间序列的处理方式
     * @throws Exception 如果作业运行过程中出现错误，则抛出异常
     */
    @Override
    public void runMapReduceJobForGroup(List<Map<String, Object>> dataList, String className, String columnName) throws Exception {
        // 上传数据到 HDFS
        Path inputPath = HadoopConfig.uploadHdfs(dataList, "input", "group", className, columnName, "timeType.json");
        
        // 创建输出路径
        Path outputPath = HadoopConfig.createPath("output", "group", className, columnName);

        // 配置并运行 MapReduce 任务
        boolean result = configureAndRunJob(inputPath, outputPath, String.format("runMapReduceJobForGroup_%s_%s", className, columnName), HadoopMapReduce.groupMapper.class, HadoopMapReduce.groupReducer.class, "", "");
        if (result) {
            // 下载 HDFS 文件内容并处理
            List<Map<String, String>> resList = HadoopConfig.downloadHdfsFilesContent(outputPath, PART_R_00000).parallelStream()
                    .map(pair -> parseLine(pair, columnName))
                    .collect(Collectors.toList());
            // 将结果写入本地文件
            HadoopConfig.writeLocal(resList, "group", className, columnName);
        }
    }

    /**
     * 配置并运行 MapReduce 作业
     *
     * @param inputPath    输入路径
     * @param outputPath   输出路径
     * @param jobName      作业名称
     * @param mapperClass  Mapper 类
     * @param reducerClass Reducer 类
     * @param xColumnName  X 轴列名
     * @param yColumnNames Y 轴列名数组
     * @return 作业完成情况
     * @throws Exception 如果作业配置或运行失败
     */
    private boolean configureAndRunJob(Path inputPath, Path outputPath, String jobName, Class<?> mapperClass, Class<?> reducerClass, String xColumnName, String... yColumnNames) throws Exception {
        // 获取 Hadoop 配置
        Configuration conf = HadoopConfig.getConfiguration();
        // 设置 xColumnName 配置
        if (StringUtils.isNotBlank(xColumnName)) {
            conf.set("xColumnName", xColumnName);
        }
        // 设置 yColumnNames 配置
        if (yColumnNames != null && yColumnNames.length > 0) {
            conf.setStrings("yColumnNames", yColumnNames);
        }

        // 创建并配置 Job
        Job job = Job.getInstance(conf, jobName);
        job.setJarByClass(HadoopMapReduce.class);
        job.setMapperClass((Class<? extends Mapper>) mapperClass);
        job.setCombinerClass((Class<? extends Reducer>) reducerClass);
        job.setReducerClass((Class<? extends Reducer>) reducerClass);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        // 运行 Job 并等待完成
        boolean result = job.waitForCompletion(true);
        if (!result) {
            throw new RuntimeException("MapReduce job failed: " + jobName);
        }
        return true;
    }

    /**
     * 处理 MapReduce 输出行
     *
     * @param lines        行列表
     * @param xColumnName  X 轴列名
     * @param yColumnNames Y 轴列名数组
     * @return 处理后的结果列表
     */
    private List<List<Map<String, Object>>> processLines(List<String> lines, String xColumnName, String[] yColumnNames) {
        Map<String, List<Map<String, Object>>> resultMap = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split("\\t+");
            if (parts.length < 2) continue;

            String mskPart = parts[0];
            String mskValue = mskPart.split(":")[1];

            String countsPart = parts[1];
            String[] counts = countsPart.split(",");
            for (String count : counts) {
                String[] countParts = count.split(":");
                if (countParts.length != 2) continue;

                String countName = countParts[0];
                Double countValue = Double.parseDouble(countParts[1]);

                if (Arrays.asList(yColumnNames).contains(countName)) {
                    Map<String, Object> resultMapEntry = new HashMap<>();
                    resultMapEntry.put("total", countValue);
                    resultMapEntry.put(xColumnName, mskValue);

                    resultMap.computeIfAbsent(countName, k -> new ArrayList<>()).add(resultMapEntry);
                }
            }
        }

        return Arrays.stream(yColumnNames)
                .map(resultMap::get)
                .collect(Collectors.toList());
    }

    /**
     * 解析 MapReduce 输出行
     *
     * @param line       行内容
     * @param columnName 列名
     * @return 解析后的结果映射
     */
    private Map<String, String> parseLine(String line, String columnName) {
        String[] parts = line.split("\\t");
        if (parts.length != 2) return new HashMap<>();

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(columnName, parts[0]);
        resultMap.put("total", parts[1]);
        return resultMap;
    }
}
