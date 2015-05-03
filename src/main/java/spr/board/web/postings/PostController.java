package spr.board.web.postings;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spr.board.model.BoardDataBean;
import spr.board.model.ConvertToXmlTest;
import spr.board.model.ConvertToXmlTestList;
import spr.board.model.ConvertToXmlTestList2;
import spr.board.model.Member;
import spr.board.model.PhoneDTO;
import spr.board.model.PhoneVO;
import spr.board.model.PostVO;
import spr.board.model.UserVO;
import spr.board.utils.BoardUtils;
import spr.board.utils.Pagenation;
import spr.board.utils.download.ImageUtils;
import spr.board.utils.download.ThumbnailUtil;
import spr.board.utils.excel.PageRank;
import spr.board.utils.mail.MailServiceImpl;

@Controller
public class PostController {
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	private PostService service;
	
	@Autowired
	private MailServiceImpl mailService;

	@RequestMapping(value="/postings", method=RequestMethod.GET)
	public String listAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("요청: " + "/postings");
		List<PostVO> posts = service.findAllPosts();

		model.addAttribute("allPosts", posts);

		return "list-postings" ;
	}
	
	@RequestMapping(value="/postings2", method=RequestMethod.GET)
	public String listByJqGrid(HttpServletRequest request, HttpServletResponse response) {
		return "list-by-jqgrid" ;
	}
	
	
	@RequestMapping(value="/postings.json", 
			method=RequestMethod.GET, 
			produces="application/json; charset=utf-8")
	@ResponseBody
	public String listPosts(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		// _search=false&nd=1430198053012&rows=20&page=1&sidx=&sord=asc
		String paramRows = request.getParameter("rows");
		String paramPageNum = request.getParameter("page");
		int rows = BoardUtils.convertToInt(paramRows, 10);
		int curPage = BoardUtils.convertToInt(paramPageNum, 1); 
		int totalRows = service.countAllPostings();
		
		Pagenation pgn = new Pagenation(curPage-1, rows, totalRows);
		List<PostVO> posts = service.findByRange(pgn.getOffset(), pgn.getRPP());
		
		JSONObject json = new JSONObject();
		
		json.put("page", curPage);
		json.put("total", 
				pgn.getTotalPage());
		json.put("records", totalRows);
		JSONArray arr = new JSONArray();
		
		for(int i = 0 ; i < posts.size() ; i++) {
			PostVO aPost = posts.get(i);
			JSONObject tmp = new JSONObject();
			tmp.put("seq", aPost.getSeq());
			tmp.put("title", aPost.getTitle());
			tmp.put("writer", aPost.getWriter().getNickName());
			tmp.put("when_created", aPost.getWhenCreated());
			tmp.put("deleted", aPost.isDeleted());
			arr.add(tmp);
		}	
		json.put("rows", arr);
		return  json.toJSONString();
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

	@RequestMapping(value="/postings/{pid:[0-9]+}", method=RequestMethod.DELETE)
	public String deletePost(Model model) {

		return "";
	}
	@RequestMapping(value="/postings/write.json", method=RequestMethod.POST)
	public String insertPost(@RequestParam String title, @RequestParam String content,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("[NEW POST]" + title + ":" + content);

		HttpSession session = request.getSession(false);
		UserVO user = (UserVO) session.getAttribute("user");
		PostVO post = service.insertPost(title, content, user.getSeq());

		String nextUrl = "/postings/" + post.getSeq();

		JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		json.put("nextUrl", nextUrl);

		//		try {
		//			PrintWriter pw = response.getWriter();
		//			logger.info("JSON : " + json.toJSONString());
		//			pw.write(json.toJSONString());
		//			
		//			pw.flush();
		//			return null;
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

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
	/**
	 * fileUpload 처리
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/home/fileUpload", method = RequestMethod.POST)
	public String fileUpload( @RequestParam("file1") MultipartFile multipartFile, Model model ) throws IOException {

		if ( multipartFile == null) return "home";

		String fileExt = multipartFile.getOriginalFilename().substring( multipartFile.getOriginalFilename().lastIndexOf( ".") + 1, multipartFile.getOriginalFilename().length());

		// 넘어온 파일을 임시의 폴더에 둔다.
		// 임시 폴더는 C:\ 로 잡기로 한다.
		File uploadFile =  File.createTempFile( "c:\\", "." + fileExt);
		multipartFile.transferTo( uploadFile);

		File thumbnail =  File.createTempFile( "c:\\", "." + fileExt);


		// 이미지 파일만 썸네일 을 만든다.
		if ( ImageUtils.isImageFile ( fileExt))
		{
			ThumbnailUtil.makeThumbnail( uploadFile, thumbnail, 100, 100);
			String imageBase64 = ImageUtils.encodeToString( thumbnail, fileExt);
			model.addAttribute("imageBase64", "data:image/png;base64," + imageBase64);
		}

		model.addAttribute("targetFileInfo", multipartFile.getOriginalFilename());
		model.addAttribute("uploadFilePath", uploadFile.getAbsolutePath());


		return "home";

	}
	/**
	 * DownLoad 처리
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "postings/download", method = RequestMethod.GET)
	public ModelAndView down(HttpServletRequest request,@RequestParam(value="filepath", required=true) String filepath){
		//File file = new File("C:/Users/PT/","11.txt");
		File file = new File(filepath);

		request.setAttribute("fileName", "이름_재지정.txt");   //다운 받을 시 이름을 결정합니다. 빼게되면 기존에 저장된 이름으로 받습니다.  
		return new ModelAndView("fileDownloadView","fileDownload", file);
	}
	/**
	 * Mailing
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "postings/sendMail", method = RequestMethod.GET)
	public String sendMail(HttpServletRequest request){
		
		Member member = new Member();
		member.setEmail("sangmin0602@gmail.com");
		member.setId("sangmin0602@gmail.com");
		member.setName("sangmin0602@gmail.com");
		mailService.sendMail(member);
		
		return "home";
	}
	
//    @RequestMapping(value = "/postings/write", method = RequestMethod.POST)
//    public String wirteSubmit(PhoneDTO phoneDTO) {
//        return "writeSubmit";
//    }
    /**
     * EL태그처리 P 태그처리
     * @param phoneVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/postings/write", method = RequestMethod.POST)
    public String wirteSubmit(@ModelAttribute("p") PhoneDTO phoneDTO) {
        return "writeSubmit";
    }
    /**
     * EL태그처리
     * @param phoneVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/postings/writeList", method = RequestMethod.POST)
    public String wirteListSubmit(PhoneVO phoneVO, Model model) {
    	List<PhoneDTO> list = phoneVO.getPhoneItems();
    	model.addAttribute("article", list);
        return "writeListSubmit";
    }
    
	/**
	 * ConvertToXmlTest home.jsp참조
	 * @return
	 */
    @RequestMapping(value = "/postings/list.xml")
    @ResponseBody
    public ConvertToXmlTestList listXml() {
    	return getMessageList();
    }
	/**
	 * ConvertToXmlTest home.jsp참조
	 * @return
	 */
	@RequestMapping(value = "/postings/post.xml", method = RequestMethod.POST)
	@ResponseBody
	public ConvertToXmlTestList postXml(@RequestBody ConvertToXmlTestList messageList) {
		logger.debug(messageList.toString());
		List<ConvertToXmlTest> list = messageList.getMessages();
		return messageList;
	}
	
	/**
	 * ConvertToXmlTest home.jsp참조
	 * @return
	 */
	private ConvertToXmlTestList getMessageList() {
		List<ConvertToXmlTest> messages = Arrays.asList(
				new ConvertToXmlTest(1,"aaa",new Date()),
				new ConvertToXmlTest(1,"bbbb",new Date())
				);
		return new ConvertToXmlTestList(messages);
	}
	
	/**
	 * ConvertToXmlTest home.jsp참조
	 * @return
	 */
	@RequestMapping(value = "/postings/list.json")
	@ResponseBody
	public ConvertToXmlTestList2 listJson() {
		return getMessageList2();
	}

	private ConvertToXmlTestList2 getMessageList2() {
		List<ConvertToXmlTest> messages = Arrays.asList(
				new ConvertToXmlTest(1,"aaa",new Date()),
				new ConvertToXmlTest(1,"aaa",new Date())
				);

		return new ConvertToXmlTestList2(messages);
	}
	/**
	 * 엑셀 다운로드 
	 * Guide
	 * http://poi.apache.org/spreadsheet/quick-guide.html#NewWorkbook
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/postings/pagestat/rank")
	public String pageRank(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageRank";
	}
	/**
	 * PDF 다운로드
	 * @param model
	 * @return
	 */
	@RequestMapping("/postings/pagestat/rankreport")
	public String pageRankReport(Model model) {
		List<PageRank> pageRanks = Arrays.asList(
				new PageRank(1, "/board/humor/1011"),
				new PageRank(2, "/board/notice/12"),
				new PageRank(3, "/board/phone/190")
				);
		model.addAttribute("pageRankList", pageRanks);
		return "pageReport";
	}

	/**
	 * Validator 테스트
	 
	@RequestMapping("/member/regist") 
	public String regist(@ModelAttribute("memberInfo") MemberRegistRequest memReqReq,
							BindingResult bindingResult) {
		//Validator() 호출
		new MemberRegistValidator().validate(memReqReq, bindingResult);
		if(bindingResult.hasErrors()) {
			return "";
		}
		//memberService.registNewMember(memRegReq);
		return "member/registered";
	}
	*/
	
	/**
	 * REST API 테스트 (웹서비스) : 이기종 통신시 WSDL(XML)로 처리
	 * URI로 처리 확장자가 없음
	 * GET,POST,DELETE, PUT
	 */
	
	/**
	 * 웹소켓 
	 * 
	 */
	
	/**
	 * 
	 * 트랜잭션 처리
	 * 
	 */
}
