package com.ops;

public class Transaction {
	int tid;
	String userid;
	String payeeid;
	int amount;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPayeeid() {
		return payeeid;
	}
	public void setPayeeid(String payeeid) {
		this.payeeid = payeeid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
