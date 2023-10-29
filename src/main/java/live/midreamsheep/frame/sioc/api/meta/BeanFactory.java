package live.midreamsheep.frame.sioc.api.meta;

public interface BeanFactory {
    /**
     * 通过class获取bean
     * @param clazz 需要获取对象的类型
     * */
    <T> T getBean(Class<T> clazz);
    /**
     * 注册bean
     * */
    void registerBean(Object bean);
    /**
     * 注销bean
     * */
    void unregisterBeanByClass(Class<?> clazz);
}
