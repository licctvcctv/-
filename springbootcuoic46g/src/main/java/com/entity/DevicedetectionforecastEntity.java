package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 故障预测
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
@TableName("devicedetectionforecast")
public class DevicedetectionforecastEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DevicedetectionforecastEntity() {
		
	}
	
	public DevicedetectionforecastEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 名称
	 */
					
	private String title;
	
	/**
	 * 工作温度
	 */
					
	private Double operationtemperature;
	
	/**
	 * 工作压力
	 */
					
	private Double workingpressure;
	
	/**
	 * 设备状态
	 */
					
	private String equipmentstatus;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：工作温度
	 */
	public void setOperationtemperature(Double operationtemperature) {
		this.operationtemperature = operationtemperature;
	}
	/**
	 * 获取：工作温度
	 */
	public Double getOperationtemperature() {
		return operationtemperature;
	}
	/**
	 * 设置：工作压力
	 */
	public void setWorkingpressure(Double workingpressure) {
		this.workingpressure = workingpressure;
	}
	/**
	 * 获取：工作压力
	 */
	public Double getWorkingpressure() {
		return workingpressure;
	}
	/**
	 * 设置：设备状态
	 */
	public void setEquipmentstatus(String equipmentstatus) {
		this.equipmentstatus = equipmentstatus;
	}
	/**
	 * 获取：设备状态
	 */
	public String getEquipmentstatus() {
		return equipmentstatus;
	}

}
