package live.midreamsheep.frame.sioc.api.context.application;

import live.midreamsheep.frame.sioc.api.bean.Bean;

/**
 * 上下文管理器，内部维护了一个beanFactory，可以通过该容器获取bean
 * @see live.midreamsheep.frame.sioc.api.context.factory.BeanFactory
 * @author midreamsheep
 * */
public interface ApplicationContext {
    /**
     * 通过class获取bean
     * @param clazz 需要获取对象的类型
     * */
    <T> T getBean(Class<T> clazz);
    /**
     * 通过name获取bean
     * @param name 需要获取对象的名称
     * */
    <T> T getBean(String name,Class<T> clazz);

    /**
     * 通过bean id获取bean
     * */
    Object getBean(long id);

    /**
     * 注册bean
     * */
    void registerBean(String name, Bean bean);
    /**
     * 注册bean
     * */
    void registerBean(String name,Object object);
    /**
     * 注销bean
     * */
    void unregisterBeanByName(String name);
    void unregisterBeanByClass(Class<?> clazz);

}
