package com.entity.view;

import com.entity.StzcEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 电气设备
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
@TableName("stzc")
public class StzcView  extends StzcEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public StzcView(){
	}
 
 	public StzcView(StzcEntity stzcEntity){
 	try {
			BeanUtils.copyProperties(this, stzcEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
