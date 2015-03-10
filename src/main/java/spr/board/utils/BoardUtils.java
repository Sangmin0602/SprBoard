package spr.board.utils;

import javax.servlet.http.HttpServletRequest;

public class BoardUtils {
	
	/**
	 * 요청 uri에서 context path 를 제외한 나머지 부분을 반환함.
	 * 
	 * ex) uri = "/root/postings/23993" 이고 contextpath가 "/root"인 경우 "/postings/23992"을 반환
	 *     
	 * @param request
	 * @return context path를 제외한 나머지 uri 부분들을 반환.
	 */
	public static String parsePath ( HttpServletRequest request )  {
		String ctxpath = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(ctxpath.length());
		
		return path ;
	}

}
