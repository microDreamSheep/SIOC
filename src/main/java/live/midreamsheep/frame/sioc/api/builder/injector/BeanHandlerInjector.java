package live.midreamsheep.frame.sioc.api.builder.injector;

import live.midreamsheep.frame.sioc.api.builder.injector.sort.DependenciesSorter;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

/**
 * 用于将handler进行处理到applicationContext中
 * @see live.midreamsheep.frame.sioc.api.builder.application.ApplicationContextBuilder
 * @author midreamsheep
 * */
public interface BeanHandlerInjector {
    /**
     * 具体的执行方法
     * @param handlerManager 待处理的集合
     * @param applicationContext 用于处理的上下文信息
     * @return 处理后的上下文信息,一般为传入的applicationContext
     * */
    ApplicationContext inject(ApplicationContext applicationContext, HandlerSet handlerManager);

    /**
     * 策略模式
     * 设置依赖关系排序器
     * @param sorter 依赖关系排序器
     * */
    void setDependenciesSorter(DependenciesSorter sorter);
}
