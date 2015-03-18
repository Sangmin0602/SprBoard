package spr.board.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import spr.board.model.UserVO;

@Aspect
public class LogingAspect {
	@Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
	private void adminMethod() {}
	
	@Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
	private void bbsMethod(){}
	
	@Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
	private void commonMethod() {}
	
    @Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
    private void onmAdministratorMethod(){}
     
    @Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
    private void onmMembershipMethod(){}
     
    @Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
    private void onmReportMethod(){}
         
    private String[] access_url = {       };
   
    @Around(value ="adminMethod() || bbsMethod()  || commonMethod() "
    		+ "|| onmAdministratorMethod() || onmMembershipMethod() || onmReportMethod()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
 
    	System.out.println("#### LoginAspect 시작 ####");     
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