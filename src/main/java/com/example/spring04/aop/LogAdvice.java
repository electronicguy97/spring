package com.example.spring04.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // spring bean
@Aspect // 공통업무를 지원하는 클래스
public class LogAdvice {
	// 메인 액션 전후에 호출되는 함수 
	@Around("execution(* com.example.spring04.controller..*Controller.*(..)) || execution(* com.example.spring04.service..*Impl.*(..)) || execution(* com.example.spring04.model..*Impl.*(..)) ")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		long start=System.currentTimeMillis(); //현재 시각(타임스탬프)
		Object result=joinPoint.proceed(); //메인 액션 수행
		String type=joinPoint.getSignature().getDeclaringTypeName(); //클래스의 이름 
		String name="";
		if(type.indexOf("Controller") != -1) {
			name="Controller\t: ";
		}else if(type.indexOf("Service") != -1) {
			name="Service\t: ";
		}else if(type.indexOf("DAO") != -1) {
			name="DAO\t: ";
		}
		System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()"); //함수
		System.out.println(Arrays.toString(joinPoint.getArgs()));  //매개변수
		long end=System.currentTimeMillis(); 
		long time=end - start;
		System.out.println("실행시간:"+time);
		return result;
	}
}
