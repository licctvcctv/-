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


import com.dao.DevicedetectionforecastDao;
import com.entity.DevicedetectionforecastEntity;
import com.service.DevicedetectionforecastService;
import com.entity.vo.DevicedetectionforecastVO;
import com.entity.view.DevicedetectionforecastView;

@Service("devicedetectionforecastService")
public class DevicedetectionforecastServiceImpl extends ServiceImpl<DevicedetectionforecastDao, DevicedetectionforecastEntity> implements DevicedetectionforecastService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevicedetectionforecastEntity> page = this.selectPage(
                new Query<DevicedetectionforecastEntity>(params).getPage(),
                new EntityWrapper<DevicedetectionforecastEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DevicedetectionforecastEntity> wrapper) {
		  Page<DevicedetectionforecastView> page =new Query<DevicedetectionforecastView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DevicedetectionforecastVO> selectListVO(Wrapper<DevicedetectionforecastEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DevicedetectionforecastVO selectVO(Wrapper<DevicedetectionforecastEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DevicedetectionforecastView> selectListView(Wrapper<DevicedetectionforecastEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DevicedetectionforecastView selectView(Wrapper<DevicedetectionforecastEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
