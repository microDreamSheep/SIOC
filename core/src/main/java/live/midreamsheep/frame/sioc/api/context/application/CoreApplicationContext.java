package live.midreamsheep.frame.sioc.api.context.application;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.context.factory.BeanFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * 核心上下文，用于存储bean
 * */
@SuppressWarnings("unchecked")
public class CoreApplicationContext implements ApplicationContext{

    private int level;
    private BeanFactory factory;

    private final Map<String,Long> beanNameMapper = new HashMap<>();

    private final Map<Class<?>,LevelClass> beanClassMapper = new HashMap<>();

    @Override
    public <T> T getBean(Class<T> clazz) {
        return (T) factory.getBean(beanClassMapper.get(clazz).getId());
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return (T) factory.getBean(beanNameMapper.get(name));
    }

    @Override
    public void registerBean(String name, Bean bean) {
        factory.registerBean(bean);
        beanNameMapper.put(name,bean.getBeanMetaData().getId());
        //解析到指定的父类
        List<LevelClass> allLevelClass = getAllLevelClass(bean.getBeanMetaData().getAClass(), 0);
        allLevelClass.forEach((aLevelClass)->{
            if(beanClassMapper.get(aLevelClass.getAClass()).getLevel()>= aLevelClass.level){
                beanClassMapper.put(aLevelClass.getAClass(),aLevelClass);
                aLevelClass.setId(bean.getBeanMetaData().getId());
            }
        });
        //将自身放入
        beanClassMapper.put(bean.getBeanMetaData().getAClass(),new LevelClass(0,bean.getBeanMetaData().getId(),bean.getBeanMetaData().getAClass()));
    }

    private List<LevelClass> getAllLevelClass(Class<?> aClass, int level){
        List<LevelClass> list = new LinkedList<>();
        //获取父类
        Class<?> superclass = aClass.getSuperclass();
        if (superclass!=Object.class){
            list.add(new LevelClass(level+1,superclass));
        }
        Class<?>[] interfaces = aClass.getInterfaces();
        list.addAll(LevelClass.buildByClasses(interfaces,level+1));
        if(level>=this.level){
            return list;
        }
        for (Class<?> anInterface : interfaces) {
            list.addAll(getAllLevelClass(anInterface,level+1));
        }
        if (superclass!= Object.class){
            list.addAll(getAllLevelClass(superclass,level+1));
        }
        return list;
    }



    @Override
    public void registerBean(String name, Object object) {

    }

    @Override
    public void unregisterBeanByName(String name) {

    }

    @Override
    public void unregisterBeanByClass(Class<?> clazz) {

    }

    @Data
    @AllArgsConstructor
    static class LevelClass{
       int level;
       long id;
       Class<?> aClass;

        public LevelClass(int level, Class<?> aClass) {
            this.level = level;
            this.aClass = aClass;
        }


        public static List<LevelClass> buildByClasses(Class<?>[] classes,int level){
            List<LevelClass> levelClasses = new LinkedList<>();
            for (Class<?> aClass : classes) {
                levelClasses.add(new LevelClass(level,aClass));
            }
            return levelClasses;
        }
    }
}
