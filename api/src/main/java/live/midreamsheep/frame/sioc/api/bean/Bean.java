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
     * 获取bean内部的Object
     * */
    Object getObject();
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
