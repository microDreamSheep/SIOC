package live.midreamsheep.frame.sioc.api.builder.injector;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

public interface BeanHandlerInjector {
    ApplicationContext inject(ApplicationContext applicationContext, HandlerSet handlerManager);
}
