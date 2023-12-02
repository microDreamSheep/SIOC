package live.midreamsheep.frame.sioc.api.builder.application.sort;

import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

@FunctionalInterface
public interface DependenciesSorter {
    HandlerSet sort(HandlerSet handlerSet);
}
