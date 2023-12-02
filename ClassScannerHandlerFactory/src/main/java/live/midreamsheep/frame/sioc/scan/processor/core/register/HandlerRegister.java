package live.midreamsheep.frame.sioc.scan.processor.core.register;

import live.midreamsheep.frame.sioc.api.annotation.meta.HandlerFlag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于注册handler
 * */
@HandlerFlag
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HandlerRegister {
/**
     * 注册的handler
     * */
    Class<?>[] value();
}
