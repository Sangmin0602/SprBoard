package spr.board.web.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	private IDaoRepository repository;
	
	public void setDaoRepository(IDaoRepository repo) {
		this.repository = repo;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listAllUsers(Model model) {
		List<UserVO> users = repository.getUserDao().findAll();
		logger.debug( "[REQ] for all users : size (" + users.size() + ")" );
		
		model.addAttribute("users", users);
		return "list-all-users";
	}

	/*
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("list-all-users");		
		List<UserVO> users = repository.getUserDao().findAll();
		request.setAttribute("users", users);
		return mav;
	}
	*/
}
