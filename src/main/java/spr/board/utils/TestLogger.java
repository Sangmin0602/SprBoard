package spr.board.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spr.board.web.postings.PostController;

@Aspect
public class TestLogger {
	//private Log log = LogFactory.getLog(getClass());
	//private Log log = LogFactory.getLog(TestLogger.class);
	private Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Before("execution(* spr.board.dao.IPostDao.*(..))")
	public void beforelog(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug("!!!!" + point.getSignature().getName() + "@Before IPostDao Method");
		logger.debug("====================================================");
	}
	@Before("execution(* spr.board.dao.IUserDao.*(..))")
	public void beforeboardLog(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug(point.getSignature().getName() + "@Before IUserDao Method");
		logger.debug("====================================================");
	}
	
	@After("execution(* spr.board.dao.IPostDao.*(..))")
	public void log(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug(point.getSignature().getName() + "@After IPostDao Method");
		logger.debug("====================================================");
	}
	@After("execution(* spr.board.dao.IUserDao.*(..))")
	public void boardLog(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug(point.getSignature().getName() + "@After IUserDao  Method");
		logger.debug("====================================================");
	}
}
