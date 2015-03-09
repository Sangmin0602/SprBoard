package spr.board.web.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spr.board.model.UserVO;
import spr.board.mybatis.IUserDao;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
		
	@Autowired
	private IUserDao userDao;
	
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public String listAllUser(Model model) {
		logger.debug("요청 url : "+"/users");
		try {
			List<UserVO> users = userDao
					.findAll();
			model.addAttribute("users", users);
			return "users";
			
		} catch ( Exception e) {
			logger.error("exception!!!!!!!!!!!!!", e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/userDetail", method=RequestMethod.GET)
	public String userBySeq(Integer seq, Model model) {
		logger.debug("요청 url : "+"/users");
		UserVO userSeq = userDao.userBySeq(seq);
		model.addAttribute("userBySeq", userSeq);
		return "userByseq";
	}
	//BbsController 참조
	@RequestMapping(value = "/loginCheck", method =RequestMethod.GET)
	public String loginCheck (String id, String pw) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("pass", pw);
		UserVO userVo = userDao.loginCheck(param);
		if(userVo == null) {
			return "loginCheck";
		}
		return "loginCheck";
	}
	//BbsController 참조
	@RequestMapping(value = "/insertNewUser", method = RequestMethod.POST)
	public String insertNewUser(UserVO newUser) {
		userDao.insertNewUser(newUser);
		return "users";
	}
	
}
