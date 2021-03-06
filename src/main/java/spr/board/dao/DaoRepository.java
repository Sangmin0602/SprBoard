package spr.board.dao;

public class DaoRepository implements IDaoRepository {

	private IUserDao userDao;
	private IPostDao postDao;

	public DaoRepository(IUserDao userDao, IPostDao postDao) {
		this.userDao = userDao;
		this.postDao = postDao;
	}
	@Override
	public IUserDao getUserDao() {
		return userDao;
	}

	@Override
	public IPostDao getPostDao() {
		return postDao;
	}

}
