package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DevicedetectionforecastEntity;
import com.entity.view.DevicedetectionforecastView;

import com.service.DevicedetectionforecastService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.DevicedetectionService;
import com.entity.DevicedetectionEntity;
import java.text.DecimalFormat;
import weka.core.*;
import java.util.*;
import java.util.stream.Stream;
import cn.hutool.core.date.DateUtil;
            import weka.classifiers.trees.RandomForest;

/**
 * 故障预测
 * 后端接口
 * @author
 * @email
 * @date 2025-06-08 19:56:16
 */
@RestController
@RequestMapping("/devicedetectionforecast")
public class DevicedetectionforecastController {
    @Autowired
    private DevicedetectionforecastService devicedetectionforecastService;
            @Autowired
        private DevicedetectionService devicedetectionService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DevicedetectionforecastEntity devicedetectionforecast,
                                                                                HttpServletRequest request){
                                    //设置查询条件
        EntityWrapper<DevicedetectionforecastEntity> ew = new EntityWrapper<DevicedetectionforecastEntity>();


                //查询结果
        PageUtils page = devicedetectionforecastService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, devicedetectionforecast), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
        @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DevicedetectionforecastEntity devicedetectionforecast,
                                                                                HttpServletRequest request){
                //设置查询条件
        EntityWrapper<DevicedetectionforecastEntity> ew = new EntityWrapper<DevicedetectionforecastEntity>();

                //查询结果
        PageUtils page = devicedetectionforecastService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, devicedetectionforecast), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



            /**
         * 列表
         */
        @RequestMapping("/lists")
        public R list( DevicedetectionforecastEntity devicedetectionforecast){
            EntityWrapper<DevicedetectionforecastEntity> ew = new EntityWrapper<DevicedetectionforecastEntity>();
        ew.allEq(MPUtil.allEQMapPre( devicedetectionforecast, "devicedetectionforecast"));
        return R.ok().put("data", devicedetectionforecastService.selectListView(ew));
        }

    /**
    * 查询
    */
    @RequestMapping("/query")
    public R query(DevicedetectionforecastEntity devicedetectionforecast){
            EntityWrapper< DevicedetectionforecastEntity> ew = new EntityWrapper< DevicedetectionforecastEntity>();
    ew.allEq(MPUtil.allEQMapPre( devicedetectionforecast, "devicedetectionforecast"));
        DevicedetectionforecastView devicedetectionforecastView =  devicedetectionforecastService.selectView(ew);
    return R.ok("查询故障预测成功").put("data", devicedetectionforecastView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
            DevicedetectionforecastEntity devicedetectionforecast = devicedetectionforecastService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(devicedetectionforecast,deSens);
        return R.ok().put("data", devicedetectionforecast);
    }

    /**
     * 前台详情
     */
        @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
            DevicedetectionforecastEntity devicedetectionforecast = devicedetectionforecastService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(devicedetectionforecast,deSens);
        return R.ok().put("data", devicedetectionforecast);
    }




    /**
     * 后台保存
     */
@RequestMapping("/save")
        public R save(@RequestBody DevicedetectionforecastEntity devicedetectionforecast, HttpServletRequest request){
                                                                                                //ValidatorUtils.validateEntity(devicedetectionforecast);
                                                                            devicedetectionforecastService.insert(devicedetectionforecast);
        return R.ok().put("data",devicedetectionforecast.getId());
    }

    /**
     * 前台保存
     */
                @RequestMapping("/add")
    public R add(@RequestBody DevicedetectionforecastEntity devicedetectionforecast, HttpServletRequest request){
                                                                                                //ValidatorUtils.validateEntity(devicedetectionforecast);
                                                                                    devicedetectionforecastService.insert(devicedetectionforecast);
        return R.ok().put("data",devicedetectionforecast.getId());
    }





    /**
     * 修改
     */
@RequestMapping("/update")
@Transactional
                    public R update(@RequestBody DevicedetectionforecastEntity devicedetectionforecast, HttpServletRequest request){
        //ValidatorUtils.validateEntity(devicedetectionforecast);
                                                                                                                //全部更新
            devicedetectionforecastService.updateById(devicedetectionforecast);
                        return R.ok();
    }





    /**
     * 删除
     */
@RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids){
            devicedetectionforecastService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }








    /**
     * 获取最新预测的故障信息
     */
    @RequestMapping("/latestPredicted")
    public R getLatestPredictedForecast() {
        EntityWrapper<DevicedetectionforecastEntity> ew = new EntityWrapper<>();
        ew.isNotNull("equipmentstatus");
        ew.ne("equipmentstatus", "");
        ew.orderBy("id", false); // Order by id descending

        List<DevicedetectionforecastEntity> list = devicedetectionforecastService.selectList(ew);

        DevicedetectionforecastEntity latestForecast = null;
        if (list != null && !list.isEmpty()) {
            latestForecast = list.get(0); // Get the first element, which is the latest due to ordering
        }

        if (latestForecast != null) {
            // Optionally desensitize if needed, similar to other methods
            // Map<String, String> deSens = new HashMap<>();
            // DeSensUtil.desensitize(latestForecast,deSens);
            return R.ok().put("data", latestForecast);
        } else {
            return R.ok().put("data", null).put("msg", "No predicted forecasts found.");
        }
    }

            /**
        * 预测算法
        */
        @RequestMapping("/forecast")
        public R forecast(@RequestBody Map<String, Object> params) throws Exception {
        // 特征值
        String[] eigenValueArr = "title,operationtemperature,workingpressure,workingcurrent".split(",");
        // 目标值
        String[] targetValueArr = "equipmentstatus".split(",");
                String[] sqlSelectArr = Stream.of(eigenValueArr, targetValueArr).flatMap(Arrays::stream).toArray(String[]::new);
        // 模型训练
        Wrapper<DevicedetectionEntity> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect(sqlSelectArr);
                                // 从数据库获取需要元数据
        List<Map<String,Object>> list =devicedetectionService.selectMaps(wrapper);
                    // 模型训练
            Instances instances = createInstances(list, sqlSelectArr);

            // 创建一个map来存储预测结果
            Map<String, Object> forecastRes = forecastRes(instances, params, eigenValueArr, targetValueArr);

            // 更新数据库或其他操作
            EntityWrapper<DevicedetectionforecastEntity> ew = new EntityWrapper<>();
            ew.eq("id", params.get("id"));
            for (Map.Entry<String, Object> entry : forecastRes.entrySet()) {
                String updateSet = entry.getKey() + "='" + entry.getValue().toString() + "'";
                    devicedetectionforecastService.updateForSet(updateSet, ew);
            }
            params.putAll(forecastRes);
            return R.ok(params);
            }

        /**
         * 预测结果
         * @param instances
         * @param params
         * @param eigenValueArr
         * @param targetValueArr
         * @return
         * @throws Exception
         */
                    private Map<String, Object> forecastRes(Instances instances, Map<String, Object> params, String[] eigenValueArr, String[] targetValueArr) throws Exception {
            Map<String, Object> forecastRes = new HashMap<>();
            // 预测结果
            // 创建一个与训练数据集结构相同的 Instance
            double[] instanceValue = new double[instances.numAttributes()];
            int x = 0;
            for (String attr : eigenValueArr) {
                if (instances.attribute(attr).isNominal()) {
                    // 对于名义属性，使用indexOfValue
                    double res = instances.attribute(attr).indexOfValue(params.get(attr).toString());
                    instanceValue[x++] = res == -1 ? instances.attribute(attr).indexOfValue("unknown") : res; // 如果值不存在，使用Double.NaN
                } else {
                    if (isParsableDate(params.get(attr).toString())) {
                        instanceValue[x++] = parseDateStringToTimestamp(params.get(attr).toString());
                        System.out.println();
                    } else {
                        // 对于数值属性，直接使用数值
                        instanceValue[x++] = Double.parseDouble(params.get(attr).toString());
                    }

                }
            }
            for (String attr : targetValueArr) {
                instanceValue[x++] = Double.NaN;
            }

            Instance instance = new DenseInstance(1.0, instanceValue);
            instance.setDataset(instances);

            for (int i = 0; i < targetValueArr.length; i++) {
                // 设置类属性索引为当前目标属性
                instances.setClassIndex(instances.numAttributes() - targetValueArr.length + i);

                // 创建并训练随机森林模型
                RandomForest randomForest = new RandomForest();
                randomForest.buildClassifier(instances);

                // 为预测创建一个新的实例
                double[] instanceValueForPrediction = Arrays.copyOf(instanceValue, instanceValue.length);
                instanceValueForPrediction[instances.numAttributes() - targetValueArr.length + i] = Double.NaN; // 设置目标属性为缺失值

                Instance instanceForPrediction = new DenseInstance(1.0, instanceValueForPrediction);
                instanceForPrediction.setDataset(instances);

                // 进行预测
                double predictedValue = randomForest.classifyInstance(instanceForPrediction);
                if (instances.classAttribute().isNominal()) {
                    // 如果是名义属性，获取预测的类别名称
                    String predictedClassName = instances.classAttribute().value((int) predictedValue);
                    forecastRes.put(targetValueArr[i], predictedClassName);
                } else {
                    // 如果是数值属性，直接使用预测值
                    DecimalFormat df = new DecimalFormat("#.00");
                    double formattedPredictedValue = Double.parseDouble(df.format(predictedValue));
                    forecastRes.put(targetValueArr[i], formattedPredictedValue);
                }
            }
            return forecastRes;
        }

        /**
         * 训练模型
         */
        private Instances createInstances(List<Map<String, Object>> dataList, String[] attrs) throws Exception {
        // 遍历数据集以确定每个属性的类型
        Map<String, Set<Object>> uniqueValuesPerAttribute = new HashMap<>();
        for (Map<String, Object> data : dataList) {
            for (String attr : attrs) {
                uniqueValuesPerAttribute.computeIfAbsent(attr, k -> new HashSet<>()).add(data.get(attr));
            }
        }
        FastVector attributes = new FastVector();
        for (String attr : attrs) {
            Set<Object> uniqueValues = uniqueValuesPerAttribute.get(attr);
            if (isNominal(uniqueValues)) {
                // 如果是分类属性，创建分类属性
                FastVector nominalValues = new FastVector();
                uniqueValues.forEach(value -> nominalValues.addElement(value.toString()));
                nominalValues.add("unknown");
                attributes.addElement(new Attribute(attr, nominalValues));
            } else if (isDate(uniqueValues)) {
                // 如果是日期属性，创建数值属性（时间戳）
                attributes.addElement(new Attribute(attr));
            } else if (isDateString(uniqueValues)) {
                // 如果是字符串日期属性，创建数值属性（时间戳）
                attributes.addElement(new Attribute(attr));
            } else {
                attributes.addElement(new Attribute(attr));
            }
        }

        // 创建 Instances 对象
        Instances dataset = new Instances("dataset-name", attributes, dataList.size());
        dataset.setClassIndex(dataset.numAttributes() - 1); // 设置类属性索引
        // 填充数据
        for (Map<String, Object> data : dataList) {
            double[] instanceValue = new double[dataset.numAttributes()];
            int i = 0;
            for (String attr : attrs) {
                Attribute attribute = dataset.attribute(attr);
                Object value = data.get(attr);
                if (attribute.isNominal()) {
                    instanceValue[i++] = attribute.indexOfValue(value.toString());
                } else if (isDate(uniqueValuesPerAttribute.get(attr)) || isDateString(uniqueValuesPerAttribute.get(attr))) {
                    // 将日期转换为时间戳
                    long timestamp = value instanceof Date ? ((Date) value).getTime() : parseDateStringToTimestamp(value.toString());
                    instanceValue[i++] = timestamp;
                } else {
                    instanceValue[i++] = Double.parseDouble(value.toString());
                }
            }
            Instance instance = new DenseInstance(1.0, instanceValue);
            dataset.add(instance);
        }
        return dataset;
    }

        /**
         * 检测数据类型是否为字符串
         */
        private boolean isNominal(Set<Object> values) {
        // 检查是否所有的值都是字符串，并且数量是否超过某个阈值（例如，如果超过50%的值是唯一的，则可能是分类属性）
        return values.stream().allMatch(v -> v instanceof String);
    }

        /**
         * 检测数据类型是否为日期
         */
        private boolean isDate(Set<Object> values) {
        // 检查是否所有的值都是日期
        return values.stream().allMatch(v -> v instanceof Date);
    }

        /**
         * 检测数据类型是否为字符串日期
         */
        private boolean isDateString(Set<Object> values) {
        // 检查是否所有的值都是可以转换为日期的字符串
        return values.stream().allMatch(v -> v instanceof String && isParsableDate(v.toString()));
    }

        /**
         * 检查字符串是否可以解析为日期
         */
        private boolean isParsableDate(String value) {
        try {
            DateUtil.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        /**
         * 将字符串日期解析为时间戳
         */
        private long parseDateStringToTimestamp(String value) {
        try {
            Date date = DateUtil.parse(value);
            return date.getTime();
        } catch (Exception e) {
            throw new IllegalArgumentException("无法解析日期字符串: " + value, e);
        }
    }
        }
