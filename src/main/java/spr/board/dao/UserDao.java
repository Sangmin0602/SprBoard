package spr.board.dao;

import java.util.ArrayList;
import java.util.List;

import spr.board.model.UserVO;

public class UserDao implements IUserDao {

	@Override
	public List<UserVO> findAll() {
		ArrayList<UserVO> users = new ArrayList<UserVO>();
		users.add(new UserVO(5000, "gamja"));
		users.add(new UserVO(5001, "goguma"));
		
		return users;
	}

}
