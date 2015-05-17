package spr.board.dao;

public interface IDaoRepository {

	public IUserDao getUserDao();
//	public void setUserDao(IUserDao dao);

	public IPostDao getPostDao();
//	public void setPostDao(IPostDao dao);
	
	public IJoinDao getJoinDao();
}
