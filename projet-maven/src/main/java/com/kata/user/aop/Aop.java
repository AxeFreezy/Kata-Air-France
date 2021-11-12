package com.kata.user.aop;

import java.util.logging.Logger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 * @author Axel Aboyeji
 *
 */
@Aspect
@Component
public class Aop {
	private final Logger LOGGER = Logger.getLogger(String.valueOf(Aop.class));

	
    /**
     * method that calculate the time of execution of a method
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("userPointCut()")
    public Object aop(final ProceedingJoinPoint joinPoint) throws Throwable{

        Object returnvalue;
        StopWatch stopWatch = new StopWatch(getClass().getName());
        try{
        	stopWatch.start(joinPoint.toString());
            returnvalue = joinPoint.proceed();

        }finally {
        	stopWatch.stop();
            LOGGER.info("Time of execution : "+ stopWatch.prettyPrint());
        }
        return returnvalue;
    }
    
    
    /**
     * method that define pointCut that satisfy the conditions to activate the aspect
     */
    @Pointcut("execution(* com.kata.user.impl.*UserServiceImpl.*(..))")
    public void userPointCut(){

    }
}
