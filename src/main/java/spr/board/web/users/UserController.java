package spr.board.web.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	//private IDaoRepository repository;
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/users", method=RequestMethod.GET) 
	public String listAllUsers(Model model) {
		List<UserVO> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "list-all-users";
	}
	
//	public void setDaoRepository(IDaoRepository repo) {
//		this.repository = repo;
//	}
//	
//	@RequestMapping(value="/users", method=RequestMethod.GET)
//	public String listAllUsers(Model model) {
//		List<UserVO> users = repository.getUserDao().findAll();
//		logger.debug( "[REQ] for all users : size (" + users.size() + ")" );
//		
//		model.addAttribute("users", users);
//		return "list-all-users";
//	}
}
