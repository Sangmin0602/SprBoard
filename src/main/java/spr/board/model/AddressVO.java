package spr.board.model;

public class AddressVO {
	private Integer seq;
	private String zipCode;
	private String mainAddr;
	private String fullAddr;
	
	public AddressVO(Integer seq, String zipCode, String mainAddr,
			String fullAddr) {
		this.seq = seq;
		this.zipCode = zipCode;
		this.mainAddr = mainAddr;
		this.fullAddr = fullAddr;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public String getZipCode() {
		return zipCode;
	}
	public String getMainAddr() {
		return mainAddr;
	}
	public String getFullAddr() {
		return fullAddr;
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
		AddressVO other = (AddressVO) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
}
