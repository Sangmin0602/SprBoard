package spr.board.web.postings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spr.board.model.PostVO;
import spr.board.model.UserVO;

@Controller
public class PostController {
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/postings", method=RequestMethod.GET)
	public String listAll(Model model) {
		logger.debug("요청: " + "/postings");
		List<PostVO> posts = service.findAllPosts();
		
		model.addAttribute("allPosts", posts);
		
		return "list-postings" ;
	}
	
	@RequestMapping(value="/postings/{pid:[0-9]+}", method=RequestMethod.GET)
	public String getPostBySeq(Model model, @PathVariable String pid) {
		logger.debug("요청: " + "/postings" + "/" + pid);
		PostVO post = service.findBySeq ( pid );
		model.addAttribute("post", post);
		return "view-post";
	}
	
	@RequestMapping(value="/postings/write", method=RequestMethod.GET)
	public String showWritingPage() {
		return "writing";
	}
	
	@RequestMapping(value="/postings/write.json", method=RequestMethod.POST)
	public String insertPost(@RequestParam String title, @RequestParam String content,
			HttpServletRequest request) {
		logger.debug("[NEW POST]" + title + ":" + content);
		HttpSession session = request.getSession(false);
		UserVO user = (UserVO) session.getAttribute("user");
		PostVO post = service.insertPost(title, content, user.getSeq());
		String nextUrl = "/postings/" + post.getSeq();
		
		JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		json.put("nextUrl", nextUrl);
		request.setAttribute("json", json.toJSONString());
		
		return "json/json-writer";
	}
}
