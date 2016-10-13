// Author :Vikesh Inbasekharan
// Date : 30 - 09 - 2016
// course : 08-672

package com.vikesh.hw3JavaBeans;
import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteId")
public class Favorite {

	private int favoriteId;
	private int userId;
	private String URL;
	private String comment;
	private int clickCount;
	
	public int getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	
    
	
}
