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


import com.dao.DevicedetectionDao;
import com.entity.DevicedetectionEntity;
import com.service.DevicedetectionService;
import com.entity.vo.DevicedetectionVO;
import com.entity.view.DevicedetectionView;

@Service("devicedetectionService")
public class DevicedetectionServiceImpl extends ServiceImpl<DevicedetectionDao, DevicedetectionEntity> implements DevicedetectionService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevicedetectionEntity> page = this.selectPage(
                new Query<DevicedetectionEntity>(params).getPage(),
                new EntityWrapper<DevicedetectionEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DevicedetectionEntity> wrapper) {
		  Page<DevicedetectionView> page =new Query<DevicedetectionView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DevicedetectionVO> selectListVO(Wrapper<DevicedetectionEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DevicedetectionVO selectVO(Wrapper<DevicedetectionEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DevicedetectionView> selectListView(Wrapper<DevicedetectionEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DevicedetectionView selectView(Wrapper<DevicedetectionEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<DevicedetectionEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<DevicedetectionEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<DevicedetectionEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
