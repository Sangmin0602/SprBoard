package spr.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spr.board.model.UserVO;

public class LogingAspect {
	private Logger logger = LoggerFactory.getLogger(LogingAspect.class);
	
	@Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
	private void adminMethod() {}
	
	@Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
	private void bbsMethod(){}
	
	@Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
	private void commonMethod() {}
	
    @Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
    private void onmAdministratorMethod(){}
     
    @Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
    private void onmMembershipMethod(){}
     
    @Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
    private void onmReportMethod(){}
         
    private String[] access_url = {       };
   
    @Around(value ="adminMethod() || bbsMethod()  || commonMethod() "
    		+ "|| onmAdministratorMethod() || onmMembershipMethod() || onmReportMethod()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
 
    	logger.info("#### LoginAspect 시작 ####");     
    	HttpServletRequest request = null;
    	HttpServletResponse response = null;

    	for(Object o : joinPoint.getArgs()){
    		if(o instanceof HttpServletRequest) {
    			request = (HttpServletRequest)o;
    		}
    		if(o instanceof HttpServletResponse) {
    			response = (HttpServletResponse) o;
    		}
    	}
    	try {
    		HttpSession session = request.getSession(false);
    		//session.setAttribute("user", "test");
			UserVO loginId = (UserVO) session.getAttribute("user");
			String userEnterType = (String) session.getAttribute("UserEnterType");
			if (loginId == null || "".equals(loginId)) {
				System.out.println("loginId=="+loginId);
				throw new RuntimeException("먼저 로그인을 하셔야 합니다.");
			}
    			
    	} catch(Exception e) {
    		throw new RuntimeException("먼저 로그인을 하셔야 합니다.");
    	}
    	Object result = joinPoint.proceed();
    	System.out.println("#### LoginAspect 끝 ####"); 
    	return result;
    }
}