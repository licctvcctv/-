package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.StzcDao;
import com.entity.StzcEntity;
import com.service.StzcService;
import com.entity.vo.StzcVO;
import com.entity.view.StzcView;

@Service("stzcService")
public class StzcServiceImpl extends ServiceImpl<StzcDao, StzcEntity> implements StzcService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StzcEntity> page = this.selectPage(
                new Query<StzcEntity>(params).getPage(),
                new EntityWrapper<StzcEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<StzcEntity> wrapper) {
		  Page<StzcView> page =new Query<StzcView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<StzcVO> selectListVO(Wrapper<StzcEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public StzcVO selectVO(Wrapper<StzcEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<StzcView> selectListView(Wrapper<StzcEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public StzcView selectView(Wrapper<StzcEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
