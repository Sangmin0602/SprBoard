package spr.board.dao;

import spr.board.mybatis.IPostDao;
import spr.board.mybatis.IUserDao;

public interface IDaoRepository {

	public IUserDao getUserDao();
//	public void setUserDao(IUserDao dao);

	public IPostDao getPostDao();
//	public void setPostDao(IPostDao dao);
}
