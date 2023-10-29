package live.midreamsheep.frame.sioc.entity.bean;

import live.midreamsheep.frame.sioc.api.meta.handle.BeanHandler;
import live.midreamsheep.frame.sioc.entity.bean.meta.Scope;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.ClassMetaDefinition;

import java.util.List;

public interface BeanDefinition {

    /**
     * 设置bean的构建方式,默认为Singleton
     * */
    void setScope(Scope scope);
    Scope getScope();

    /**
     * 设置对象的类型
     * */
    void setBeanClass(Class<?> beanClass);
    Class<?> getBeanClass();
    /**
     * 设置bean的名称
     * */
    void setBeanName(String name);
    String getBeanName();


    ClassMetaDefinition getClassDefinition();

    void initAnnotationInfo();
    void initFieldAnnotationInfo();
    void initMethodAnnotationInfo();


    /**
     * 新增对象处理器
     * */
    void addBeanHandler(BeanHandler injector);
    List<BeanHandler> getBeanHandlers();

}
