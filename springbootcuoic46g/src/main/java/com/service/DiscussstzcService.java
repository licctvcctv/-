package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussstzcEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussstzcVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussstzcView;


/**
 * stzc评论表
 *
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
public interface DiscussstzcService extends IService<DiscussstzcEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussstzcVO> selectListVO(Wrapper<DiscussstzcEntity> wrapper);
   	
   	DiscussstzcVO selectVO(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);
   	
   	List<DiscussstzcView> selectListView(Wrapper<DiscussstzcEntity> wrapper);
   	
   	DiscussstzcView selectView(@Param("ew") Wrapper<DiscussstzcEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussstzcEntity> wrapper);

   	

}

