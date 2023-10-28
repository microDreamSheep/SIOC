package live.midreamsheep.frame.sioc;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.definition.BeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.api.meta.parser.BeanDependenciesInjector;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import lombok.Data;

import java.util.Set;


@Data
public class ApplicationContextBuilder {
    /**
     * 用于获取bean
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
        return definitionParser.parse(applicationContext, beanDefinitions);
    }
}
