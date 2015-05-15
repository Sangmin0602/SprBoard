package spr.board.web.join;

import java.util.List;

import spr.board.model.AddressVO;

public interface IJoinService {
	
	public List<AddressVO> searchByUMD ( String umd);
}
