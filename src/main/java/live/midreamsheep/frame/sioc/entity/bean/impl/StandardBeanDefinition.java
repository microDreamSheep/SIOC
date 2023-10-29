package live.midreamsheep.frame.sioc.entity.bean.impl;

import live.midreamsheep.frame.sioc.api.meta.handle.BeanHandler;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.meta.Scope;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.ClassMetaDefinitionImpl;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class StandardBeanDefinition implements BeanDefinition {

    private Class<?> beanClass;
    private String beanName;
    private List<BeanHandler> dependenceInjectors = new LinkedList<>();
    private ClassMetaDefinition classMetaDefinition;

    public StandardBeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.classMetaDefinition = new ClassMetaDefinitionImpl(beanClass);
    }


    @Override
    public void setScope(Scope scope) {
        //TODO Auto-generated method stub
    }

    @Override
    public Scope getScope() {
        //TODO
        return Scope.SINGLETON;
    }

    @Override
    public ClassMetaDefinition getClassDefinition() {
        return this.classMetaDefinition;
    }

    @Override
    public void initAnnotationInfo() {
        classMetaDefinition.getAnnotationInfo().init(beanClass);
    }

    @Override
    public void initFieldAnnotationInfo() {

    }

    @Override
    public void initMethodAnnotationInfo() {

    }

    @Override
    public void addBeanHandler(BeanHandler injector) {
        dependenceInjectors.add(injector);
    }

    @Override
    public List<BeanHandler> getBeanHandlers() {
        return dependenceInjectors;
    }

}
