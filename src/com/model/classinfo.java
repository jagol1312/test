package com.model;

public class classinfo {
	private int id;
	private String no;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "classinfo [id=" + id + ", no=" + no + ", name=" + name + "]";
	}
	
}
