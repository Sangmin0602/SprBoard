package spr.board.utils.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import spr.board.model.Log;

@Aspect
public class LogAspect {
	
	@Autowired
	private LogService logBO;
	
	/**
	 * 비즈니스 논리 추가 방법을 좀 넣다 
	 */
	@Pointcut("execution(* spr.board.dao.IPostDao.*(..))")
	public void insertCell(){}
	
	/**
	 * 비즈니스 논리 방법 변경 좀 넣다 
	 */
	@Pointcut("execution(* com.hz.yiliao.bo.*.update(..))")
	public void updateCell(){}
	
	/**
	 * 비즈니스 논리 삭제 방법 좀 넣다 
	 */
	@Pointcut("execution(* com.hz.yiliao.bo.*.delete(..))")
	public void deleteCell(){}
	
	/**
	 * 작업 기록 (사후 통지) 추가 
	 * @param joinPoint
	 * @param rtv
	 */
	@SuppressWarnings("unused")
	@AfterReturning(value="insertCell()",argNames="rtv", returning="rtv")
	public void insertLog(JoinPoint joinPoint, Object rtv) throws Throwable{
		Long userId = 1L;
		
		if(userId == null){//관리원 로그인 없이  
			return ;
		}
		//판단 매개 변수  
		if(joinPoint.getArgs() == null){//인자가 없습니다.  
			return ;
		}
		//가져오는 방법을 명 
		String methodName = joinPoint.getSignature().getName();
		//내용 가져오는 조작
		String opContent = optionContent(joinPoint.getArgs(),methodName);
		
		Log log = new Log();
		log.setUserId(userId);
		log.setContent(opContent);
		log.setOperation("추가");
		logBO.insertLog(log);
	}
	
	 /** 
     * 관리자에게 작업 로그 (사후 통지) 
     * @param joinPoint 
     * @param rtv 
     * @throws Throwable 
     */  
    @SuppressWarnings("unused")
	@AfterReturning(value="updateCell()", argNames="rtv", returning="rtv")  
    public void updateLog(JoinPoint joinPoint, Object rtv) throws Throwable{  
    	Long userId = 1L;
          
        if(userId == null){//로그인 없이  
            return;  
        }  
        //판단 매개 변수  
        if(joinPoint.getArgs() == null){//인자가 없습니다.  
            return;  
        }  
        //가져오는 방법을 명  
        String methodName = joinPoint.getSignature().getName();  
        //내용 가져오는 조작  
        String opContent = optionContent(joinPoint.getArgs(), methodName);  
          
        //생성 대상  
        Log log = new Log();  
		log.setUserId(userId);
		log.setContent(opContent);
        log.setOperation("수정"); // 조작  
        logBO.insertLog(log);
    }  
    
    /** 
     * 삭제 작업이
     * @param joinPoint 
     * @param rtv 
     */  
    @SuppressWarnings("unused")
	@AfterReturning(value="deleteCell()",argNames="rtv", returning="rtv")
    public void deleteLog(JoinPoint joinPoint, Object rtv) throws Throwable{
    	Long userId = 1L;
    	if(userId == null){//로그인 없이  
            return;  
        }  
        //판단 매개 변수  
        if(joinPoint.getArgs() == null){//인자가 없습니다.  
            return;  
        }  
        //가져오는 방법을 명  
        String methodName = joinPoint.getSignature().getName();  
        
        StringBuffer rs = new StringBuffer();
		rs.append(methodName);
		String className = null;
		for(Object info : joinPoint.getArgs()){
        	//개체 유형 가져오기
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[매개 변수 1 종류: "+ className + ", 값:(id:" + joinPoint.getArgs()[0]+")");
        }
          
        //생성 대상  
        Log log = new Log();  
		log.setUserId(userId);
		log.setContent(rs.toString());
        log.setOperation("삭제"); // 조작  
        logBO.insertLog(log);
    }
	
	/**
	 * 자바 반사 온 가져오기 사용 의해 차단 방법 (insert, update) 변수,  
     * 매개 변수 값 장미 작업 내용을 위한 것이다
	 * @param args
	 * @param mName
	 * @return
	 */
	public String optionContent(Object[] args, String mName){
		if(args == null){
			return null;
		}
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		//매개 변수 대상 사이를 옮겨다니기 
		for(Object info : args){
			//개체 유형 가져오기
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[인자 "+index+ "종류:" + className +", 값:");
			//대상 모든 방법을 가져오기
			Method[] methods = info.getClass().getDeclaredMethods();
			// 사이를 옮겨다니기 방법을 판단 get 방법 
			for(Method method : methods){
				String methodName = method.getName();
				// 판단 혹시 get 방법
				if(methodName.indexOf("get") == -1){//get 방법이 아니다. 
					continue;//처리할 수 없다
				}
				Object rsValue = null;
				try{
					// 호출 get 방법을 가져오기 복귀 값
					rsValue = method.invoke(info);
				}catch (Exception e) {
					continue;
				}
				//는 값 가입 내용 중
				rs.append("(" + methodName+ ":" + rsValue + ")");
			}
			rs.append("]");
			index ++;
		}
		return rs.toString();
	}
	
}
