package com.showbt.web.pojo;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Movie extends BaseText implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 下载地址
	 */
	private String downUrl;
	
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 推荐
	 */
	private int  recommend;

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	@Override
	public String toString() {
		return "Movie [downUrl=" + downUrl + ", nickName=" + nickName
				+ ", recommend=" + recommend + ", toString()="
				+ super.toString() + "]";
	}
	
}