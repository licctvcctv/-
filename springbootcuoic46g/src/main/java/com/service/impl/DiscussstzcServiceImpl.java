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


import com.dao.DiscussstzcDao;
import com.entity.DiscussstzcEntity;
import com.service.DiscussstzcService;
import com.entity.vo.DiscussstzcVO;
import com.entity.view.DiscussstzcView;

@Service("discussstzcService")
public class DiscussstzcServiceImpl extends ServiceImpl<DiscussstzcDao, DiscussstzcEntity> implements DiscussstzcService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussstzcEntity> page = this.selectPage(
                new Query<DiscussstzcEntity>(params).getPage(),
                new EntityWrapper<DiscussstzcEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussstzcEntity> wrapper) {
		  Page<DiscussstzcView> page =new Query<DiscussstzcView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussstzcVO> selectListVO(Wrapper<DiscussstzcEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussstzcVO selectVO(Wrapper<DiscussstzcEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussstzcView> selectListView(Wrapper<DiscussstzcEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussstzcView selectView(Wrapper<DiscussstzcEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
