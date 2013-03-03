package com.showbt.web.pojo;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Movie  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	/**
	 * 电影名称
	 */
	@Persistent
	private String title;
	/**
	 * 电影图片，可以多张
	 */
	@Persistent
	private String[] picture;
	/**
	 * 电影简介
	 */
	@Persistent
	private String content;
	/**
	 * 录入时间
	 */
	@Persistent
	private String addTime;
	/**
	 * 喜欢数
	 */
	@Persistent
	private int like;
	/**
	 * 电影标签
	 */
	@Persistent
	private String[] tag;	
	/**
	 * 下载地址
	 */
	@Persistent
	private String downUrl;	
	/**
	 * 用户ＩＤ
	 */
	@Persistent
	private Long userId;
	
	@Persistent
	private String nickName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getPicture() {
		return picture;
	}

	public void setPicture(String[] picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String[] getTag() {
		return tag;
	}

	public void setTag(String[] tag) {
		this.tag = tag;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}