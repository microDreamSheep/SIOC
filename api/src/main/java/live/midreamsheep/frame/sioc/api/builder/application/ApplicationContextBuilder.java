package live.midreamsheep.frame.sioc.api.builder.application;

import live.midreamsheep.frame.sioc.api.builder.bean.BeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.api.builder.injector.BeanDependenciesInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import lombok.Data;

import java.util.Set;


@Data
public class ApplicationContextBuilder {
    /**
     * 用于获取的handle
     * */
    private BeanDefinitionsFactory classDefinitionsBuilder;
    /**
     * 用于注册bean
     * */
    private ApplicationContext applicationContext;
    /**
     * 用于将BeanDefinition解析为bean并注册到applicationContext
     * */
    private BeanDependenciesInjector definitionParser;

    public ApplicationContext build() {
        //获取类定义
        Set<BeanDefinition> beanDefinitions = classDefinitionsBuilder.getBeanDefinitions();
        //交由applicationContext注册
        return definitionParser.inject(applicationContext, beanDefinitions);
    }
}
