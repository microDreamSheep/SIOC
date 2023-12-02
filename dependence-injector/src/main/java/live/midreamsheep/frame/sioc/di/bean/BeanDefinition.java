package live.midreamsheep.frame.sioc.di.bean;

public interface BeanDefinition {
    /**
     * beanName相关
     * */
    void setBeanName(String beanName);
    String getBeanName();
    /**
     * Scope相关
     * */
    void setScope(BeanScope scope);
    BeanScope getScope();
    /**
     * beanClass相关
     * */
    void setBeanClass(Class<?> beanClass);
    Class<?> getBeanClass();
    /**
     * BeanId相关
     * */
    void setBeanId(long id);
    long getBeanId();
}
