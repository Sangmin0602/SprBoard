package spr.board.web.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;

@Service
public class UserService {

	@Autowired
	private IDaoRepository repo;
	
	public UserVO addNewUser ( Map<String, String> userInfo ) {
		UserVO user = new UserVO(
				userInfo.get("userId"), 
				userInfo.get("nickname"), 
				userInfo.get("email"), 
				userInfo.get("password"));
		
		return repo.getUserDao().insert(user);
	}
	
	public List<UserVO> findAllUsers() {
		List<UserVO> users = repo.getUserDao().findAll();
		return users;
	}
	
	public UserVO findBySeq(int seq) {
		UserVO user = repo.getUserDao().findBySeq(seq);
		return user;
	}
	
	public UserVO findById(String userId) {
		UserVO user = repo.getUserDao().findById(userId);
		return user;
	}
	
	public void insertUser(UserVO newUser) {
		repo.getUserDao().insert(newUser);
	}

	public int getTotalUsers() {
		return repo.getUserDao().getTotalUsers();
	}

	public List<UserVO> findByRange(int offset, int rpp) {
		return repo.getUserDao().findByRange(offset, rpp);
	}

	public boolean deleteUser(int userSeq) {
		repo.getUserDao().delete(userSeq);
		return true;
	}
	
}
