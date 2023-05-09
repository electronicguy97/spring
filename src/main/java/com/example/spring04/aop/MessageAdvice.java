package com.example.spring04.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect // 공통업무 지원 클래스
public class MessageAdvice {
// MessageService로 시작하는 클래스의 모든 함수 실행 전에 호출되는 코드 
	@Before("execution(* com.example.spring04.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("class:" + jp.getSignature());
		System.out.println("method:" + jp.getSignature().getName());
		System.out.println("args:" + Arrays.toString(jp.getArgs()));
	}
//MessageService로 시작하는 클래스의 모든 함수 실행 전후에 호출되는 코드 
	@Around("execution(* com.example.spring04.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed(); // 메인 함수 실행 
		long end = System.currentTimeMillis();
		System.out.println(pjp.getSignature().getName() + ":" + (end - start));
		System.out.println("==============");
		return result;
	}
}
