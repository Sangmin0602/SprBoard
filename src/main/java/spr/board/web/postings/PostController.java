package spr.board.web.postings;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import spr.board.model.BoardDataBean;
import spr.board.model.PostVO;
import spr.board.model.UserVO;

@Controller
public class PostController {
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	private PostService service;

	@RequestMapping(value="/postings", method=RequestMethod.GET)
	public String listAll(HttpServletRequest request, HttpServletResponse response, Model model) {
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
	public String showWritingPage(HttpServletRequest request, HttpServletResponse response) {
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

	@RequestMapping(value="/postings/list", method=RequestMethod.GET)
	public String insertPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum");//페이지 번호

		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10;//한 페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;//한 페이지의 시작글 번호
		int endRow = currentPage * pageSize;//한 페이지의 마지막 글 번호
		int count = 0; // 전체 글의 수
		int number=0; // 글 목록에 표시할 글번호 설정

		List<BoardDataBean> articleList = null;
		// mybatis에서 PostingVO.xml에서 count가져올떄 resultMap사용어떻게 하는지
		count = service.getArticleCount();//전체 글의수

		if (count > 0) {
			Object getArticleCount;
			articleList = service.getArticles(startRow, pageSize);//현재 페이지에 해당하는 글 목록
		} else {
			articleList = Collections.EMPTY_LIST;
		}

		number=count-(currentPage-1)*pageSize;//글목록에 표시할 글번호
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "list";//해당 뷰
	}

	@RequestMapping(value="/postings/writeForm", method=RequestMethod.GET)
	public String writeForm(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num=0,ref=1,re_step=0,re_level=0;  
		try{  
			if(request.getParameter("num")!=null){
				num=Integer.parseInt(request.getParameter("num"));
				ref=Integer.parseInt(request.getParameter("ref"));
				re_step=Integer.parseInt(request.getParameter("re_step"));
				re_level=Integer.parseInt(request.getParameter("re_level"));
			}
		}catch(Exception e){e.printStackTrace();}
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("re_step", new Integer(re_step));
		request.setAttribute("re_level", new Integer(re_level));
		return "writeForm";//해당 뷰
	}

	@RequestMapping(value="/postings/writePro", method=RequestMethod.POST)
	public void writePro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//페이지 이동이렇게 해야하는지
		request.setCharacterEncoding("euc-kr");

		BoardDataBean article = new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setPasswd(request.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		article.setContent(request.getParameter("content"));
		article.setIp(request.getRemoteAddr());

		service.insertArticle(article);

		response.sendRedirect("list");
	}
	
	@RequestMapping(value="/postings/content", method=RequestMethod.GET)
	public String content(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="num", required=true) int num, 
			@RequestParam(value="num", required=true) String pageNum) throws Throwable {
		//http://localhost:8080/postings/content?num=4&pageNum=1 이렇게 보낼때 어떻게 받아야할지../
		//int num = Integer.parseInt(request.getParameter("num"));//해당 글 번호
        //String pageNum = request.getParameter("pageNum");//해당 페이지 번호

        BoardDataBean article =  service.getArticle(num);//해당 글 번호에 대한 해당 레코드
  
        //해당 뷰에서 사용할 속성
        request.setAttribute("num", new Integer(num));
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("article", article);
        
        return "content";//해당 뷰
	}
	
	@RequestMapping(value="/postings/updateForm", method=RequestMethod.GET)
	public String update(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="num", required=true) int num, 
			@RequestParam(value="pageNum", required=true) String pageNum) throws Throwable {
        BoardDataBean article =  service.updateGetArticle(num); //해당 글번호에 대한 레코드를 가져온다.

		//해당 뷰에서 사용할 속성
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("article", article);

        return "updateForm";//해당 뷰
	}
	
	@RequestMapping(value="/postings/updatePro", method=RequestMethod.POST)
	public void updatePro(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="num", required=true) int num, 
			@RequestParam(value="pageNum", required=true) String pageNum) throws Throwable {
        
		request.setCharacterEncoding("euc-kr");

		BoardDataBean article = new BoardDataBean();
        article.setNum(Integer.parseInt(request.getParameter("num")));
        article.setWriter(request.getParameter("writer"));
        article.setEmail(request.getParameter("email"));
        article.setSubject(request.getParameter("subject"));
        article.setContent(request.getParameter("content"));
        article.setPasswd(request.getParameter("passwd"));
	    
        service.updateArticle(article);

        request.setAttribute("pageNum", new Integer(pageNum));
        
        response.sendRedirect("list");
	}
	@RequestMapping(value="/postings/deleteForm", method=RequestMethod.GET)
	public String deleteFrom(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="num", required=true) int num, 
			@RequestParam(value="pageNum", required=true) String pageNum) throws Throwable {
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
	
		return "deleteForm";
	}
	
	@RequestMapping(value="/postings/deletePro", method=RequestMethod.POST)
	public void deletePro(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="pageNum", required=true) String pageNum) throws Throwable {
		request.setAttribute("pageNum", new Integer(pageNum));
		
		request.setCharacterEncoding("euc-kr");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		int check = service.deleteArticle(num, passwd);
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		response.sendRedirect("list");
	}
 }
