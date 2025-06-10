package com.dao;

import com.entity.StzcEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.StzcVO;
import com.entity.view.StzcView;


/**
 * 电气设备
 * 
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public interface StzcDao extends BaseMapper<StzcEntity> {
	
	List<StzcVO> selectListVO(@Param("ew") Wrapper<StzcEntity> wrapper);
	
	StzcVO selectVO(@Param("ew") Wrapper<StzcEntity> wrapper);
	
	List<StzcView> selectListView(@Param("ew") Wrapper<StzcEntity> wrapper);

	List<StzcView> selectListView(Pagination page,@Param("ew") Wrapper<StzcEntity> wrapper);

	
	StzcView selectView(@Param("ew") Wrapper<StzcEntity> wrapper);
	

}
