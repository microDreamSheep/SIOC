package live.midreamsheep.frame.sioc.api.context.factory;

import live.midreamsheep.frame.sioc.api.bean.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心BeanFactory，用于管理Bean
 * */
public class CoreBeanFactory implements BeanFactory {

    private final Map<Long,Bean> map = new HashMap<>();

    @Override
    public Bean getBean(long id) {
        return map.get(id);
    }

    @Override
    public boolean registerBean(long id, Bean bean) {
        if(map.containsKey(id)){
            return false;
        }
        map.put(id,bean);
        return true;
    }

    @Override
    public boolean registerBean(Bean bean) {
        return registerBean(bean.getBeanMetaData().getId(),bean);
    }

    @Override
    public boolean removeBean(long id) {
        map.remove(id);
        return true;
    }
}
