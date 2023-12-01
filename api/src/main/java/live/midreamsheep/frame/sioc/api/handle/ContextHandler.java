package live.midreamsheep.frame.sioc.api.handle;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;

public interface ContextHandler {
    /**
     * 用于获取相关依赖计算处理顺序
     * TODO
     * */
    Class<?>[] getDependencies();

    /**
     * 用于获取将会生成的被依赖项，用于计算处理顺序
     * TODO
     * */
    Class<?>[] toGenerate();
    /**
     * 具体处理，例如生成beanDefinition进行注入，进行切面等操作的处理
     * TODO
     * */
    void handle(ApplicationContext context);
    
}
