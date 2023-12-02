package live.midreamsheep.frame.sioc.di.processor;

import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Injector;
import live.midreamsheep.frame.sioc.di.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.di.bean.BeanDefinitionHandler;
import live.midreamsheep.frame.sioc.di.bean.BeanScope;
import live.midreamsheep.frame.sioc.di.bean.CoreBeanDefinition;
import live.midreamsheep.frame.sioc.di.bean.field.FieldInjectHandler;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.clazz.field.FieldInter;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;
import live.midreamsheep.frame.sioc.scan.processor.HandlerProcessor;
import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegister;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@HandlerRegister({Comment.class})
public class CoreBeanProcessor implements HandlerProcessor {
    @Override
    public void process(ClassMetaDefinition classMetaDefinition, List<ContextHandler> contextHandlerList) {
        //处理构造方法的注入
        contextHandlerList.add(constructorInject(classMetaDefinition.getMethodInfo().getConstructorList(),classMetaDefinition));
        //处理字段的注入
        contextHandlerList.addAll(FieldInject(classMetaDefinition));
    }

    private List<ContextHandler> FieldInject(ClassMetaDefinition classMetaDefinition) {
        List<ContextHandler> contextHandlerList = new LinkedList<>();
        for (FieldInter fieldInter : classMetaDefinition.getFieldInfo().getFieldInterList()) {
            Injector annotation = fieldInter.getAnnotationInfo().getAnnotation(Injector.class);
            if (annotation==null){
                continue;
            }
            contextHandlerList.add(new FieldInjectHandler(classMetaDefinition.getId(),fieldInter));
        }
        return contextHandlerList;
    }

    private ContextHandler constructorInject(List<MethodInter> constructorList,ClassMetaDefinition classMetaDefinition){
        Comment annotation = classMetaDefinition.getAnnotationInfo().getAnnotation(Comment.class);
        //构建BeanDefinition
        BeanDefinition beanDefinition = new CoreBeanDefinition();
        beanDefinition.setBeanId(classMetaDefinition.getId());
        beanDefinition.setBeanName(getBeanName(annotation.value(),classMetaDefinition.getOwnClass()));
        beanDefinition.setScope(BeanScope.getScope(annotation.scope()));
        beanDefinition.setBeanClass(classMetaDefinition.getOwnClass());

        MethodInter target = null;
        for (MethodInter methodInter : constructorList) {
            if (methodInter.getAnnotationInfo().getAnnotation(Injector.class)!=null){
                target=methodInter;
                break;
            }
            if(methodInter.getParameterTypes().length==0){
                target=methodInter;
            }
        }
        assert target != null:"not found suitable constructor method.please ensure a null constructor or a @Injector annotation tag";
        return new BeanDefinitionHandler(beanDefinition,target);
    }

    private String getBeanName(String name,Class<?> aClass){
        if (name.isEmpty()){
            return name;
        }
        //如果name为空，就使用类名首字母小写
        name = aClass.getName();
        name = name.substring(0,1).toLowerCase() + name.substring(1);
        return name;
    }
}