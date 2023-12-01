package live.midreamsheep.frame.sioc.api.bean.single;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.bean.DecorateBeanAbstract;

/**
 * 单例Bean，用于获取唯一性的对象
 * @author midreamsheep
 * */
public class SingleBean extends DecorateBeanAbstract {

    public SingleBean(Bean object) {
        super(object);
    }

    @Override
    public Object getObject() {
        return bean.getObject();
    }
}
