package com.myroad2pro.beepos.domain;

public class GiftInfo {
	private String sender;
	
	private String receiver;
	
	private Integer points;
	
	private String message;
	
	private String hashtag;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public GiftInfo(String sender, String receiver, Integer points, String message, String hashtag) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.points = points;
		this.message = message;
		this.hashtag = hashtag;
	}
}
