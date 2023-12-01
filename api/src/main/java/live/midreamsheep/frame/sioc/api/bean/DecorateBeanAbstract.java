package live.midreamsheep.frame.sioc.api.bean;

public abstract class DecorateBeanAbstract extends BeanAbstract{

    protected final Bean bean;

    public DecorateBeanAbstract(Bean bean) {
        this.bean = bean;
        this.beanMetaData = bean.getBeanMetaData();
    }

    @Override
    public abstract Object getObject();
}
