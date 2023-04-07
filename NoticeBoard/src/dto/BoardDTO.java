package dto;

import java.sql.Date;

/*
 * 게시글DTO
 * 날짜, 제목, 내용, 작성자 이름
 */
public class BoardDTO {
	private String id;
	private Date date;
	private String title;
	private String content;
	private String writer;
	
	public BoardDTO() {}
	
	public BoardDTO(String id, Date date, String title, String content, String writer) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
}
