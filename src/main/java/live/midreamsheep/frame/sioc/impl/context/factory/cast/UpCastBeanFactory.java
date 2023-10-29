package live.midreamsheep.frame.sioc.impl.context.factory.cast;

import live.midreamsheep.frame.sioc.api.meta.BeanFactory;
import lombok.Data;

import java.util.*;

public class UpCastBeanFactory implements BeanFactory {

    private final Map<Class<?>,ObjectDefinition> beanMap = new HashMap<>();

    private final int catchLevel;

    public UpCastBeanFactory(int catchLevel) {
        this.catchLevel = catchLevel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        ObjectDefinition objectDefinition = beanMap.get(clazz);
        if (objectDefinition != null){
            return (T) objectDefinition.getBean();
        }
        return null;
    }

    @Override
    public void registerBean(Object bean) {
        Class<?> aClass = bean.getClass();
        //获取类的所有父类和接口
        List<ObjectDefinition> classAllSuperClass = getClassAllSuperClass(aClass, 0);
        classAllSuperClass.forEach((a)->{
            ObjectDefinition result = beanMap.get(a.getAClass());
            if (result == null || result.getLevel() > a.getLevel()){
                a.setBean(bean);
                this.beanMap.put(a.getAClass(),a);
            }
        });
        //注册到容器中
        this.beanMap.put(aClass,new ObjectDefinition(0,aClass,bean));
    }

    @Override
    public void unregisterBeanByClass(Class<?> clazz) {
        ObjectDefinition objectDefinition = beanMap.get(clazz);
        //获取类的所有父类和接口
        List<ObjectDefinition> classAllSuperClass = getClassAllSuperClass(clazz, 0);
        classAllSuperClass.forEach((a)->{
            if(a.getBean() != objectDefinition.getBean()){
                return;
            }
            this.beanMap.remove(a.getAClass());
        });
        this.beanMap.remove(clazz);
    }

    private List<ObjectDefinition> getClassAllSuperClass(Class<?> aClass,int level){
        List<ObjectDefinition> classList = new LinkedList<>();

        if (level == catchLevel||aClass == null){
            return classList;
        }
        level++;

        //获取接口
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            classList.add(new ObjectDefinition(level,anInterface));
            classList.addAll(getClassAllSuperClass(anInterface,level));
        }

        //获取父类
        Class<?> superclass = aClass.getSuperclass();
        if (superclass!=null&&superclass != Object.class){
            classList.add(new ObjectDefinition(level,superclass));
            classList.addAll(getClassAllSuperClass(superclass,level));
        }

        return classList;
    }

    @Data
    private static class ObjectDefinition{
        private int level;
        private Class<?> aClass;
        private Object bean;

        public ObjectDefinition(int level, Class<?> superclass) {
            this(level,superclass,null);
        }

        public ObjectDefinition(int level, Class<?> aClass, Object bean) {
            this.level = level;
            this.aClass = aClass;
            this.bean = bean;
        }
    }
}
