package live.midreamsheep.frame.sioc.impl.standard.application;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class StandardApplicationContextImpl implements ApplicationContext {

    private final Map<String,Object> beanNameMap = new HashMap<>();
    private final Map<Class<?>,Object> beanClassMap = new HashMap<>();

    @Override
    public <T> T getBean(Class<T> clazz) {
        return beanClassMap.containsKey(clazz) ? (T) beanClassMap.get(clazz) : null;
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return beanNameMap.containsKey(name) ? (T) beanNameMap.get(name) : null;
    }

    @Override
    public void registerBean(String name, Object bean) {
        beanNameMap.put(name,bean);
        beanClassMap.put(bean.getClass(),bean);
    }

    @Override
    public void unregisterBeanByName(String name) {
        Object o = beanNameMap.get(name);
        beanNameMap.remove(name);
        unregisterBeanByClass(o.getClass());
    }

    @Override
    public void unregisterBeanByClass(Class<?> clazz) {
        beanClassMap.remove(clazz);
    }
}
