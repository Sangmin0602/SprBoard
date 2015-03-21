package spr.board.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * request가 접근하는 uri가 로그인이 필요한 경우, 로그인 url뒤에 덧붙여서 로그인 성공시
 * 자동으로 redirect 하도록 지원함.
 *   
 * @author chminseo
 *
 */
public class LoginUrlAssembler {
	final private static Logger logger = LoggerFactory.getLogger(LoginUrlAssembler.class);
	
	final public static String TOKEN_CONTEXT_PATH = "{$ctxpath}";
	final public static String TOKEN_NEXT_URL = "{$nextUrl}";
	
	private String loginUrlTemplate ;

	public String getLoginUrlTemplate() {
		return loginUrlTemplate;
	}

	public void setLoginUrlTemplate(String loginUrlTemplate) {
		this.loginUrlTemplate = loginUrlTemplate;
	}
	
	/**
	 *     http://xxx.com/postings?id=393993
	 *     
	 *     물음표(?) 뒷 부분을 쿼리 스트링이라고 하는데, 이 부분을 login을 전담하는
	 *     uri의 쿼리 스트링으로 다시 넘기려면 특수문자를 표준에 맞게 인코딩해서 붙여줘야 합니다.
	 *     
	 *     이렇게 인코딩된 url을 target 의 값으로 붙여서 브라우저한테 redirect 응답(302응답)으로 보내줍니다.
	 *     
	 *     그리고 사용자가 로그인 화면에서 id와 password를 입력하고 확인을 누르면 LoginController가 실제
	 *     로그인 과정을 거쳐서 target의 값을 다시 디코딩한 후에 또다시 redirect응답(302응답)을 보내서
	 *     자동 로그인 페이지 이동 후 원래 페이지 이동 프로세스가 끝납니다.
	 *     
	 *     
	 * @param request
	 * @return
	 */
	public String createLoginUrl(HttpServletRequest request) {
		String encoding = "utf-8";
		String urlToGo = BoardUtils.parsePath(request);

		String queryPart = request.getQueryString();
		if ( queryPart != null) {
			urlToGo += "?" + queryPart ;
		}
		try {
			urlToGo = URLEncoder.encode(urlToGo, encoding);
		} catch (UnsupportedEncodingException e) {
			logger.error("invalid encoding [" + encoding + "]", e);
		}
		
		String login = loginUrlTemplate.replace(TOKEN_CONTEXT_PATH, request.getContextPath());
		login = login.replace(TOKEN_NEXT_URL, urlToGo);
		
		logger.debug("[LOGIN-REQUIRED]" + login);
		return login;
	}
	
	

}
