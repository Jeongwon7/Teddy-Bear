package com.shop.utility;

public class Criteria {//멤버변수만 private, 생성자랑 메서드는 public
	//페이징 처리를 위해서 필요한 파라미터는 1) 페이지번호, 2) 한 페이지당 보여줄 레코드의 개수가 필요하다
	private int pageNum;
	private int amount;
	
	private String type;// 검색할 때 제목, 내용(검색할 카테고리) 저장
	private String keyword;// 검색할 단어를 저장
	
	public Criteria() {
		this(1,10);
	}
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
