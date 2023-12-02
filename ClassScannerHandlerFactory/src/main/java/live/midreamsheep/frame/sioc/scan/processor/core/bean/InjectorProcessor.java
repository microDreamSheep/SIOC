package live.midreamsheep.frame.sioc.scan.processor.core.bean;

import live.midreamsheep.frame.sioc.api.annotation.basic.Comment;
import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.handler.bean.BeanHandler;
import live.midreamsheep.frame.sioc.scan.processor.HandlerProcessor;
import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegister;

import java.util.List;

@HandlerRegister({Comment.class})
public class InjectorProcessor  implements HandlerProcessor {
    @Override
    public void process(ClassMetaDefinition classMetaDefinition, List<ContextHandler> contextHandlerList) {
        BeanHandler beanHandler = new BeanHandler(classMetaDefinition);
        contextHandlerList.add(beanHandler);
    }
}
