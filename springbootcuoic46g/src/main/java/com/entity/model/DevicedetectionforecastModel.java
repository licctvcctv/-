package com.entity.model;

import com.entity.DevicedetectionforecastEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 故障预测
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
public class DevicedetectionforecastModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
