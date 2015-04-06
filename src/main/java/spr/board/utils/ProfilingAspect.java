package spr.board.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut 애노테이션을 이용해서 Pointcut을 정의하면, Advice관련 애노테이션에서 해당 메서드 이름을'
 * 이용해서 Pointcut을 사용할 수 있게 된다. 라인 15에서는 @Around애노테이션을 이용해서 Around Advice를 구현하고
 * 있는데, 이때 @Around애노테이션의 값으로 @Pointcut 애노테이션을 적용한 메서드의 이름을 지정한 것을 확인
 * 할 수 있다.
 * 
 * @Aspect 애노테이션을 이용해서 Aspect 클래스를 작성했다면, 다음으로  할 작업은 설정 정보에 Aspect 클래스를
 * 빈으로 등록하는 것이다.
 * 
 * <aop:aspectj-autoproxy/>
 * <!--Aspect 클래스를 빈으로 등록-->
 * <bean id = "performanceTraceAspect"
 * 	class="net.madvirus.spring4.chap06.aop.ProfilingAspect"/>
 * @author sangmin
 *
 */
@Aspect
public class ProfilingAspect {
	
//	@Pointcut("execution(pulblic * net.madvirus.spring4.chap06.board..*(..))")
//	private void profileTarget() {
//	}
//	
//	@Around("profileTarger()")
//	public Object trace(ProceedingJoinPoint joinPoint)throws Throwable {
//		String signatureString = joinPoint.getSignature().toShortString();
//		System.out.println(signatureString + "시작");
//		long start = System.currentTimeMillis();
//		try {
//			Object result = joinPoint.proceed();
//			return result;
//		} finally {
//			long finish = System.currentTimeMillis();
//			System.out.println(signatureString + "종료");
//			System.out.println(signatureString + "실행시간:" +
//			(finish - start)+"ms");
//		}
//	}
}
/**
 * @Configuration을 이용한 자바 코드 스프링 설정 방식을 사용할 경우, @EnableAspectJAutoProxy 애노테이션을 
 * 사용하면 @Aspect가 적용된 빈 객체를 Aspect로 사용할 수 있게 된다.
 * 
 * JoinPont를 파라미터로 사용할 때에는 반드시 첫 번째 파라미터로 지정해주어야한다. 두번째나 그 뒤에 위치한
 * 파라미터로 지정하면 익셉션을 발생 시킨다.
 * @author sangmin
 *
 */
//@Configuration
//@EnableAspectJAutoProxy
//class QuickStartConfig {
//	@Bean
//	public ProfilingAspect performanceTraceAspect() {
//		return new ProfilingAspect(); //@Aspect를 사용한 Aspect클래스
//	}
//	
//}