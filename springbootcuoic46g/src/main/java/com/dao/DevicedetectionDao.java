package com.dao;

import com.entity.DevicedetectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DevicedetectionVO;
import com.entity.view.DevicedetectionView;


/**
 * 设备检测
 * 
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public interface DevicedetectionDao extends BaseMapper<DevicedetectionEntity> {
	
	List<DevicedetectionVO> selectListVO(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);
	
	DevicedetectionVO selectVO(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);
	
	List<DevicedetectionView> selectListView(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);

	List<DevicedetectionView> selectListView(Pagination page,@Param("ew") Wrapper<DevicedetectionEntity> wrapper);

	
	DevicedetectionView selectView(@Param("ew") Wrapper<DevicedetectionEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DevicedetectionEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DevicedetectionEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DevicedetectionEntity> wrapper);



}
