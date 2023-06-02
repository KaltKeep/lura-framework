package me.luraframework.logging.oprationlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 操作日志注解
 * @author ranliu
 * @date 10/25/22 3:32 PM
**/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value();
}
