package live.midreamsheep.frame.sioc.api.context.factory;

import live.midreamsheep.frame.sioc.api.bean.Bean;

/**
 * BeanFactory,用于存储具体的Bean
 * @see live.midreamsheep.frame.sioc.api.context.application.ApplicationContext
 * @author midreamsheep
 * */
public interface BeanFactory {

    /**
     * 通过id获取Bean对象
     * */
    Bean getBean(long id);

    /**
     * 通过Bean对象注册Bean
     * @param bean 将要存储的Bean
     * @return 是否注册成功
     * */
    boolean registerBean(Bean bean);

    /**
     * 通过id删除Bean
     * @return 是否删除成功
     * */
    boolean removeBean(long id);
}
