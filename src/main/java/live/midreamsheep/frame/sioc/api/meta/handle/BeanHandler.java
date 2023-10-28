package live.midreamsheep.frame.sioc.api.meta.handle;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;

public interface BeanHandler {
    /**
     * 初始化，将需要注入的bean传入
     * */
    void init(Object bean);

    /**
     * 执行注入操作
     * */
    boolean handle(ApplicationContext context);
}
