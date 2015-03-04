package spr.board.model;

public class UserVO {

	private Integer seq;
	private String nickName;
	
	
	public UserVO(Integer seq, String nickName) {
		super();
		this.seq = seq;
		this.nickName = nickName;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
		UserVO other = (UserVO) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserVO [seq=" + seq + ", nickName=" + nickName + "]";
	}
	
	
	
	
}
