package me.luraframework.logging.aspect;

import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@Aspect
public class LogAspect {

    private final LogService logService = new LogService();

    @Pointcut("@annotation(com.caacitc.samps.logging.annotation.Log)")
    public void logPointcut() {

    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object result = joinPoint.proceed();
        stopwatch.stop();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
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
