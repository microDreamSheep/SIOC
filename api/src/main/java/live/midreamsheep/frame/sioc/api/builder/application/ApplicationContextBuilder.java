package live.midreamsheep.frame.sioc.api.builder.application;

import live.midreamsheep.frame.sioc.api.builder.bean.BeanHandlerFactory;
import live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.HandlerManager;
import lombok.Data;


@Data
public class ApplicationContextBuilder {
    /**
     * 用于获取的handle
     * */
    private BeanHandlerFactory classbeanHandlerFactory;
    /**
     * 用于注册bean
     * */
    private ApplicationContext applicationContext;
    /**
     * 获取BeanHandlers
     * */
    private BeanHandlerInjector beanHandlerInjector;

    public ApplicationContext build() {
        //获取类定义
        HandlerManager handlerManager = classbeanHandlerFactory.generateHandlerManager();
        //交由applicationContext注册
        return beanHandlerInjector.inject(applicationContext, handlerManager);
    }
}
