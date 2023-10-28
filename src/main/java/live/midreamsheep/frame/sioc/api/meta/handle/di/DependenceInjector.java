package live.midreamsheep.frame.sioc.api.meta.handle.di;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.handle.BeanHandler;

public abstract class DependenceInjector implements BeanHandler {

    protected Object bean;

    @Override
    public void init(Object bean) {
        this.bean = bean;
    }

    @Override
    public boolean handle(ApplicationContext context) {
        return inject(context);
    }

    public abstract boolean inject(ApplicationContext context);
}
