package live.midreamsheep.frame.sioc.api.bean;

import lombok.Data;

/**
 * Bean的存储结构，用于存储在{@link live.midreamsheep.frame.sioc.api.context.factory.BeanFactory}中
 * */
public interface Bean {

    /**
     * 获取bean的属性
     * */
    BeanMetaData getBeanMetaData();
    /**
     * 获取bean对应的Object
     * */
    Object getObject();
    /**
     * 获取源bean
     * 默认返回{@link #getObject()}
     * 对于代理模式则需特别返回被代理对象
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
        /**bean的唯一id*/
        long id;
    }
}
