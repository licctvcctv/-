package com.dao;

import com.entity.DevicedetectionforecastEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DevicedetectionforecastVO;
import com.entity.view.DevicedetectionforecastView;


/**
 * 故障预测
 * 
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
public interface DevicedetectionforecastDao extends BaseMapper<DevicedetectionforecastEntity> {
	
	List<DevicedetectionforecastVO> selectListVO(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);
	
	DevicedetectionforecastVO selectVO(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);
	
	List<DevicedetectionforecastView> selectListView(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);

	List<DevicedetectionforecastView> selectListView(Pagination page,@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);

	
	DevicedetectionforecastView selectView(@Param("ew") Wrapper<DevicedetectionforecastEntity> wrapper);
	

}
