package spr.board.dao;

import java.util.List;

import spr.board.model.PostVO;

public interface IPostDao extends IDao<IPostDao> {
	
	public List<PostVO> findAll() throws DaoException;

	public PostVO findBySeq ( int seq, boolean updateViewCount) throws DaoException;
	
	public PostVO insert(PostVO posting) throws DaoException;

	/**
	 * 조회수를 변경함(글을 조회할때 사용됨)
	 * @param seq
	 * @param viewCount
	 */
	public boolean updateViewCount(int seq, int viewCount);
	
}
