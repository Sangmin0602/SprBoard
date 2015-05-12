package spr.board.web.users;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spr.board.dao.DaoException;
import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;
import spr.board.utils.BoardUtils;
import spr.board.utils.Pagenation;

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
	
	@RequestMapping(value="/users2", method=RequestMethod.GET)
	public String listByUsers(HttpServletRequest req, HttpServletResponse res) throws Throwable{
		
		return "list-users";
	}
	
	@RequestMapping(value="/users.json", 
			method=RequestMethod.GET, 
			produces="application/json; charset=utf-8")
	@ResponseBody
	public String listUsers(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		int rpp = BoardUtils.convertToInt(req.getParameter("rows"), 10);
		int currentPage = BoardUtils.convertToInt(req.getParameter("page"), 1);
		int totalUsers = service.getTotalUsers();
		
		Pagenation pgn = new Pagenation(currentPage-1, rpp, totalUsers);
		
		List<UserVO> user = service.findByRange(pgn.getOffset(), pgn.getRPP());
		
		JSONObject json = new JSONObject();
		//Request URL:http://localhost:8080/web/users.json?_search=false
							//&nd=1430222075259&rows=4&page=page&sidx=&sord=asc
		
		json.put("page", currentPage);
		json.put("total", pgn.getTotalPage());
		json.put("records", totalUsers);
		
//		System.out.println("뭐냐 이거? " + json);
		JSONArray arr = new JSONArray();
		for(int i = 0; i < user.size(); i++) {
			UserVO u = user.get(i);
			JSONObject tmp = new JSONObject();
			tmp.put("seq", u.getSeq());
			tmp.put("nickname", u.getNickName());
			tmp.put("userId", u.getUserId());
			tmp.put("email", u.getEmail());
			tmp.put("password", u.getPassword());
			tmp.put("whenjoined", u.getWhenJoined());
			arr.add(tmp);
		}
		
		json.put("rows", arr);
		
		return json.toJSONString();
	}
	
	@RequestMapping(value="/users/new", 
			method=RequestMethod.POST, 
			produces="application/json; charset=utf-8")
	@ResponseBody
	public String insertUser ( HttpServletRequest req ) {
		/*
		 * userId=xxx   -> setUserId ( req.getParamet("userId") );
		 * nickname=xxx 
		 * email=xx%40naver.com 
		 * password=23233232
		 * oper=add | edit | del
		 * id=_empty
		 */
		String userId = req.getParameter("userId");
		String nick = req.getParameter("nickname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserVO user = service.addUser( userId, nick, email, password);
		
		JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		return json.toJSONString();
	}
	
	@RequestMapping(value="/users/del", 
			method=RequestMethod.POST, 
			produces="application/json; charset=utf-8")
	@ResponseBody
	public String deleteUser(HttpServletRequest req) {
		int id = BoardUtils.convertToInt(req.getParameter("id"));
		JSONObject json = new JSONObject();
		try {
			service.deleteUser(id);
			json.put("success", Boolean.TRUE);
		} catch(DaoException e) {
			json.put("success", Boolean.FALSE);
			json.put("cause", "e6000");
		}
		return json.toJSONString();
	}
	@RequestMapping(value="users/edit",
				method = RequestMethod.POST,
				produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateUser(HttpServletRequest req) {
		int seq = BoardUtils.convertToInt(req.getParameter("id"));
		JSONObject json = new JSONObject();
		UserVO user = service.findBySeq(seq);
		if ( user == null ) {
			json.put("success", Boolean.FALSE);
			json.put("cause", "e7000");
			return json.toJSONString();
		}
		
		String userId = req.getParameter("userId");
		String nick = req.getParameter("nickname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		user.setUserId(userId);
		user.setNickName(nick);
		user.setEmail(email);
		user.setPassword(password);
		
		try {
			UserVO userCheck = service.updateUser(user);
			json.put("success", Boolean.TRUE);
		} catch ( DaoException e) {
			json.put("success", Boolean.FALSE);
			json.put("cause", "e7001");
		}
		
		return json.toJSONString();
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
