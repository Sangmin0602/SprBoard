package spr.board.dao;

import java.util.List;

import spr.board.model.AddressVO;

public interface IAddressDao extends IDao<IAddressDao>{

	/**
	 * 읍/면/동으로 검색
	 * @param umd
	 * @return
	 */
	public List<AddressVO> findByUMD(String umd) ;
}
