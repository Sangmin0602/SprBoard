package spr.board.web.login;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spr.board.dao.IDaoRepository;
import spr.board.model.UserVO;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IDaoRepository repository;
	
	@RequestMapping(value="login")
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="login.json")
	public ModelAndView doLogin(@RequestParam String user,
			@RequestParam String password,
			HttpServletRequest req,
			HttpSession session) {
		logger.debug ( "[LOGIN REQ] " + user + ", " + password);

		String ctxPath = req.getContextPath();
		UserVO userVO = repository.getUserDao().findUser(user, password);
		
		ModelAndView mv = new ModelAndView("json/json-writer");
		String json = "";
		if ( userVO != null ) {
			session.setAttribute("user", userVO);
			json = loginSuccessResponse(req, userVO);
		} else {
			json = loginFailureResponse(req, userVO);
		}
		
		logger.debug("JSON RESPONSE : " + json );
		mv.addObject("json", json);
		
		return mv;
	}

	private String loginSuccessResponse(HttpServletRequest req, UserVO userVO) {
		JSONObject json =new JSONObject();
		json.put("success", Boolean.TRUE);
		json.put("nextUrl", getNextUrl(req));
		return json.toJSONString();
	}

	private String loginFailureResponse(HttpServletRequest req, UserVO userVO) {
		JSONObject json =new JSONObject();
		json.put("success", Boolean.FALSE);
		json.put("ecode", "e4000");
		return json.toJSONString();
	}
	
	private String getNextUrl(HttpServletRequest request) {
		String url = request.getParameter("target");
		if ( url == null || url.trim().length() == 0 ) {
			url = "";
		} else {
			try {
				url = URLDecoder.decode(url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// 발생할 일이 없음.
				logger.error("strange error", e);
				e.printStackTrace();
			}
		}
		logger.debug("NEXT URL : [" + url + "]");
		return url;
	}
	
	
}
