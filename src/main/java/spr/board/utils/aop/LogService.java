package spr.board.utils.aop;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spr.board.model.Log;

@Service
public class LogService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	/**
	 * 신규
	 * @param log
	 */
	//@Transactional("transactionManager")
	public void insertLog(Log log){
		
		SqlSession session = sqlSessionFactory.openSession(false);
		session.insert("Log.insert",log);
		session.close();
	}
}
