package live.midreamsheep.frame.sioc.api.handle;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractContextHandler implements ContextHandler{

    protected List<Class<?>> dependencies = new LinkedList<>();
    protected List<Class<?>> toGenerate = new LinkedList<>();

    @Override
    public Class<?>[] getDependencies() {
        return dependencies.toArray(new Class[0]);
    }

    @Override
    public Class<?>[] toGenerate() {
        return toGenerate.toArray(new Class[0]);
    }

    @Override
    public HandlerLevel getHandlerLevel() {
        return HandlerLevel.GENERATE;
    }

    @Override
    public abstract void handle(ApplicationContext context);
}
