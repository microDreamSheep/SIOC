package live.midreamsheep.frame.sioc.api.bean;

/**
 * 核心bean，仅用于存储对象，可用其他Bean进行封装
 * @author midreamsheep
 * */
public class CoreBean extends BeanAbstract{

    private final Object object;

    public CoreBean(Object object,BeanMetaData beanMetaData) {
        this.object = object;
        this.beanMetaData = beanMetaData;
    }

    @Override
    public Object getObject() {
        return object;
    }
}
