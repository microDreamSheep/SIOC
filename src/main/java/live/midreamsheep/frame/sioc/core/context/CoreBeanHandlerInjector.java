package live.midreamsheep.frame.sioc.core.context;

import live.midreamsheep.frame.sioc.api.builder.injector.sort.DependenciesSorter;
import live.midreamsheep.frame.sioc.core.context.injector.sort.CoreDependenciesSorter;
import live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;
import lombok.Setter;

/**
 * 核心的bean处理注入器
 * 先进行依赖排序，再进行依赖注入
 * @author midreamsheep
 * */
@Setter
public class CoreBeanHandlerInjector implements BeanHandlerInjector {

    private DependenciesSorter dependenciesSorter = new CoreDependenciesSorter();

    @Override
    public ApplicationContext inject(ApplicationContext applicationContext, HandlerSet handlerManager) {
        HandlerSet sort = dependenciesSorter.sort(handlerManager);

        for (ContextHandler contextHandler : sort.getContextHandlers()) {
            contextHandler.handle(applicationContext);
        }
        return applicationContext;
    }

}
