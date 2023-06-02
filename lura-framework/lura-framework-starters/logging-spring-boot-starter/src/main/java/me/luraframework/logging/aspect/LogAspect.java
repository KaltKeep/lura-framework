package me.luraframework.logging.aspect;

import com.cait.samps.core.util.RequestHolder;
import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author: LiuRan
 * @create: 2022-10-25 15:36
 * @description: 操作记录拦截类
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final LogService logService;

    @Pointcut("@annotation(com.cait.samps.logging.annotation.Log)")
    public void logPointcut() {

    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object result = joinPoint.proceed();
        stopwatch.stop();

        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save(joinPoint, getBrowser(request), getIp(request), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }


    private String getIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    private String getBrowser(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
