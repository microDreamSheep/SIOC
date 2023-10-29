package live.midreamsheep.frame.sioc.pojo;

import live.midreamsheep.frame.sioc.api.annotation.injector.Comment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记将要注入的bean
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Comment
public @interface Service {
    /**
     * bean的名称
     * */
    String value() default "";
}
