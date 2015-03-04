package spr.board.dao;

import java.util.List;

import spr.board.model.UserVO;

public interface IUserDao {
	public List<UserVO> findAll();
}
