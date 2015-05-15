package spr.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spr.board.model.AddressVO;

@Repository
public class AddressDao implements IAddressDao {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(AddressDao.class);
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public String getName() {
		return "AddressDao";
	}

	@Override
	public IAddressDao getDao() {
		return this;
	}

	/**
	 * 읍/면/동의 일부 검색
	 */
	@Override
	public List<AddressVO> findByUMD(String umd) {
		SqlSession session = sqlSessionFactory.openSession(false);
		logger.debug("umd : " + umd );
		List<AddressVO> addresses = session.selectList("Address.findByUMD", umd);
		session.close();
		return addresses;
	}

}
