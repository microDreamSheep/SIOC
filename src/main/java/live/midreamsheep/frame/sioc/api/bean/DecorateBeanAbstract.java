package live.midreamsheep.frame.sioc.api.bean;

/**
 * 修饰bean，用于一般用户的自定义的bean的定义，内部bean的底层bean为{@link live.midreamsheep.frame.sioc.core.bean.CoreBean}
 * 例如：懒加载修饰，单例修饰等
 * @author midreamsheep
 * */
public abstract class DecorateBeanAbstract extends BeanAbstract{

    /**
     * bean内部的bean
     * */
    protected final Bean bean;

    /**
     * 传入一个被修饰的bean
     **/
    public DecorateBeanAbstract(Bean bean) {
        this.bean = bean;
        this.beanMetaData = bean.getBeanMetaData();
    }

    @Override
    public abstract Object getInstance();
}
