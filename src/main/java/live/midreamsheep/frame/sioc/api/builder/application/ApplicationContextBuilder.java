package live.midreamsheep.frame.sioc.api.builder.application;

import live.midreamsheep.frame.sioc.api.builder.bean.BeanHandlerFactory;
import live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import lombok.Data;


/**
 * 用于构建ApplicationContext
 * 执行顺序：
 * 1.获取BeanHandlers {@link BeanHandlerFactory}
 * 2.执行BeanHandlers {@link BeanHandlerInjector}
 * 3.返回ApplicationContext {@link ApplicationContext}
 * @author midreamsheep
 * */
@Data
public class ApplicationContextBuilder {
    /**
     * 用于获取的handle
     * */
    private BeanHandlerFactory classbeanHandlerFactory;
    /**
     * 用于处理handler
     * */
    private BeanHandlerInjector beanHandlerInjector;
    /**
     * 用于管理bean
     * 用户可自行传入自定义的ApplicationContext
     * */
    private ApplicationContext applicationContext;

    public ApplicationContext build() {
        return beanHandlerInjector.inject(applicationContext,classbeanHandlerFactory.generateHandlerManager());
    }
}
