package live.midreamsheep.frame.sioc.core.context.factory;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.context.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心BeanFactory，用于管理Bean
 * @author midreamsheep
 * */
public class CoreBeanFactory implements BeanFactory {

    private final Map<Long,Bean> map = new HashMap<>();

    @Override
    public Bean getBean(long id) {
        return map.get(id);
    }

    @Override
    public boolean registerBean(Bean bean) {
        if(map.containsKey(bean.getBeanMetaData().getId())){
            return false;
        }
        map.put(bean.getBeanMetaData().getId(),bean);
        return true;
    }

    @Override
    public boolean removeBean(long id) {
        map.remove(id);
        return true;
    }
}
