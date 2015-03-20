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
//	@After("within(spr.board.dao.*)")
//	@After("target(spr.board.dao.IDao)")
	@After("@within(org.springframework.stereotype.Repository)")
	public void log(JoinPoint point) {
		logger.debug("====================================================");
		logger.debug(point.getSignature().getName() + "@After Dao Request");
		logger.debug("====================================================");
	}
}
