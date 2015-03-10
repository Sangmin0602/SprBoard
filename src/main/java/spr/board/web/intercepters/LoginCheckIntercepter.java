package spr.board.web.intercepters;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spr.board.model.UserVO;
import spr.board.utils.LoginUrlAssembler;

/**
 * request의 접근 uri에 대해 로그인 여부를 확인함.
 * 
 * @author chminseo
 *
 */
public class LoginCheckIntercepter extends HandlerInterceptorAdapter {
	private enum ReqType {NORMAL, JSON};
	
	final static Logger logger = LoggerFactory.getLogger(LoginCheckIntercepter.class);
	
	@Autowired
	private LoginUrlAssembler urlMaker ;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if ( session == null ) {
			doRedirection(request, response);
			return false;
		}
		
		UserVO user = UserVO.class.cast ( session.getAttribute("user"));
		
		if ( user == null ) {
			doRedirection(request, response);
			return false;
		}
		
		return true;
	}
	
	private void doRedirection(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ReqType rType = getRequestType(request);
		if ( rType == ReqType.NORMAL ) {
			String url = urlMaker.createLoginUrl(request);
			response.sendRedirect(url);
		} else {
			throw new RuntimeException("로그인이 필요한 json 요청에 대한 응답 처리 - 구현 안했음");
		}
	}

	/**
	 * json 요청인지 일반 text/html 요청인지 판단함(응답이 달라지므로)
	 * @param request
	 * @return
	 */
	private ReqType getRequestType(HttpServletRequest request) {
		String uri = request.getRequestURI().toLowerCase();
		return uri.endsWith(".json") ? ReqType.JSON : ReqType.NORMAL; 
	}
}