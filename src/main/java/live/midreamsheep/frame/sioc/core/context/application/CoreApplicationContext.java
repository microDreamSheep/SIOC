package live.midreamsheep.frame.sioc.core.context.application;

import cn.hutool.core.lang.Snowflake;
import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.context.factory.BeanFactory;
import live.midreamsheep.frame.sioc.core.bean.CoreBean;
import live.midreamsheep.frame.sioc.core.bean.single.SingleBean;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * 核心上下文，用于存储bean
 * 包括类的向上解析，比如A继承B，B继承C，那么A的bean可以被解析到B和C中，即getBean(B.class)和getBean(C.class)都可以获取到A的bean
 * @author midreamsheep
 * */
@SuppressWarnings("unchecked")
public class CoreApplicationContext implements ApplicationContext {
    /**
     * 向上查询的结构
     * */
    private int level = Integer.MAX_VALUE;
    /**
     * BeanFactory
     * */
    private final BeanFactory factory;

    private final Map<String,Long> beanNameMapper = new HashMap<>();

    private final Map<Class<?>,LevelClass> beanClassMapper = new HashMap<>();

    public CoreApplicationContext(BeanFactory beanFactory) {
        this.factory = beanFactory;
    }

    public CoreApplicationContext(BeanFactory beanFactory, int level) {
        this.factory = beanFactory;
        this.level = level;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return (T) factory.getBean(beanClassMapper.get(clazz).getId()).getInstance();
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return (T) factory.getBean(beanNameMapper.get(name));
    }

    @Override
    public Object getBean(long id) {
        return factory.getBean(id).getInstance();
    }

    @Override
    public void registerBean(String name, Bean bean) {
        factory.registerBean(bean);
        beanNameMapper.put(name,bean.getBeanMetaData().getId());
        //解析到指定的父类
        List<LevelClass> allLevelClass = getAllLevelClass(bean.getBeanMetaData().getAClass(), 0);
        for (LevelClass aClass : allLevelClass) {
            LevelClass levelClass = beanClassMapper.get(aClass.getAClass());
            if (levelClass!=null&&levelClass.getLevel()<aClass.getLevel()){
                continue;
            }
            beanClassMapper.put(aClass.getAClass(),aClass);
            aClass.setId(bean.getBeanMetaData().getId());

        }
        //将自身放入
        beanClassMapper.put(bean.getBeanMetaData().getAClass(),new LevelClass(0,bean.getBeanMetaData().getId(),bean.getBeanMetaData().getAClass()));
    }

    private List<LevelClass> getAllLevelClass(Class<?> aClass, int level){
        List<LevelClass> list = new LinkedList<>();
        //获取父类
        Class<?> superclass = aClass.getSuperclass();
        if (superclass!=Object.class&&superclass!=null){
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
        if (superclass!= Object.class&&superclass!=null){
            list.addAll(getAllLevelClass(superclass,level+1));
        }
        return list;
    }



    @Override
    public void registerBean(String name, Object object) {
        Bean.BeanMetaData beanMetaData = new Bean.BeanMetaData();
        beanMetaData.setAClass(object.getClass());
        beanMetaData.setId(new Snowflake(1,1).nextId());
        beanMetaData.setName(name);
        SingleBean singleBean = new SingleBean(new CoreBean(object, beanMetaData));
        registerBean(name,singleBean);
    }

    @Override
    public void unregisterBeanByName(String name) {
        Long id = beanNameMapper.get(name);
        Bean bean = factory.getBean(id);
        Class<?> aClass = bean.getBeanMetaData().getAClass();
        getAllLevelClass(aClass,0).forEach(levelClass -> {
            if (levelClass.getId()==id){
                beanClassMapper.remove(levelClass.getAClass());
            }
        });
        factory.removeBean(id);
        beanNameMapper.remove(name);
    }

    @Override
    public void unregisterBeanByClass(Class<?> clazz) {
        LevelClass levelClass = beanClassMapper.get(clazz);
        Class<?> aClass = factory.getBean(levelClass.getId()).getBeanMetaData().getAClass();
        getAllLevelClass(aClass,0).forEach(levelClass1 -> {
            if (levelClass1.getId()==levelClass.getId()){
                beanClassMapper.remove(levelClass1.getAClass());
            }
        });
        factory.removeBean(levelClass.getId());
        beanClassMapper.remove(clazz);
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
