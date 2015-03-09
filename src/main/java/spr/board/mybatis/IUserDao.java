package spr.board.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spr.board.model.UserVO;

@Repository
public interface IUserDao {
	
	public List<UserVO> findAll();
	
	public UserVO userBySeq( Integer seq);
	
	public UserVO loginCheck( Map<String, String> param);
	
	public void insertNewUser(UserVO user);
	
}
