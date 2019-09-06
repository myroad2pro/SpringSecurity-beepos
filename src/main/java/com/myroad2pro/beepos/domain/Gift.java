package com.myroad2pro.beepos.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "gift")
public class Gift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "point", nullable = false)
	private int point;

	@Column(name = "sent_time", nullable = false)
	private Date sentTime;

	@ManyToOne(optional = false)
	@JoinTable(name = "gift_sender", 
			joinColumns = @JoinColumn(name = "gift_id"), 
			inverseJoinColumns = @JoinColumn(name = "sender_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account sender;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "gift_receiver", 
			joinColumns = @JoinColumn(name = "gift_id"), 
			inverseJoinColumns = @JoinColumn(name = "receiver_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account receiver;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "gift_hashtag", 
			joinColumns = @JoinColumn(name = "gift_id"),
			inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
	private Hashtag hashtag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

	public Gift(String message, int point, Date sentTime, Account sender, Account receiver, Hashtag hashtag) {
		super();
		this.message = message;
		this.point = point;
		this.sentTime = sentTime;
		this.sender = sender;
		this.receiver = receiver;
		this.hashtag = hashtag;
	}

	public Gift() {
		super();
	}
	
	
}
