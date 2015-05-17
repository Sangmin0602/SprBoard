package spr.board.dao;

import java.util.List;

import spr.board.model.AddressVO;

public interface IJoinDao extends IDao<IJoinDao> {
	public List<AddressVO> searchByUmd(String umd);
}
