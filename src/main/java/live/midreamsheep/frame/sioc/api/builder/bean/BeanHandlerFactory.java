package live.midreamsheep.frame.sioc.api.builder.bean;

import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

/**
 * 用于处理获取对context的处理器
 * @see live.midreamsheep.frame.sioc.api.builder.application.ApplicationContextBuilder
 * @author midreamsheep
 * */
public interface BeanHandlerFactory {
    /**
     * 用于获取处理器
     * @return 处理器集合
     * */
    HandlerSet generateHandlerManager();

}
