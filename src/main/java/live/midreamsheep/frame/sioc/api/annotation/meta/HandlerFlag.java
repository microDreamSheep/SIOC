package live.midreamsheep.frame.sioc.api.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于标记上下文处理器类<br/>
 * 比如BeanHandler，用于向上下文中新增一个Bean<br/>
 * 处理器类必须实现接口{@link live.midreamsheep.frame.sioc.api.handle.ContextHandler}<br/>
 * 处理的类必须用注解{@link live.midreamsheep.frame.sioc.api.annotation.meta.SIocFlag}标记<br/>
 * @author midreamsheep
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlerFlag {
}
