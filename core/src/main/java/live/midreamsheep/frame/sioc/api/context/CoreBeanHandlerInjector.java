package live.midreamsheep.frame.sioc.api.context;

import live.midreamsheep.frame.sioc.api.builder.injector.BeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

public class CoreBeanHandlerInjector implements BeanHandlerInjector {
    @Override
    public ApplicationContext inject(ApplicationContext applicationContext, HandlerSet handlerManager) {
        for (ContextHandler contextHandler : handlerManager.getContextHandlers()) {
            contextHandler.handle(applicationContext);
        }
        return applicationContext;
    }
}
