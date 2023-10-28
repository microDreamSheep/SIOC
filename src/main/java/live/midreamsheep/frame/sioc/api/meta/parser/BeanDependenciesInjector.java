package live.midreamsheep.frame.sioc.api.meta.parser;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;

import java.util.Set;

/**
 * 用于将BeanDefinition实例化并注入到ApplicationContext中
 * */
public interface BeanDependenciesInjector {
    /**
     * @param context 容器上下文
     * @param beanDefinitions BeanDefinition集合
     * */
    ApplicationContext parse(ApplicationContext context, Set<BeanDefinition> beanDefinitions);
}
