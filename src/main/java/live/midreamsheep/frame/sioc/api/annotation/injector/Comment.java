package live.midreamsheep.frame.sioc.api.annotation.injector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记将要注入的bean
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {
    /**
     * bean的名称
     * */
    String value() default "";
}
