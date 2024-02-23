package live.midreamsheep.frame.sioc.core.bean.proto;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.bean.DecorateBeanAbstract;

import java.lang.reflect.InvocationTargetException;

/**
 * 原型bean，用于通过一个对象获取多个对象，每个对象都是新的
 * */
public class PrototypeBean extends DecorateBeanAbstract {
    public PrototypeBean(Bean object) {
        super(object);
    }

    @Override
    public Object getInstance() {
        Object targetObject = bean.getInstance();
        //通过反射调用clone
        try {
            return targetObject.getClass().getMethod("clone").invoke(targetObject);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 除有特殊需求外，一般返回{@link #getInstance()}
     */
    @Override
    public Object getSourceObject() {
        return bean.getSourceObject();
    }
}
