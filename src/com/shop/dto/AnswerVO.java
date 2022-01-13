package com.shop.dto;

public class AnswerVO {
	private int abno;
	private int qbno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	
	private int ref;
	
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getAbno() {
		return abno;
	}
	public void setAbno(int abno) {
		this.abno = abno;
	}
	public int getQbno() {
		return qbno;
	}
	public void setQbno(int qbno) {
		this.qbno = qbno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
