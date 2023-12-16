package live.midreamsheep.frame.sioc.api.bean;

import lombok.Getter;

/**
 * bean的抽象类，用于实现bean的BeanMetaData的相关基本功能
 * @author midreamsheep
 * */
@Getter
public abstract class BeanAbstract implements Bean{

    /**
     * bean的元数据
     * protected，子类可直接访问
     * */
    protected BeanMetaData beanMetaData;

    @Override
    public BeanMetaData getBeanMetaData() {return beanMetaData;}

    @Override
    public abstract Object getInstance();

    /**
     * 除有特殊需求外，一般返回{@link #getInstance()}
     * */
    @Override
    public Object getSourceObject() {
        return getInstance();
    }
}
