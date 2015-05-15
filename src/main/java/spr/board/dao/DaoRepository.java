package spr.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="daoRepo")
public class DaoRepository implements IDaoRepository {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IPostDao postDao;
	
	@Autowired
	private IAddressDao addrDao;

//	public DaoRepository(IUserDao userDao, IPostDao postDao) {
//		this.userDao = userDao;
//		this.postDao = postDao;
//	}
	@Override
	public IUserDao getUserDao() {
		return userDao;
	}

	@Override
	public IPostDao getPostDao() {
		return postDao;
	}

	@Override
	public IAddressDao getAddressDao() {
		return addrDao;
	}

}
