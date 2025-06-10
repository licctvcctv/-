package com.entity.model;

import com.entity.DevicedetectionEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 设备检测
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public class DevicedetectionModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 额定电压(kv)
	 */
	
	private Double ratedvoltage;
		
	/**
	 * 额定频率(Hz)
	 */
	
	private Double ratedfrequencyhz;
		
	/**
	 * 额定电流(A)
	 */
	
	private Double ratedcurrent;
		
	/**
	 * 额定短路开断电流(kA)
	 */
	
	private Double ratedshortcircuitbreakingcurrentka;
		
	/**
	 * 额定短时耐受电流有效值(kA)
	 */
	
	private Double effectivevalueofratedshor;
		
	/**
	 * 额定短路关合电流(KA)
	 */
	
	private Double ratedshortcircuitclosingcurrent;
		
	/**
	 * 额定峰值耐受电流(kA)
	 */
	
	private Double ratedpeakwithstandcurrentka;
		
	/**
	 * 工频耐受电压(kV)
	 */
	
	private Double powerfrequencywithstan;
		
	/**
	 * 雷电冲击耐受电压(kV)
	 */
	
	private Double lightningimpulsewithstan;
		
	/**
	 * 防护等级
	 */
	
	private String protectiongrade;
		
	/**
	 * 设备状态
	 */
	
	private String equipmentstatus;
		
	/**
	 * 工作时长
	 */
	
	private Double runningtime;
		
	/**
	 * 工作温度
	 */
	
	private Double operationtemperature;
		
	/**
	 * 工作压力
	 */
	
	private Double workingpressure;
		
	/**
	 * 检测时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date detectiontime;
		
	/**
	 * 账号
	 */
	
	private String zhanghao;
		
	/**
	 * 设备标准
	 */
	
	private String shebeibiaozhun;
		
	/**
	 * 图片
	 */
	
	private String imgurl;
				
	
	/**
	 * 设置：额定电压(kv)
	 */
	 
	public void setRatedvoltage(Double ratedvoltage) {
		this.ratedvoltage = ratedvoltage;
	}
	
	/**
	 * 获取：额定电压(kv)
	 */
	public Double getRatedvoltage() {
		return ratedvoltage;
	}
				
	
	/**
	 * 设置：额定频率(Hz)
	 */
	 
	public void setRatedfrequencyhz(Double ratedfrequencyhz) {
		this.ratedfrequencyhz = ratedfrequencyhz;
	}
	
	/**
	 * 获取：额定频率(Hz)
	 */
	public Double getRatedfrequencyhz() {
		return ratedfrequencyhz;
	}
				
	
	/**
	 * 设置：额定电流(A)
	 */
	 
	public void setRatedcurrent(Double ratedcurrent) {
		this.ratedcurrent = ratedcurrent;
	}
	
	/**
	 * 获取：额定电流(A)
	 */
	public Double getRatedcurrent() {
		return ratedcurrent;
	}
				
	
	/**
	 * 设置：额定短路开断电流(kA)
	 */
	 
	public void setRatedshortcircuitbreakingcurrentka(Double ratedshortcircuitbreakingcurrentka) {
		this.ratedshortcircuitbreakingcurrentka = ratedshortcircuitbreakingcurrentka;
	}
	
	/**
	 * 获取：额定短路开断电流(kA)
	 */
	public Double getRatedshortcircuitbreakingcurrentka() {
		return ratedshortcircuitbreakingcurrentka;
	}
				
	
	/**
	 * 设置：额定短时耐受电流有效值(kA)
	 */
	 
	public void setEffectivevalueofratedshor(Double effectivevalueofratedshor) {
		this.effectivevalueofratedshor = effectivevalueofratedshor;
	}
	
	/**
	 * 获取：额定短时耐受电流有效值(kA)
	 */
	public Double getEffectivevalueofratedshor() {
		return effectivevalueofratedshor;
	}
				
	
	/**
	 * 设置：额定短路关合电流(KA)
	 */
	 
	public void setRatedshortcircuitclosingcurrent(Double ratedshortcircuitclosingcurrent) {
		this.ratedshortcircuitclosingcurrent = ratedshortcircuitclosingcurrent;
	}
	
	/**
	 * 获取：额定短路关合电流(KA)
	 */
	public Double getRatedshortcircuitclosingcurrent() {
		return ratedshortcircuitclosingcurrent;
	}
				
	
	/**
	 * 设置：额定峰值耐受电流(kA)
	 */
	 
	public void setRatedpeakwithstandcurrentka(Double ratedpeakwithstandcurrentka) {
		this.ratedpeakwithstandcurrentka = ratedpeakwithstandcurrentka;
	}
	
	/**
	 * 获取：额定峰值耐受电流(kA)
	 */
	public Double getRatedpeakwithstandcurrentka() {
		return ratedpeakwithstandcurrentka;
	}
				
	
	/**
	 * 设置：工频耐受电压(kV)
	 */
	 
	public void setPowerfrequencywithstan(Double powerfrequencywithstan) {
		this.powerfrequencywithstan = powerfrequencywithstan;
	}
	
	/**
	 * 获取：工频耐受电压(kV)
	 */
	public Double getPowerfrequencywithstan() {
		return powerfrequencywithstan;
	}
				
	
	/**
	 * 设置：雷电冲击耐受电压(kV)
	 */
	 
	public void setLightningimpulsewithstan(Double lightningimpulsewithstan) {
		this.lightningimpulsewithstan = lightningimpulsewithstan;
	}
	
	/**
	 * 获取：雷电冲击耐受电压(kV)
	 */
	public Double getLightningimpulsewithstan() {
		return lightningimpulsewithstan;
	}
				
	
	/**
	 * 设置：防护等级
	 */
	 
	public void setProtectiongrade(String protectiongrade) {
		this.protectiongrade = protectiongrade;
	}
	
	/**
	 * 获取：防护等级
	 */
	public String getProtectiongrade() {
		return protectiongrade;
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
				
	
	/**
	 * 设置：工作时长
	 */
	 
	public void setRunningtime(Double runningtime) {
		this.runningtime = runningtime;
	}
	
	/**
	 * 获取：工作时长
	 */
	public Double getRunningtime() {
		return runningtime;
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
	 * 设置：检测时间
	 */
	 
	public void setDetectiontime(Date detectiontime) {
		this.detectiontime = detectiontime;
	}
	
	/**
	 * 获取：检测时间
	 */
	public Date getDetectiontime() {
		return detectiontime;
	}
				
	
	/**
	 * 设置：账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
				
	
	/**
	 * 设置：设备标准
	 */
	 
	public void setShebeibiaozhun(String shebeibiaozhun) {
		this.shebeibiaozhun = shebeibiaozhun;
	}
	
	/**
	 * 获取：设备标准
	 */
	public String getShebeibiaozhun() {
		return shebeibiaozhun;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	/**
	 * 获取：图片
	 */
	public String getImgurl() {
		return imgurl;
	}
			
}
