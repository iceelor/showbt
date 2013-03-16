package com.showbt.web.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.Text;

@Entity
@MappedSuperclass
public abstract class BaseText {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String picture;
	@Transient
	private String[] pictures;
	
	/**
	 * 内容
	 */
	private Text content;
	/**
	 * 录入时间
	 */
	private Date addTime;
	/**
	 * 喜欢数
	 */
	private int like;
	/**
	 *　标签
	 */
	private String tag;
	@Transient
	private String[] tags;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public String[] getPictures() {
		this.pictures = picture.split(",|，"); 
		return pictures;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getContent() {
		return content.getValue();
	}
	public void setContent(String text) {
		content = new Text(text);
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getTag() {
		return tag;
	}
	public String[] getTags() {
		this.tags = tag.split(",|，");
		return tags;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "BaseText [id=" + id + ", userId=" + userId + ", title=" + title
				+ ", picture=" + picture + ", content=" + content
				+ ", addTime=" + addTime + ", like=" + like + ", tag=" + tag
				+ ", toString()=" + super.toString() + "]";
	}
	
}
