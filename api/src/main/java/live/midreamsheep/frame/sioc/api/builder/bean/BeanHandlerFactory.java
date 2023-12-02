package live.midreamsheep.frame.sioc.api.builder.bean;

import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

/**
 * 用于处理获取对context的处理器
 * */
public interface BeanHandlerFactory {
    /**
     * 用于获取处理器
     * */
    HandlerSet generateHandlerManager();

}
