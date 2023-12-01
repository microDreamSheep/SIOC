package live.midreamsheep.frame.sioc.api.context.factory;

import live.midreamsheep.frame.sioc.api.bean.Bean;

/**
 * BeanFactory,用于存在具体的Bean
 * */
public interface BeanFactory {

    Bean getBean(long id);

    boolean registerBean(long id,Bean bean);

    boolean registerBean(Bean bean);

    boolean removeBean(long id);
}
