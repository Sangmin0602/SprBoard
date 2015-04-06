package spr.board.utils.aop;

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
	
	/**
	 * 같은 클래스에 위치한 @Pointcut 메서드느'메서드이름'만 입력
	 * 같은 패키지에 위치한 @Pointcut 메서드는 '클래스단순이름.메서드이름' 을 입력
	 * 다른 패키지에 위치한 @Pointcut 메서드는 '완전한 클래스이름.메서드이름'을 입력
	 * 
	 * 
	 */
	@Pointcut("execution(* spr.board.web.postings.PostController.showWritingPage(..))")
	private void adminMethod() {}
	
	@Pointcut("execution(* spr.board.web.postings.PostController.listAll(..))")
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
    	/**
    	 * Around Advice를 사용할경우
    	 * ProceedingJoinPoint를 전달받고있다.ProceedingJoinPoint의 proceed()메서드를 호출하면 프록시대상 객체의
    	 * 실제 메서드를 호출하게 된다. 따라서 ProceedingJoinPoint.Proceed()메서드를 호출하기 전과 후에 알맞은 공통
    	 * 기능을 구현하면된다.
    	 * 
    	 */
    	Object result = joinPoint.proceed();
    	System.out.println("#### LoginAspect 끝 ####"); 
    	return result;
    }
}