package live.midreamsheep.frame.sioc.api.builder.injector.sort;

import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

/**
 * handler排序处理函数式接口，用于自定义排序逻辑
 * 标准逻辑：先根据{@link live.midreamsheep.frame.sioc.api.handle.HandlerLevel}进行主排序
 *         然后根据依赖关系计算出能让Handler正常执行的顺序,即一个handler处理时他的dependencies比如在之前的handler的generate中
 * */
@FunctionalInterface
public interface DependenciesSorter {
    /**
     * @param handlerSet 未排序的接口
     * @return 排序完的接口
     * */
    HandlerSet sort(HandlerSet handlerSet);
}
