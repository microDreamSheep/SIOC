package live.midreamsheep.frame.sioc.api.bean;

public abstract class BeanAbstract implements Bean{

    protected BeanMetaData beanMetaData;

    @Override
    public BeanMetaData getBeanMetaData() {
        return beanMetaData;
    }

    @Override
    public abstract Object getObject();
}
