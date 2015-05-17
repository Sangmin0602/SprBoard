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
	private IJoinDao joinDao;
	
	@Override
	public IUserDao getUserDao() {
		return userDao;
	}

	@Override
	public IPostDao getPostDao() {
		return postDao;
	}

	@Override
	public IJoinDao getJoinDao() {
		return joinDao;
	}
	

}
