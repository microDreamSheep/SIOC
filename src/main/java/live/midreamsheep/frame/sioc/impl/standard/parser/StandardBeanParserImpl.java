package live.midreamsheep.frame.sioc.impl.standard.parser;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.handle.BeanHandler;
import live.midreamsheep.frame.sioc.api.meta.parser.BeanDependenciesInjector;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StandardBeanParserImpl implements BeanDependenciesInjector {
    @Override
    public ApplicationContext parse(ApplicationContext context, Set<BeanDefinition> beanDefinitions) {
        //用于存储注入失败的处理器
        List<BeanHandler> beanHandlers = new LinkedList<>();
        //遍历所有的BeanDefinition
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                //实例化对象
                Class<?> beanClass = beanDefinition.getBeanClass();
                Object o = beanClass.getDeclaredConstructor().newInstance();

                //对对象进行处理
                beanDefinition.getBeanHandlers().forEach((a)->{
                    a.init(o);
                    boolean result = a.handle(context);
                    if (!result) {
                        beanHandlers.add(a);
                    }
                });
                //注册到容器中
                context.registerBean(beanDefinition.getBeanName(), o);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        //对注入失败的处理器进行处理
        for (BeanHandler beanDependencyInjector : beanHandlers) {
            boolean inject = beanDependencyInjector.handle(context);
            if (!inject) {
                throw new RuntimeException("inject fail");
            }
        }
        //返回上下文
        return context;
    }
}