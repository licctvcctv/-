package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DevicedetectionforecastEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DevicedetectionforecastVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DevicedetectionforecastView;


/**
 * 故障预测
 *
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
public interface DevicedetectionforecastService extends IService<DevicedetectionforecastEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DevicedetectionforecastVO> selectListVO(Wrapper<DevicedetectionforecastEntity> wrapper);
   	
   	DevicedetectionforecastVO selectVO(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);
   	
   	List<DevicedetectionforecastView> selectListView(Wrapper<DevicedetectionforecastEntity> wrapper);
   	
   	DevicedetectionforecastView selectView(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DevicedetectionforecastEntity> wrapper);

   	

}

