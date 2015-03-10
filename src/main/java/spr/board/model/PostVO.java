package spr.board.model;

public class PostVO {
	private Integer seq;
	private String title;
	private String content;
	private Integer viewCount;
	private String whenCreated;
	private UserVO writer;

	public PostVO(Integer seq, String title, String content, Integer viewCount,
			String whenCreated) {
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.whenCreated = whenCreated;
	}
	public PostVO(Integer seq, String title, String content, Integer viewCount,
			String whenCreated, UserVO writer) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.whenCreated = whenCreated;
		this.writer = writer;
	}
	public PostVO(String title, String content, UserVO writer) {
		this.title = title;
		this.content = content;
		this.viewCount = 0;
		this.writer = writer;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public String getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}
	public UserVO getWriter() {
		return writer;
	}
	public void setWriter(UserVO writer) {
		this.writer = writer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostVO other = (PostVO) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PostVO [seq=" + seq + ", title=" + title + ", content="
				+ content + ", viewCount=" + viewCount + ", whenCreated="
				+ whenCreated + ", writer=" + writer + "]";
	}
}
