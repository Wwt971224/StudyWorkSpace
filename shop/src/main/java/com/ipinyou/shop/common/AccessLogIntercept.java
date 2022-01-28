package com.ipinyou.shop.common;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * controller执行拦截器
 */
@Slf4j
@Aspect
@Component
public class AccessLogIntercept {

    @Pointcut("execution(public * com.ipinyou.shop.controller..*.*(..))")
    public void recordExecute() {
    }

    @Around("recordExecute()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Signature signature = pjp.getSignature();

        Object obj = null;
        try {
            stopWatch.start(signature.toShortString());
            before(pjp);
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("{} execute error", signature.toShortString());
            throw throwable;
        } finally {
            log.info("{} execute result data:{}", signature.toShortString(), JSON.toJSONString(obj));
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }

        return obj;
    }

    private void before(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        List<Object> objectList = new ArrayList<>();
        Object[] args = pjp.getArgs();
        if (Objects.nonNull(args)) {
            for (Object item : args) {
                if (Objects.nonNull(item)) {
                    if (item instanceof HttpServletResponse || item instanceof HttpSession) {
                        continue;
                    }
                    if (item instanceof HttpServletRequest) {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) item;
                        log.info(httpServletRequest.getMethod());
                        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
                        if (Objects.nonNull(parameterNames)) {
                            while (parameterNames.hasMoreElements()) {
                                String name = parameterNames.nextElement();
                                String value = httpServletRequest.getParameter(name);
                                objectList.add(name + ":" + value);
                            }
                        }
                    } else {
                        if (item.getClass().isArray()) {
                            handleArray(item, objectList);
                        } else {
                            objectList.add(JSON.toJSONString(item));
                        }
                    }
                }
            }
        }

        log.info("URL: {}, ARGS: {},  CLASS_METHOD: {}", request.getRequestURL(),
                Arrays.toString(objectList.toArray()), (pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()));
    }

    private void handleArray(Object item, List<Object> objectList) {
        if (item instanceof Object[]) {
            objectList.add(Arrays.toString((Object[]) item));
        } else if (item instanceof boolean[]) {
            objectList.add(Arrays.toString((boolean[]) item));
        } else if (item instanceof byte[]) {
            objectList.add(Arrays.toString((byte[]) item));
        } else if (item instanceof char[]) {
            objectList.add(Arrays.toString((char[]) item));
        } else if (item instanceof double[]) {
            objectList.add(Arrays.toString((double[]) item));
        } else if (item instanceof float[]) {
            objectList.add(Arrays.toString((float[]) item));
        } else if (item instanceof int[]) {
            objectList.add(Arrays.toString((int[]) item));
        } else if (item instanceof long[]) {
            objectList.add(Arrays.toString((long[]) item));
        } else if (item instanceof short[]) {
            objectList.add(Arrays.toString((short[]) item));
        }
    }
}
