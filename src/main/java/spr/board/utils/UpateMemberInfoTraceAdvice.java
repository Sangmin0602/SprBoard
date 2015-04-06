package spr.board.utils;

public class UpateMemberInfoTraceAdvice {
//	public void traceReturn(String memberId, UpdateInfo info) {
//		System.out.println("[TA] 정보수정: 대상회원=%s, 수정정보=%s\n",
//				memberId, info);
//	}
}

//<bean id= "memberUpdateTraceAdvice"
//	class="net.madvirus.spring4.chap06.aop.UpdateMemberInfoTraceAdvice"/>
//
//<aop:config>
//	<aop:aspect id="memberUpdateTraceAspect" ref="memberUpdateTraceAdvice">
//		<aop:after-returning pointcut = "args(memberId, info)"
//			method = "traceReturn">
//	</aop:aspect>
//</aop:config>

//8.1 인자의 이름매핑 처리
//앞서 args() 명시자를 이용해서 메서드 호출시 사용된 인자를 파라미터로 전달받을 수 있따고 했다. args()명시자에
//지정한 이름과 Advice 구현 메서드의 파라미터 이름이 일치하는 지의 여부를 확인하는 순서는 다음과 같다.

//1. Advice 애노테이션 태그의 argsNames속성이나 Advice 설정 XML스키마의 arg-names 속성에서 명시한 파라미터이름을
//사용한다.
//2. argName 속성이 없을 경우, 컴파일할 때 생성되는 디버그 정보를 이용해서 파라미터 이름이 일치하는지의
//여부를 확인한다.
//3. 디버그 옵션이 없을 경우 파라미터 개수를 이용해서 일치 여부를 유추한다.

//argNames 속성은 Advice 구현 메서드의 파라미터 이름을 입력할 때 사용된다. 아래 코드는 사용 예를 보여주고있다.
//argnames속성은 모든 파라미터의 이름을 순서대로 표시해서 Pointcut표현식에서 사용된 이름이 몇번쨰 파라미터인지
//검색할 수 있도록 한다.

//@AfterReturning(pointcut="args(memberId,info)", argNames="memberId, info")
//public void traceReturn(String memberId, UpdateInfo info) {
//	System.out.printf("TA정보 수정 : 대상회원=%s, 수정정보=%s\n", memberId, info)''
//}
//
//만약 첫 번째 파라미터의 타입이 JoinPoint나 ProceedingJoinPoint라면, JoinPoint 타입의 파라미터 이름을 포함하지 않는다.
//@AfterReturning(pointcut="args(memberId,info)", argNames="memberId, info")
//public void traceReturn(JoinPoint joinPoint,String memberId, UpdateInfo info) {
//	System.out.printf("TA정보 수정 : 대상회원=%s, 수정정보=%s\n", memberId, info)''
//}

//XML 스키마를 사용하는 경우 다음과 같이 arg0names 속성을 이용해서 파라미터 이름을 지정한다.
//<aop:after-returing
//	pointcut =  "args(memberId, info)" method ="traceReturn"
//	returning = "result" arg-names = "joinPoint,memberId, info"/>
//




