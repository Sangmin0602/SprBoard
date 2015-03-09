package spr.board.web.postings;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spr.board.model.PostVO;
import spr.board.mybatis.IPostDao;

@Controller
public class PostController {
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private IPostDao postDao;
	
	@RequestMapping(value="/postings", method=RequestMethod.GET)
	public String listAll(Model model) {
		logger.debug("ø‰√ª url : " + "/postings");
		List<PostVO> posts = postDao.findAll();
		
		model.addAttribute("allPosts", posts);
		
		return "postings" ;
	}
}
