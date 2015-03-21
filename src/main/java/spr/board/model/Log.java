package spr.board.model;

import java.sql.Date;

public class Log {
	private static final long serialVersionUID = -7372303516713218870L;
	
	private Long id;//
	private Long userId;//
	private Date createDate;//만든 날짜
	private String content;//로그 내용
	private String operation;//사용자 하는 작업
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
