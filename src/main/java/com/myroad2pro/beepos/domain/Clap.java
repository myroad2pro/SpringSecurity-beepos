package com.myroad2pro.beepos.domain;

import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "clap")
public class Clap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "gift_clap", 
			joinColumns = @JoinColumn(name = "clap_id"), 
			inverseJoinColumns = @JoinColumn(name = "gift_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Gift gift;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "account_clap",
			joinColumns = @JoinColumn(name = "clap_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Clap(Gift gift, Account account) {
		super();
		this.gift = gift;
		this.account = account;
	}

	public Clap() {
		super();
	}
}
