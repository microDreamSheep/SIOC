package live.midreamsheep.frame.sioc.di.bean;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;

import java.util.Arrays;

public class BeanDefinitionHandler extends AbstractContextHandler {

    private final BeanDefinition beanDefinition;
    private final MethodInter constructor;

    public BeanDefinitionHandler(BeanDefinition beanDefinition, MethodInter constructor) {
        toGenerate.add(beanDefinition.getBeanClass());
        dependencies.addAll(Arrays.asList(constructor.getParameterTypes()));
        this.beanDefinition = beanDefinition;
        this.constructor = constructor;
    }

    @Override
    public void handle(ApplicationContext context) {

    }
}
