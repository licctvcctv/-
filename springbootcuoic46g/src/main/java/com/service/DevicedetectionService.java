package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DevicedetectionEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DevicedetectionVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DevicedetectionView;


/**
 * 设备检测
 *
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public interface DevicedetectionService extends IService<DevicedetectionEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DevicedetectionVO> selectListVO(Wrapper<DevicedetectionEntity> wrapper);
   	
   	DevicedetectionVO selectVO(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);
   	
   	List<DevicedetectionView> selectListView(Wrapper<DevicedetectionEntity> wrapper);
   	
   	DevicedetectionView selectView(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DevicedetectionEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<DevicedetectionEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<DevicedetectionEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<DevicedetectionEntity> wrapper);



}

