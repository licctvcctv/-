package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.StzcEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.StzcVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.StzcView;


/**
 * 电气设备
 *
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public interface StzcService extends IService<StzcEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<StzcVO> selectListVO(Wrapper<StzcEntity> wrapper);
   	
   	StzcVO selectVO(@Param("ew") Wrapper<StzcEntity> wrapper);
   	
   	List<StzcView> selectListView(Wrapper<StzcEntity> wrapper);
   	
   	StzcView selectView(@Param("ew") Wrapper<StzcEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<StzcEntity> wrapper);

   	

}

