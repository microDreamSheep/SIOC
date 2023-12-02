package live.midreamsheep.frame.sioc.scan.processor;

import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegister;
import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegisterProcessor;

import java.util.HashMap;
import java.util.Map;

public class ProcessorManager {
    private static final Map<Class<?>,HandlerProcessor> contextHandlerMap = new HashMap<>();

    /*注入默认处理器*/
    static {
        //用于注册handler的处理器
        addProcessor(HandlerRegister.class,new HandlerRegisterProcessor());
    }

    /**
     * 用于新增上下文处理器
     * @param annotationClass 注解类
     * @param contextHandler 上下文处理器
     * */
    public static void addProcessor(Class<?> annotationClass, HandlerProcessor contextHandler){
        contextHandlerMap.put(annotationClass,contextHandler);
    }

    /**
     * 用于获取上下文处理器
     * @param annotationClass 注解类
     * @return 上下文处理器
     * */
    public static HandlerProcessor getProcessor(Class<?> annotationClass) {
        return contextHandlerMap.get(annotationClass);
    }

}
