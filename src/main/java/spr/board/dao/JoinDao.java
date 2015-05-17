package spr.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spr.board.model.AddressVO;

@Repository
public class JoinDao implements IJoinDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public String getName() {
		return "JoinDao";
	}

	@Override
	public IJoinDao getDao() {
		return this;
	}

	@Override
	public List<AddressVO> searchByUmd(String umd) {
		SqlSession session = sqlSessionFactory.openSession(false);
		List<AddressVO> addr = session.selectList("Addr.searchByUmd", umd+"%");
		session.close();
		return addr;
	}

}
