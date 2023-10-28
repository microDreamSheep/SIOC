package live.midreamsheep.frame.sioc.api.annotation.injector;

import live.midreamsheep.frame.sioc.entity.bean.meta.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanScope {
    Scope value() default Scope.SINGLETON;
}
