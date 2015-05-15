package spr.board.web.join;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.AddressVO;

@Service
public class JoinService implements IJoinService {

	private IDaoRepository repo;
	
	@Autowired
	public JoinService ( IDaoRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<AddressVO> searchByUMD(String umd) {
		return repo.getAddressDao().findByUMD(umd + "%");
	}

}
