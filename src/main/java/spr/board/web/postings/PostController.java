package spr.board.web.postings;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spr.board.model.PostVO;

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
}
