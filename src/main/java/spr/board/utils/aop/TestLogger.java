package spr.board.utils.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spr.board.BoardException;
import spr.board.model.Member;
import spr.board.web.postings.PostController;

/**
 * Repository annotation이 붙은 모든 클래스의 메소드 호출에 advise를 붙임.
 * @author chminseo
 *
 */
@Aspect
public class TestLogger {

	private Logger logger = LoggerFactory.getLogger(PostController.class);
	
//	@Before("within(spr.board.dao.*)")
//	@Before("target(spr.board.dao.IDao)")
	@Before("@within(org.springframework.stereotype.Repository)")
	public void beforelog(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug("!!!!" + point.getSignature().getName() + "@Before Dao Request");
		logger.debug("====================================================");
	}
	
	//@Before("@within(org.springframework.stereotype.Repository)")
	@Before("execution(net.madvirus.spring4.chap06.aop.Public Pointcut.publicMethod())")
	public void beforelog() {
		logger.debug("====================================================");
		logger.debug("@Before Dao Request");
		logger.debug("====================================================");
	}
	
	@AfterReturning("net.madvirus.spring4.chap06.aop.Public Pointcut.publicMethod()")
	public void afterReturing() {
		System.out.println("메서드 실행후 수행");
	}
	
	@AfterReturning(
		pointcut ="net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
		returning = "ret")
	public void afterReturning(Object ret) {
		System.out.println("메서드실행후 후처리 수행, 리턴값 =" + ret);
	}
	
	@AfterReturning(
			pointcut ="net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
			returning = "ret")
	public void afterReturning(Member ret) {
		System.out.println("대상 객체의 메서드가 정상적으로 실행된 이후에 적용할 기능 구현" + ret);
	}
	
	@AfterReturning(
			pointcut ="net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
			returning = "ret")
	public void afterReturning(JoinPoint joinPoint, Member ret) {
		System.out.println("대상 객체의 메서드가 정상적으로 실행된 이후에 적용할 기능 구현" + ret);
	}
	
	@AfterThrowing("net.madvirus.spring4.chap06.PublicPointcut.publicMethod()")
	public void afterThrowing() {
		System.out.println("메서드 실행 중 익셉션발생");
	}
	@AfterThrowing(
			pointcut = "net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
			throwing = "ex")
	public void afterThrowing(Throwable ex) {
		System.out.println("메서드 실행 중 익셉션 발생, 익셉션=" + ex.getMessage());
	}
	/**
	 * 만약 특정 타입의 익셉션에 대해서만 처리하고 싶다면, Throwable이나 Exception이 아니라 처리하고 싶은 타입을
	 * 파라미터로 지정하면 된다. 예를 들어, 다음 코드의 경우는 발생된 익셉션이 ArticleNotFoundException인 경우에만 호출된다.
	 * @param point
	 */
	@AfterThrowing(
			pointcut = "net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
			throwing = "ex")
	public void afterThrowing(BoardException ex) {
		System.out.println("메서드 실행 중 익셉션 발생, 익셉션=" + ex.getMessage());
	}
	/**
	 * 대상 객체 및 호출되는메서드에 대한 정보나 전달되는파라미터에 대한 정보가 필요한 경우 다음과 같이
	 * org.aspectj.lang.JoinPoint를 파라미터로 추가한다.
	 * @param point
	 */
	@AfterThrowing(
			pointcut = "net.madvirus.spring4.chap06.aop.PublicPointcut.publicMethod()",
			throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		System.out.println("메서드 실행 중 익셉션 발생, 익셉션=" + ex.getMessage());
	}
	
	/**
	 * 메서드가 정상적으로 실행되는지 또는 익셉션을 발생시키는지 여부에 상관없이  적용되는
	 * Advice를 정의한다. try-cathc-finally 블록에서 finally 블록과 비슷하다.
	 * @param point
	 */
//	@After("within(spr.board.dao.*)")
//	@After("target(spr.board.dao.IDao)")
	@After("@within(org.springframework.stereotype.Repository)")
	public void log(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug(point.getSignature().getName() + "@After Dao Request");
		logger.debug("====================================================");
	}
}
