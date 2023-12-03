package live.midreamsheep.frame.sioc.di.bean;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.bean.CoreBean;
import live.midreamsheep.frame.sioc.api.bean.single.SingleBean;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;

import java.lang.reflect.InvocationTargetException;
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
        try {
            Object o = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
            Bean.BeanMetaData beanMetaData = new Bean.BeanMetaData();
            beanMetaData.setAClass(beanDefinition.getBeanClass());
            beanMetaData.setId(beanDefinition.getBeanId());
            beanMetaData.setName(beanDefinition.getBeanName());
            SingleBean singleBean = new SingleBean(new CoreBean(o, beanMetaData));
            context.registerBean(beanDefinition.getBeanName(), singleBean);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
