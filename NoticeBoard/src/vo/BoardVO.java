package vo;

/*
 * 게시글DTO
 * 날짜, 제목, 내용, 작성자 이름
 */
public class BoardVO {
	private String id;
	private String date;
	private String title;
	private String content;
	private String writer;
	
	public BoardVO() {}
	
	public BoardVO(String id, String date, String title, String content, String writer) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public BoardVO(String id, String title, String content, String writer) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public BoardVO(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
