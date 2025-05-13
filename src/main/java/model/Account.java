package model;

import java.io.Serializable;

public class Account implements Serializable {
	// ユーザー情報を格納するBeansクラス
	private String name;
	private String pass;
	private int accountId;
	
	public Account() {
		
	}
	public Account(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	public Account(String name, String pass, int accountId) {
		this(name, pass);
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
}
