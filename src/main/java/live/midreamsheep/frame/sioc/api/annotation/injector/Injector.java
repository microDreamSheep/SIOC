package live.midreamsheep.frame.sioc.api.annotation.injector;

import live.midreamsheep.frame.sioc.entity.bean.meta.InjectType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Injector {
    /**
     * 注入的方式
     * */
    InjectType type() default InjectType.BY_NAME;

    /**
     * bean的名称
     * */
    String name() default "";
}
