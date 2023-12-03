package live.midreamsheep.frame.sioc.scan.processor.core.register;

import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.processor.ProcessorManager;
import live.midreamsheep.frame.sioc.scan.processor.HandlerProcessor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 用于注册handler的处理器
 * */
public class HandlerRegisterProcessor implements HandlerProcessor {

    @Override
    public void process(ClassMetaDefinition classMetaDefinition, List<ContextHandler> contextHandlerList) {
        //将其加入到上下文处理器中
        if (classMetaDefinition.getAnnotationInfo().getAnnotation(HandlerRegister.class) == null){
            return;
        }
        for (Class<?> aClass : classMetaDefinition.getAnnotationInfo().getAnnotation(HandlerRegister.class).value()) {
            try {
                ProcessorManager.addProcessor(aClass,(HandlerProcessor) classMetaDefinition.getOwnClass().getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
