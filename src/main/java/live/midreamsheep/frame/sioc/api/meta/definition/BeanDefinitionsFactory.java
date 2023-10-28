package live.midreamsheep.frame.sioc.api.meta.definition;

import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;

import java.util.Set;

/**
 * 用于获取所有对象单独的定义，比如类定义，接口定义等
 * @author midreamsheep
 * */
public interface BeanDefinitionsFactory {
    /**
     * 获取所有的对象定义，如果需要传入参数，推荐在实现类中添加构造函数或使用构造者模式
     * */
    Set<BeanDefinition> getBeanDefinitions();
}
