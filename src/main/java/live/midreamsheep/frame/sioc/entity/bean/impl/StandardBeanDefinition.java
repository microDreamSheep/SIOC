package live.midreamsheep.frame.sioc.entity.bean.impl;

import live.midreamsheep.frame.sioc.api.meta.handle.BeanHandler;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.meta.Scope;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class StandardBeanDefinition implements BeanDefinition {

    private Class<?> beanClass;
    private String beanName;
    private List<BeanHandler> dependenceInjectors = new LinkedList<>();

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
    public void addBeanHandler(BeanHandler injector) {
        dependenceInjectors.add(injector);
    }

    @Override
    public List<BeanHandler> getBeanHandlers() {
        return dependenceInjectors;
    }

}
