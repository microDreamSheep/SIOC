package live.midreamsheep.frame.sioc.api.handle;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;

/**
 * 上下文处理器，用于对上下文中的bean进行处理
 * 例如生成bean放入容器中
 * @see live.midreamsheep.frame.sioc.api.annotation.meta.HandlerFlag
 * @see live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector
 * @see live.midreamsheep.frame.sioc.api.builder.bean.BeanHandlerFactory
 * @author midreamsheep
 * */
public interface ContextHandler {
    /**
     * 用于获取相关依赖，用以计算处理顺序
     * */
    Class<?>[] getDependencies();

    /**
     * 用于获取将会生成的被依赖项，用于计算处理顺序
     * */
    Class<?>[] toGenerate();
    /**
     * 具体处理，例如生成beanDefinition进行注入，进行切面等操作的处理
     * */
    void handle(ApplicationContext context);
    /**
     * 获取handler的处理level
     * {@link HandlerLevel}
     * */
    HandlerLevel getHandlerLevel();
    
}
