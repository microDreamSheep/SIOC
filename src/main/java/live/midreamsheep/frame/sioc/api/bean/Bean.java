package live.midreamsheep.frame.sioc.api.bean;

import lombok.Data;

/**
 * Bean的结构，用于存储在{@link live.midreamsheep.frame.sioc.api.context.factory.BeanFactory}中
 * @see live.midreamsheep.frame.sioc.api.context.factory.BeanFactory
 * @author midreamsheep
 * */
public interface Bean {

    /**
     * 获取bean的源属性
     * @return bean的源属性
     * */
    BeanMetaData getBeanMetaData();
    /**
     * 获取bean内部存储的实例对象，主要由框架使用者调用
     * */
    Object getInstance();
    /**
     * 获取源bean，即未经过代理等修饰的原始对象
     * 默认返回{@link #getInstance()}
     * 比如：对于代理模式则需特别返回被代理对象
     * */
    Object getSourceObject();
    /**
     * Bean的元属性
     * */
    @Data
    class BeanMetaData{
        /**bean的class*/
        Class<?> aClass;
        /**bean的name*/
        String name;
        /**bean的唯一id，由雪花算法生成*/
        long id;
    }
}
