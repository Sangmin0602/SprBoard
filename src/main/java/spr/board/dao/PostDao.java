package spr.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spr.board.model.BoardDataBean;
import spr.board.model.PostVO;

@Repository
public class PostDao implements IPostDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * 주어진 PK의 글을 읽어들임. 
	 * 
	 * @param seq 읽어들일 글의 PK
	 * @param updateViewCount true 이면 조회수 1 증가시킴.
	 */
	@Override
	public PostVO findBySeq( int seq, boolean updateViewCount) throws DaoException {
		
		SqlSession session = sqlSessionFactory.openSession(false);
		PostVO posting = null;
		
		try {
			posting = findBySeq(session, seq);
			if ( updateViewCount) {
				Integer newViewCnt = posting.getViewCount() + 1;
				updateViewCount(session, seq, newViewCnt);
				posting.setViewCount(newViewCnt);
			}
			session.commit();
			return posting;
		} catch ( SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}
	

	PostVO findBySeq (SqlSession session, int seq) {
		PostVO posting = null;
		
		posting = session.selectOne("Posting.findBySeq", seq);
		return posting;
	}
	
	@Override
	public PostVO insert(PostVO posting) throws DaoException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			int insertCount = session.insert("Posting.newPosting", posting);
			if ( insertCount != 1) {
				throw new SQLException("새글 삽입 실패 : " + posting);
			}
			session.commit();
			return findBySeq( session, posting.getSeq());
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<PostVO> findAll(HttpServletRequest request,HttpServletResponse response) throws DaoException {
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			return session.selectList("Posting.findAll");
		} finally {
			session.close();
		}
	}
	
	void updateViewCount(SqlSession session, int seq, int viewCount) throws SQLException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("postingId", seq);
		params.put("viewCount", viewCount);
		int updateCount = session.update("Posting.updateViewCount", params);
		
		if ( updateCount != 1) {
			throw new SQLException("조회수 갱신 실패 : posting[" + seq + "] count[" + viewCount + "]");
		}
		
	}
	@Override
	public boolean updateViewCount(int seq, int viewCount) {
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			updateViewCount(session, seq, viewCount);
			session.commit();
			return true;
		} catch ( SQLException e) {
			session.rollback();
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	@Override
	public String getName() {
		return "postingDao";
	}

	@Override
	public IPostDao getDao() {
		return this;
	}


	@Override
	public int getTotalCount() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(false);
		int count = session.selectOne("Posting.ViewListCount");
		
		if ( count < 1) {
			throw new SQLException("목록이 없습니다");
		}
		session.close();
		return count;
	}

	@Override
	public List<BoardDataBean> getArticles(int startRow, int pageSize) {
		SqlSession session = sqlSessionFactory.openSession(false);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("startRow", startRow);
		params.put("pageSize", pageSize);
		List<BoardDataBean> list = session.selectList("Posting.ViewList", params);
		session.close();
		return list;
	}


	@Override
	public void getInsertArticle(BoardDataBean article) {
		//바로 article 객체로 못받는지?
		//제네릭 할떄 integer, String 같이 있을때?
		SqlSession session = sqlSessionFactory.openSession();
		
		Map params = new HashMap();
		params.put("num", article.getNum());
		params.put("writer", article.getWriter());
		params.put("email", article.getEmail());
		params.put("subject", article.getSubject());
		params.put("passwd", article.getPasswd());
		params.put("reg_date", article.getReg_date());
		params.put("readcount", article.getReadcount());
		params.put("ref", article.getRef());
		params.put("re_step", article.getRe_step());
		params.put("re_level", article.getRe_level());
		params.put("content", article.getContent());
		params.put("ip", article.getIp());
		
		session.insert("Posting.WritePro",params);
		session.close();
	}


	@Override
	public BoardDataBean getArticle(int num) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		int updateCount  = session.update("Posting.updateReadCnt",num);
		if (updateCount != 1) {
			throw new SQLException("조회수 갱신 실패 : posting[" + num + "] count[" + num + "]");
		}
		BoardDataBean board = session.selectOne("Posting.article", num);
		session.close();
		return board;
	}


	@Override
	public BoardDataBean updateGetArticle(int num) {
		SqlSession session = sqlSessionFactory.openSession(true);
		BoardDataBean board = session.selectOne("Posting.article", num);
		session.close();
		return board;
	}


	@Override
	public void updateArticle(BoardDataBean article) throws SQLException {
		SqlSession  session = sqlSessionFactory.openSession(true);
		
		List<BoardDataBean> board = session.selectList("Posting.getPasswd", article.getNum());
		for (BoardDataBean boardPw : board) {
			int i = 0;
			String pw = boardPw.getPasswd();
			if(pw.equals(article.getPasswd())){
				int chk = session.update("UpdateContent",article);
				
				if(chk != 1) {
					throw new SQLException("업데이트 실패하였습니다.");
				}
			}
			i++;
		}
		session.close();
	}

	@Override
	public int deleteArticle(int num, String passwd) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession(true);
		
		List<BoardDataBean> board = session.selectList("Posting.getPasswd", num);
		int chk = 0;
		for (BoardDataBean boardPw : board) {
			int i = 0;
			String pw = boardPw.getPasswd();
			if(pw.equals(passwd)){
				chk = session.delete("Posting.deleteArticle", num);
				
				if(chk != 1) {
					throw new SQLException("업데이트 실패하였습니다.");
				}
			}
			i++;
		}
		session.close();
		return chk;
	}

}
