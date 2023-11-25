package live.midreamsheep.frame.sioc.api.context.application;

import live.midreamsheep.frame.sioc.api.bean.Bean;

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
