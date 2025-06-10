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

import com.entity.DevicedetectionEntity;
import com.entity.view.DevicedetectionView;

import com.service.DevicedetectionService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 设备检测
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
@RestController
@RequestMapping("/devicedetection")
public class DevicedetectionController {
    @Autowired
    private DevicedetectionService devicedetectionService;
                
    
    
    
    


    
    
    
    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DevicedetectionEntity devicedetection,
                                                                                                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if(tableName.equals("yonghu")) {
                    devicedetection.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                        //设置查询条件
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                                                                                                                                                                                                                                                                                                                                                                                                    
        
                //查询结果
        PageUtils page = devicedetectionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, devicedetection), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DevicedetectionEntity devicedetection,
                                                                                                                                                                                                                                                HttpServletRequest request){
                //设置查询条件
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                                                                                                                                                                                                                                                                                                                                                                                                    
                //查询结果
        PageUtils page = devicedetectionService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, devicedetection), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    
            /**
         * 列表
         */
        @RequestMapping("/lists")
        public R list( DevicedetectionEntity devicedetection){
            EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
        ew.allEq(MPUtil.allEQMapPre( devicedetection, "devicedetection"));
        return R.ok().put("data", devicedetectionService.selectListView(ew));
        }
    
    /**
    * 查询
    */
    @RequestMapping("/query")
    public R query(DevicedetectionEntity devicedetection){
            EntityWrapper< DevicedetectionEntity> ew = new EntityWrapper< DevicedetectionEntity>();
    ew.allEq(MPUtil.allEQMapPre( devicedetection, "devicedetection"));
        DevicedetectionView devicedetectionView =  devicedetectionService.selectView(ew);
    return R.ok("查询设备检测成功").put("data", devicedetectionView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
            DevicedetectionEntity devicedetection = devicedetectionService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(devicedetection,deSens);
        return R.ok().put("data", devicedetection);
    }

    /**
     * 前台详情
     */
        @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
            DevicedetectionEntity devicedetection = devicedetectionService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(devicedetection,deSens);
        return R.ok().put("data", devicedetection);
    }

    
    
    
    /**
     * 后台保存
     */
@RequestMapping("/save")
        public R save(@RequestBody DevicedetectionEntity devicedetection, HttpServletRequest request){
                                                                                                                                                                                                                                                                                                                                                                                                            //ValidatorUtils.validateEntity(devicedetection);
                                                                            devicedetectionService.insert(devicedetection);
        return R.ok().put("data",devicedetection.getId());
    }

    /**
     * 前台保存
     */
                @RequestMapping("/add")
    public R add(@RequestBody DevicedetectionEntity devicedetection, HttpServletRequest request){
                                                                                                                                                                                                                                                                                                                                                                                                            //ValidatorUtils.validateEntity(devicedetection);
                                                                                    devicedetectionService.insert(devicedetection);
        return R.ok().put("data",devicedetection.getId());
    }

    
    
    

    /**
     * 修改
     */
@RequestMapping("/update")
@Transactional
                    public R update(@RequestBody DevicedetectionEntity devicedetection, HttpServletRequest request){
        //ValidatorUtils.validateEntity(devicedetection);
                                                                                                                                                                                                                                                                                                                                                                                                                            //全部更新
            devicedetectionService.updateById(devicedetection);
                        return R.ok();
    }

    

    
    
    /**
     * 删除
     */
@RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids){
            devicedetectionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

            /**
         * 提醒接口
         */
        @RequestMapping("/remind/{columnName}/{type}")
        public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
            @PathVariable("type") String type,@RequestParam Map<String, Object> map) {

        Wrapper<DevicedetectionEntity> wrapper = new EntityWrapper<DevicedetectionEntity>();

        // 从map中获取remindStart和remindEnd，组装提醒过滤条件
        Object remindStart = map.get("remindstart") != null ? map.get("remindstart").toString() : null;
        Object remindEnd = map.get("remindend") != null ? map.get("remindend").toString() : null;
        if ("2".equals(type)) {
            Date startDate = null;
            Date endDate = null;
            if (null != remindStart) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindStart.toString()));
                startDate = c.getTime();
            }
            if (null != remindEnd) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(remindEnd.toString()));
                endDate = c.getTime();
            }
            if (startDate != null && endDate != null) {
                if (startDate.before(endDate)) {
                    wrapper.ge(columnName, startDate).le(columnName, endDate);
                } else {
                    wrapper.ge(columnName, startDate).or().le(columnName, endDate);
                }
            } else if (startDate == null && endDate != null) {
                wrapper.le(columnName, endDate);
            } else if (startDate != null && endDate == null) {
                wrapper.ge(columnName, startDate);
            }
        } else {
            if (remindStart != null && remindEnd != null) {
                if (Double.parseDouble(remindStart.toString()) < Double.parseDouble(remindEnd.toString())) {
                    wrapper.ge(columnName, remindStart).le(columnName, remindEnd);
                } else {
                    wrapper.ge(columnName, remindStart).or().le(columnName, remindEnd);
                }
            } else if (remindStart == null && remindEnd != null) {
                wrapper.le(columnName, remindEnd);
            } else if (remindStart != null && remindEnd == null) {
                wrapper.ge(columnName, remindStart);
            }
        }
        
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        wrapper.andNew().eq("zhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                    //根据条件，查询结果
        List<DevicedetectionEntity> list = devicedetectionService.selectList(wrapper);
        Map<String,Object> res= new HashMap<>();
        res.put("count", list.size());
                                    res.put("data",list.stream().map(DevicedetectionEntity::getTitle).collect(Collectors.toList()));
                        return R.ok(res);
    }
        

    
    
    
            /**
        * 文件导入
        */
    @RequestMapping("/importExcel")
                public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            //获取输入流
            InputStream inputStream = file.getInputStream();
            //创建读取工作簿
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行
            int rowTotal=sheet.getPhysicalNumberOfRows();
            if(rowTotal > 1) {
                //获取单元格
                for (int i = 1; i < rowTotal; i++) {
                    Row row = sheet.getRow(i);
                        DevicedetectionEntity devicedetectionEntity =new DevicedetectionEntity();
                        devicedetectionEntity.setId(new Date().getTime());
                                                                                            String title = CommonUtil.getCellValue(row.getCell(0));
                                                                devicedetectionEntity.setTitle(title);
                                                                                                                            String ratedvoltage = CommonUtil.getCellValue(row.getCell(1));
                                                                devicedetectionEntity.setRatedvoltage(Double.parseDouble(ratedvoltage));
                                                                                                                            String ratedfrequencyhz = CommonUtil.getCellValue(row.getCell(2));
                                                                devicedetectionEntity.setRatedfrequencyhz(Double.parseDouble(ratedfrequencyhz));
                                                                                                                            String ratedcurrent = CommonUtil.getCellValue(row.getCell(3));
                                                                devicedetectionEntity.setRatedcurrent(Double.parseDouble(ratedcurrent));
                                                                                                                            String ratedshortcircuitbreakingcurrentka = CommonUtil.getCellValue(row.getCell(4));
                                                                devicedetectionEntity.setRatedshortcircuitbreakingcurrentka(Double.parseDouble(ratedshortcircuitbreakingcurrentka));
                                                                                                                            String effectivevalueofratedshor = CommonUtil.getCellValue(row.getCell(5));
                                                                devicedetectionEntity.setEffectivevalueofratedshor(Double.parseDouble(effectivevalueofratedshor));
                                                                                                                            String ratedshortcircuitclosingcurrent = CommonUtil.getCellValue(row.getCell(6));
                                                                devicedetectionEntity.setRatedshortcircuitclosingcurrent(Double.parseDouble(ratedshortcircuitclosingcurrent));
                                                                                                                            String ratedpeakwithstandcurrentka = CommonUtil.getCellValue(row.getCell(7));
                                                                devicedetectionEntity.setRatedpeakwithstandcurrentka(Double.parseDouble(ratedpeakwithstandcurrentka));
                                                                                                                            String powerfrequencywithstan = CommonUtil.getCellValue(row.getCell(8));
                                                                devicedetectionEntity.setPowerfrequencywithstan(Double.parseDouble(powerfrequencywithstan));
                                                                                                                            String lightningimpulsewithstan = CommonUtil.getCellValue(row.getCell(9));
                                                                devicedetectionEntity.setLightningimpulsewithstan(Double.parseDouble(lightningimpulsewithstan));
                                                                                                                            String protectiongrade = CommonUtil.getCellValue(row.getCell(10));
                                                                devicedetectionEntity.setProtectiongrade(protectiongrade);
                                                                                                                            String equipmentstatus = CommonUtil.getCellValue(row.getCell(11));
                                                                devicedetectionEntity.setEquipmentstatus(equipmentstatus);
                                                                                                                            String runningtime = CommonUtil.getCellValue(row.getCell(12));
                                                                devicedetectionEntity.setRunningtime(Double.parseDouble(runningtime));
                                                                                                                            String operationtemperature = CommonUtil.getCellValue(row.getCell(13));
                                                                devicedetectionEntity.setOperationtemperature(Double.parseDouble(operationtemperature));
                                                                                                                            String workingpressure = CommonUtil.getCellValue(row.getCell(14));
                                                                devicedetectionEntity.setWorkingpressure(Double.parseDouble(workingpressure));
                                                                                                                            String detectiontime = CommonUtil.getCellValue(row.getCell(15));
                                                            try {
                                        devicedetectionEntity.setDetectiontime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(detectiontime));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                                                                                                            String zhanghao = CommonUtil.getCellValue(row.getCell(16));
                                                                devicedetectionEntity.setZhanghao(zhanghao);
                                                                                                                            String shebeibiaozhun = CommonUtil.getCellValue(row.getCell(17));
                                                                devicedetectionEntity.setShebeibiaozhun(shebeibiaozhun);
                                                                                                                                        //向数据库中添加新对象
                        devicedetectionService.insert(devicedetectionEntity);//方法
                }
            }
            inputStream.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("导入成功");
    }
    
            /**
         * （按值统计）
         */
        @RequestMapping("/value/{xColumnName}/{yColumnName}")
        public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
                    //读取文件，如果文件存在，则优先返回文件内容
            java.nio.file.Path path = java.nio.file.Paths.get("value_devicedetection_" + xColumnName + "_" + yColumnName + "_timeType.json");
            if(java.nio.file.Files.exists(path)) {
                String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
                return R.ok().put("data", (new org.json.JSONArray(content)).toList());
            }
                //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                            //获取结果
        List<Map<String, Object>> result = devicedetectionService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }

        /**
         * （按值统计(多)）
         */
        @RequestMapping("/valueMul/{xColumnName}")
        public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
                    //读取文件，如果文件存在，则优先返回文件内容
            java.nio.file.Path path = java.nio.file.Paths.get("value_devicedetection_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
            if(java.nio.file.Files.exists(path)) {
                String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
                return R.ok().put("data", (new org.json.JSONArray(content)).toList());
            }
                String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                    for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = devicedetectionService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

        /**
         * （按值统计）时间统计类型
         */
        @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
        public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
                    //读取文件，如果文件存在，则优先返回文件内容
            java.nio.file.Path path = java.nio.file.Paths.get("value_devicedetection_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
            if(java.nio.file.Files.exists(path)) {
                String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
                return R.ok().put("data", (new org.json.JSONArray(content)).toList());
            }
                Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                            List<Map<String, Object>> result = devicedetectionService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

        /**
         * （按值统计）时间统计类型(多)
         */
        @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
        public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
        {
                            //读取文件，如果文件存在，则优先返回文件内容
                java.nio.file.Path path = java.nio.file.Paths.get("value_devicedetection_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
                if (java.nio.file.Files.exists(path)) {
                    String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
                    return R.ok().put("data", (new org.json.JSONArray(content)).toList());
                }
                        String[] yColumnNames = yColumnNameMul.split(",");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("xColumn", xColumnName);
            params.put("timeStatType", timeStatType);
            List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //构建查询统计条件
            EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                            String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if(tableName.equals("yonghu")) {
                            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
                        }
                                                                                                                                    for(int i=0;i<yColumnNames.length;i++) {
                params.put("yColumn", yColumnNames[i]);
                List<Map<String, Object>> result = devicedetectionService.selectTimeStatValue(params, ew);
                for(Map<String, Object> m : result) {
                    for(String k : m.keySet()) {
                        if(m.get(k) instanceof Date) {
                            m.put(k, sdf.format((Date)m.get(k)));
                        }
                    }
                }
                result2.add(result);
            }
            return R.ok().put("data", result2);
        }

        /**
         * 分组统计
         */
        @RequestMapping("/group/{columnName}")
        public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
                    //读取文件，如果文件存在，则优先返回文件内容
            java.nio.file.Path path = java.nio.file.Paths.get("group_devicedetection_" + columnName + "_timeType.json");
            if(java.nio.file.Files.exists(path)){
                String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
                return R.ok().put("data", (new org.json.JSONArray(content)).toList());
            }
                Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
                    }
                                                                                                            List<Map<String, Object>> result = devicedetectionService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

                                                                                                                                                                                                                                                                                                                                                                                                    
                                                                                                                                                                                                                                                                                                                                                                                                    
    
            /**
         * 总数量
         */
        @RequestMapping("/count")
        public R count(@RequestParam Map<String, Object> params,DevicedetectionEntity devicedetection, HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("yonghu")) {
                        devicedetection.setZhanghao((String)request.getSession().getAttribute("username"));
                    }
                                                                                                    EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
        int count = devicedetectionService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, devicedetection), params), params));
        return R.ok().put("data", count);
    }
            
                /**
         * 数据清洗
         *
         * @return
         */
        @RequestMapping("/cleanse")
        public R cleanse() throws NoSuchFieldException {
        // 获取数据集合
        List<DevicedetectionEntity> list = devicedetectionService.selectList(new EntityWrapper<DevicedetectionEntity>());
                                    // 过滤空值的数据
                list = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getTitle())).collect(Collectors.toList());
                                                                                                                                                            // 当Ratedvoltage是空时，将从list中获取非空的Ratedvoltage随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRatedvoltage())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRatedvoltage())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRatedvoltage(nonNullList.get(random.nextInt(nonNullList.size())).getRatedvoltage());
                        }
                    }
                }
                                                                                                            // 当Ratedfrequencyhz是空时，将从list中获取非空的Ratedfrequencyhz随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRatedfrequencyhz())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRatedfrequencyhz())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRatedfrequencyhz(nonNullList.get(random.nextInt(nonNullList.size())).getRatedfrequencyhz());
                        }
                    }
                }
                                                                                                            // 当Ratedcurrent是空时，将从list中获取非空的Ratedcurrent随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRatedcurrent())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRatedcurrent())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRatedcurrent(nonNullList.get(random.nextInt(nonNullList.size())).getRatedcurrent());
                        }
                    }
                }
                                                                                                                                                                                            // 当Effectivevalueofratedshor是空时，将从list中获取非空的Effectivevalueofratedshor随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getEffectivevalueofratedshor())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getEffectivevalueofratedshor())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setEffectivevalueofratedshor(nonNullList.get(random.nextInt(nonNullList.size())).getEffectivevalueofratedshor());
                        }
                    }
                }
                                                                                                            // 当Ratedshortcircuitclosingcurrent是空时，将从list中获取非空的Ratedshortcircuitclosingcurrent随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRatedshortcircuitclosingcurrent())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRatedshortcircuitclosingcurrent())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRatedshortcircuitclosingcurrent(nonNullList.get(random.nextInt(nonNullList.size())).getRatedshortcircuitclosingcurrent());
                        }
                    }
                }
                                                                                                            // 当Ratedpeakwithstandcurrentka是空时，将从list中获取非空的Ratedpeakwithstandcurrentka随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRatedpeakwithstandcurrentka())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRatedpeakwithstandcurrentka())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRatedpeakwithstandcurrentka(nonNullList.get(random.nextInt(nonNullList.size())).getRatedpeakwithstandcurrentka());
                        }
                    }
                }
                                                                                                            // 当Powerfrequencywithstan是空时，将从list中获取非空的Powerfrequencywithstan随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getPowerfrequencywithstan())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getPowerfrequencywithstan())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setPowerfrequencywithstan(nonNullList.get(random.nextInt(nonNullList.size())).getPowerfrequencywithstan());
                        }
                    }
                }
                                                                                                            // 当Lightningimpulsewithstan是空时，将从list中获取非空的Lightningimpulsewithstan随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getLightningimpulsewithstan())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getLightningimpulsewithstan())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setLightningimpulsewithstan(nonNullList.get(random.nextInt(nonNullList.size())).getLightningimpulsewithstan());
                        }
                    }
                }
                                                                                                            // 当Protectiongrade是空时，将从list中获取非空的Protectiongrade随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getProtectiongrade())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getProtectiongrade())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setProtectiongrade(nonNullList.get(random.nextInt(nonNullList.size())).getProtectiongrade());
                        }
                    }
                }
                                                                                                            // 当Equipmentstatus是空时，将从list中获取非空的Equipmentstatus随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getEquipmentstatus())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getEquipmentstatus())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setEquipmentstatus(nonNullList.get(random.nextInt(nonNullList.size())).getEquipmentstatus());
                        }
                    }
                }
                                                                                                            // 当Runningtime是空时，将从list中获取非空的Runningtime随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getRunningtime())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getRunningtime())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setRunningtime(nonNullList.get(random.nextInt(nonNullList.size())).getRunningtime());
                        }
                    }
                }
                                                                                                            // 当Operationtemperature是空时，将从list中获取非空的Operationtemperature随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getOperationtemperature())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getOperationtemperature())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setOperationtemperature(nonNullList.get(random.nextInt(nonNullList.size())).getOperationtemperature());
                        }
                    }
                }
                                                                                                            // 当Workingpressure是空时，将从list中获取非空的Workingpressure随机获取一个放到空值中
                for (DevicedetectionEntity entity : list) {
                    if (org.springframework.util.StringUtils.isEmpty(entity.getWorkingpressure())) {
                        List<DevicedetectionEntity> nonNullList = list.stream().filter(n -> !org.springframework.util.StringUtils.isEmpty(n.getWorkingpressure())).collect(Collectors.toList());
                        if (!nonNullList.isEmpty()) {
                            // 创建一个随机数生成器
                            Random random = new Random();
                            entity.setWorkingpressure(nonNullList.get(random.nextInt(nonNullList.size())).getWorkingpressure());
                        }
                    }
                }
                                                                                                                                                                                                                                                                                                                                                                        // 删除全部数据
            devicedetectionService.delete(new EntityWrapper<>());
        // 如果清洗后还存在数据，则批量增加回去
        if (!list.isEmpty() && list.size() > 0) {
                devicedetectionService.insertBatch(list);
        }
        return R.ok();
    }
    }
