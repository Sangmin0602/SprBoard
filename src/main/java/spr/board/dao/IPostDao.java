package spr.board.dao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spr.board.model.BoardDataBean;
import spr.board.model.PostVO;

public interface IPostDao extends IDao<IPostDao> {
	
	public List<PostVO> findAll(HttpServletRequest request, HttpServletResponse response) throws DaoException;

	public PostVO findBySeq ( int seq, boolean updateViewCount) throws DaoException;
	
	public PostVO insert(PostVO posting) throws DaoException;

	/**
	 * 조회수를 변경함(글을 조회할때 사용됨)
	 * @param seq
	 * @param viewCount
	 */
	public boolean updateViewCount(int seq, int viewCount);
	/**
	 * List Test
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalCount() throws SQLException;

	public List<BoardDataBean> getArticles(int startRow, int pageSize);

	public void getInsertArticle(BoardDataBean article);

	public BoardDataBean getArticle(int num) throws SQLException;

	public BoardDataBean updateGetArticle(int num);

	public void updateArticle(BoardDataBean article) throws SQLException;

	public int deleteArticle(int num, String passwd) throws SQLException;
	
}
