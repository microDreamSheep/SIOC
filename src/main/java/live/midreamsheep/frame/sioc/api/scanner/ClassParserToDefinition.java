package live.midreamsheep.frame.sioc.api.scanner;

import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;

import java.util.Set;

/**
 * 解析class为bean定义
 * */
public interface ClassParserToDefinition {

    /**
     * 解析class为bean定义
     * @param classes 需要解析的class
     * @return bean定义
     * */
    Set<BeanDefinition> parse(Set<Class<?>> classes);

}
