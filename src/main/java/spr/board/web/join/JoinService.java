package spr.board.web.join;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.AddressVO;

@Service
public class JoinService implements IJoinService {
	@Autowired
	private IDaoRepository repo;
	
	@Override
	public List<AddressVO> searchByUmd(String umd) {
		List<AddressVO> addr = repo.getJoinDao().searchByUmd(umd);
		return addr;
	}

}
