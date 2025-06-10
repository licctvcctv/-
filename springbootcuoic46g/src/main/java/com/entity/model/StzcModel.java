package com.entity.model;

import com.entity.StzcEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 电气设备
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
public class StzcModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 图片链接
	 */
	
	private String imgurl;
		
	/**
	 * 产品介绍
	 */
	
	private String jieshao;
		
	/**
	 * 核心优势
	 */
	
	private String youshi;
		
	/**
	 * 技术参数(图片)
	 */
	
	private String chanshu;
		
	/**
	 * 详情链接
	 */
	
	private String xqurl;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：图片链接
	 */
	 
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	/**
	 * 获取：图片链接
	 */
	public String getImgurl() {
		return imgurl;
	}
				
	
	/**
	 * 设置：产品介绍
	 */
	 
	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}
	
	/**
	 * 获取：产品介绍
	 */
	public String getJieshao() {
		return jieshao;
	}
				
	
	/**
	 * 设置：核心优势
	 */
	 
	public void setYoushi(String youshi) {
		this.youshi = youshi;
	}
	
	/**
	 * 获取：核心优势
	 */
	public String getYoushi() {
		return youshi;
	}
				
	
	/**
	 * 设置：技术参数(图片)
	 */
	 
	public void setChanshu(String chanshu) {
		this.chanshu = chanshu;
	}
	
	/**
	 * 获取：技术参数(图片)
	 */
	public String getChanshu() {
		return chanshu;
	}
				
	
	/**
	 * 设置：详情链接
	 */
	 
	public void setXqurl(String xqurl) {
		this.xqurl = xqurl;
	}
	
	/**
	 * 获取：详情链接
	 */
	public String getXqurl() {
		return xqurl;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
