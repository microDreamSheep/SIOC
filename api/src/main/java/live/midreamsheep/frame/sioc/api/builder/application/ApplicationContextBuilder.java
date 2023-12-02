package live.midreamsheep.frame.sioc.api.builder.application;

import live.midreamsheep.frame.sioc.api.builder.application.sort.DependenciesSorter;
import live.midreamsheep.frame.sioc.api.builder.application.sort.core.CoreDependenciesSorter;
import live.midreamsheep.frame.sioc.api.builder.bean.BeanHandlerFactory;
import live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;
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

    /**
     * 策略设计模式，用于处理依赖关系
     * */
    private DependenciesSorter dependenciesSorter = new CoreDependenciesSorter();

    public ApplicationContext build() {
        //获取类定义
        HandlerSet handlerManager = classbeanHandlerFactory.generateHandlerManager();
        //交由applicationContext注册
        return beanHandlerInjector.inject(applicationContext, dependenciesSorter.sort(handlerManager));
    }
}
