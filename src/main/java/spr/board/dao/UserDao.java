package spr.board.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spr.board.model.UserVO;

@Repository
public class UserDao implements IUserDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory ;
	@Override
	public List<UserVO> findAll() throws DaoException {
		// "moms", "맘이", "mom@naver.com", "mmmm"
		List<UserVO> users = null;
		SqlSession session = sqlSessionFactory.openSession(false);
		try {
			users = session.selectList("User.findAll");
			return users;
		} finally {
			session.close();
		}
	}
	UserVO findBySeq( SqlSession session, Integer seq) throws DaoException {
			UserVO user = session.selectOne("User.userBySeq", seq);
			return user;
	}
	
	public UserVO findBySeq ( int seq) throws DaoException {
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			UserVO user = findBySeq(session, seq);
			return user;
		} finally {
			session.close();
		}
	}
	
	@Override
	public UserVO findById(String userId) throws DaoException {
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			UserVO user = session.selectOne("User.findById", userId);
			return user;
		} finally {
			session.close();
		}
	}
	

	@Override
	public UserVO insert(UserVO newUser) throws DaoException {
		// "moms", "맘이", "mom@naver.com", "mmmm"
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			int insertCount = session.insert("User.newUser", newUser);
			if ( insertCount != 1) {
				throw new SQLException("insert count가 1이 아님 : " + insertCount);
			}
			session.commit();
			return findBySeq(session, newUser.getSeq());
		} catch ( SQLException e) {
			session.rollback();
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean existsUserId(String userId) {
		// TODO 구현 안되었음.
		return false;
	}
	@Override
	public boolean exists(String userSeq) throws DaoException {
		// TODO 구현 안되었음
		return false;
	}

	@Override
	public void update(UserVO user) throws DaoException {
		// TODO 구현 안되었음

	}

	@Override
	public void delete(UserVO user) throws DaoException {
		// TODO 구현 안되었음

	}

	/**
	 * userid 와 password로 사용자 정보를 얻어냄.
	 */
	@Override
	public UserVO findUser(String id, String pw) {
		UserVO user = null;
		SqlSession session = sqlSessionFactory.openSession(false);
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", id);
			params.put("pass", pw);
			user = session.selectOne("User.login", params);
			return user;
		} finally {
			session.close();
		}
	}

	@Override
	public String getName() {
		return "userDao";
	}

	@Override
	public IUserDao getDao() {
		return this;
	}
}
