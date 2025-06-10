package com.dao;

import com.entity.DiscussstzcEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussstzcVO;
import com.entity.view.DiscussstzcView;


/**
 * stzc评论表
 * 
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
public interface DiscussstzcDao extends BaseMapper<DiscussstzcEntity> {
	
	List<DiscussstzcVO> selectListVO(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);
	
	DiscussstzcVO selectVO(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);
	
	List<DiscussstzcView> selectListView(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);

	List<DiscussstzcView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussstzcEntity> wrapper);

	
	DiscussstzcView selectView(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);
	

}
