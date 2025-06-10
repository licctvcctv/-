package com.controller;

import com.annotation.IgnoreAuth;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            import com.service.DevicedetectionService;
            import com.entity.DevicedetectionEntity;
                                                                                    import com.service.HadoopService;
import com.utils.R;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/hadoop")
public class HadoopController {
                                                                                                                                                                            @Autowired
                private DevicedetectionService devicedetectionService;
                                                                                    
    @Autowired
    private HadoopService hadoopService;

    @IgnoreAuth
    @RequestMapping("/analyze")
    public R analyze(HttpServletRequest request) throws Exception {
        value(request);
        valueMul(request);
        group(request);
        return R.ok();
    }

    /**
    * （按值统计）
    */
    private void value(HttpServletRequest request) throws Exception {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
                                    for (int i = 0; i < ",title,detectiontime,detectiontime".split(",").length; i++) {
                        String xColumn = ",title,detectiontime,detectiontime".split(",")[i];
                        if ("".equals(xColumn)) {
                            continue;
                        }

                        String[] timeTypes = ",日,日".trim().split(",");
                        String[] yColumns = ",runningtime,operationtemperature,workingpressure".trim().split(",");
                        String timeType = (i < timeTypes.length && timeTypes[i] != "" && !timeTypes[i].equals("无")) ? timeTypes[i] : "";
                        String where = "";
                        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                        ew.setSqlSelect(xColumn, yColumns[i]);
                        ew.isNotNull(xColumn).ne(xColumn, "");
                        ew.isNotNull(yColumns[i]).ne(yColumns[i], "");
                                                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (tableName.equals("yonghu")) {
                                        ew.eq("zhanghao", (String) request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                    
                        hadoopService.runMapReduceJobForValue(devicedetectionService.selectMaps(ew),"devicedetection", xColumn, timeType,  yColumns[i]);
                    }
                                                                                                                                            }

    /**
     * （按值统计(多)）
     */
    private void valueMul(HttpServletRequest request) throws Exception {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                                    for (int i = 0; i < ";title".split(";").length; i++) {
                        String xColumn = ";title".split(";")[i];
                        if ("".equals(xColumn)) {
                            continue;
                        }
                        EntityWrapper<DevicedetectionEntity> ew = new EntityWrapper<DevicedetectionEntity>();
                        String[] timeTypes = ";日".trim().split(";");
                        String[] yColumns = ";ratedvoltage,ratedfrequencyhz,ratedcurrent,ratedshortcircuitbreakingcurrentka,effectivevalueofratedshor,ratedshortcircuitclosingcurrent,ratedpeakwithstandcurrentka,".trim().split(";");
                        String timeType = (i < timeTypes.length && timeTypes[i] != "" && !timeTypes[i].equals("无")) ? timeTypes[i] : "";
                        String where = "";
                        // 去除yColumns[i]末尾的逗号
                        String yColumn = yColumns[i];
                        if (yColumn.endsWith(",")) {
                            yColumn = yColumn.substring(0, yColumn.length() - 1);
                        }
                        ew.setSqlSelect(xColumn, yColumn);
                                                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (tableName.equals("yonghu")) {
                                        ew.eq("zhanghao", (String) request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                            hadoopService.runMapReduceJobForValue(devicedetectionService.selectMaps(ew), "devicedetection", xColumn, timeType, yColumn.split(","));
                    }
                                                                                                                                            }

    /**
    * （按类统计）
    */
    private void group(HttpServletRequest request) throws Exception {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            hadoopService.runMapReduceJobForGroup(devicedetectionService.selectMaps(new EntityWrapper<DevicedetectionEntity>().setSqlSelect("protectiongrade").isNotNull("protectiongrade").ne("protectiongrade", "")), "devicedetection", "protectiongrade");
                                                                                                                                    hadoopService.runMapReduceJobForGroup(devicedetectionService.selectMaps(new EntityWrapper<DevicedetectionEntity>().setSqlSelect("equipmentstatus").isNotNull("equipmentstatus").ne("equipmentstatus", "")), "devicedetection", "equipmentstatus");
                                                                                                                                                                                                                                                                                                                                                                                                                                                    }

}
