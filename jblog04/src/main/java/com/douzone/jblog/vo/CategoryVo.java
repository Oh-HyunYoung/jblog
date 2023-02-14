package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String id;
	private String name;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", id=" + id + ", name=" + name + "]";
	}
	
	
}
