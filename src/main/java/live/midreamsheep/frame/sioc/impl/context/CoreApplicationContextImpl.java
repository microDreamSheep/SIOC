package live.midreamsheep.frame.sioc.impl.context;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.BeanFactory;
import live.midreamsheep.frame.sioc.impl.context.factory.cast.UpCastBeanFactory;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CoreApplicationContextImpl implements ApplicationContext {

    private final BeanFactory beanFactory;

    private final Map<String,Class<?>> beanNameMap = new HashMap<>();

    @Override
    public <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return (T) beanFactory.getBean(beanNameMap.get(name));
    }

    @Override
    public void registerBean(String name, Object bean) {
        beanNameMap.put(name,bean.getClass());
        beanFactory.registerBean(bean);
    }

    @Override
    public void unregisterBeanByName(String name) {
        Class<?> aClass = beanNameMap.get(name);
        beanNameMap.remove(name);
        unregisterBeanByClass(aClass);
    }

    @Override
    public void unregisterBeanByClass(Class<?> clazz) {
        beanFactory.unregisterBeanByClass(clazz);
    }

    public CoreApplicationContextImpl() {
        this.beanFactory = new UpCastBeanFactory(3);
    }

    public CoreApplicationContextImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
