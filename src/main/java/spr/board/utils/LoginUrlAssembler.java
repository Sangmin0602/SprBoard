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
