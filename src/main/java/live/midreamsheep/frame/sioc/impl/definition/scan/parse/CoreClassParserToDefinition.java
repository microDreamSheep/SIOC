package live.midreamsheep.frame.sioc.impl.definition.scan.parse;

import live.midreamsheep.frame.sioc.api.annotation.injector.BeanScope;
import live.midreamsheep.frame.sioc.api.annotation.injector.Comment;
import live.midreamsheep.frame.sioc.api.annotation.injector.Injector;
import live.midreamsheep.frame.sioc.api.annotation.injector.IocTag;
import live.midreamsheep.frame.sioc.api.meta.handle.di.injector.FieldDependenceInjector;
import live.midreamsheep.frame.sioc.api.scanner.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.impl.CoreBeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.meta.InjectType;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.ClassMetaDefinitionImpl;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class CoreClassParserToDefinition implements ClassParserToDefinition {
    @Override
    public Set<BeanDefinition> parse(Set<Class<?>> classes) {
        Set<BeanDefinition> beanDefinitions = new HashSet<>();
        classes.forEach((a)->{
            BeanDefinition parse = parse(a);
            if (parse != null) {
                beanDefinitions.add(parse);
            }
        });
        return beanDefinitions;
    }

    private BeanDefinition parse(Class<?> aClass) {
        //创建ClassDefinition
        ClassMetaDefinition classDefinition = new ClassMetaDefinitionImpl(aClass);

        //将类上的注解进行解层
        AnnotationInfo annotationInfo = classDefinition.getAnnotationInfo();
        classDefinition.initAnnotationInfo();

        //判断是否被容器标记处理
        IocTag tag = annotationInfo.getAnnotation(IocTag.class);
        if(tag ==null){
            return null;
        }
        //对方法和字段进行分析
        classDefinition.initFieldInfo();
        classDefinition.initMethodInfo();


        //TODO 以下为测试代码
        BeanDefinition beanDefinition = new CoreBeanDefinition(aClass);
        Comment comment = annotationInfo.getAnnotation(Comment.class);
        beanDefinition.setBeanName(comment.value().isEmpty()? StringUtil.firstCharToLowerCase(aClass.getSimpleName()): comment.value());
        BeanScope scope = annotationInfo.getAnnotation(BeanScope.class);
        if (scope != null) {
            beanDefinition.setScope(scope.value());
        }

        //class所有的字段
        for (Field field : aClass.getDeclaredFields()) {
            //设置字段可访问
            field.setAccessible(true);
            Injector annotation = field.getAnnotation(Injector.class);
            if (annotation == null) {
                continue;
            }
            FieldDependenceInjector fieldDependenceInjector = new FieldDependenceInjector(field);
            fieldDependenceInjector.setDependenceClass(field.getType());
            beanDefinition.addBeanHandler(fieldDependenceInjector);
            if (annotation.type()== InjectType.BY_NAME){
                fieldDependenceInjector.setDependenceName(annotation.name());
            }
        }

        return beanDefinition;
    }
}
