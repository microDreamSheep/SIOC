package live.midreamsheep.frame.sioc.impl.standard.definition.parse;

import live.midreamsheep.frame.sioc.api.annotation.injector.BeanScope;
import live.midreamsheep.frame.sioc.api.annotation.injector.Comment;
import live.midreamsheep.frame.sioc.api.annotation.injector.Injector;
import live.midreamsheep.frame.sioc.api.meta.handle.di.injector.FieldDependenceInjector;
import live.midreamsheep.frame.sioc.api.scanner.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.impl.StandardBeanDefinition;
import live.midreamsheep.frame.sioc.entity.bean.meta.InjectType;
import live.midreamsheep.frame.sioc.util.StringUtil;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class StandardClassParserToDefinition implements ClassParserToDefinition {
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
        //将类上的注解进行解层



        Comment comment = aClass.getAnnotation(Comment.class);
        //判断aClass是否是普通类而非接口或者抽象类
        if(comment ==null){
            return null;
        }
        BeanDefinition beanDefinition = new StandardBeanDefinition();
        beanDefinition.setBeanClass(aClass);
        beanDefinition.setBeanName(comment.value().isEmpty()? StringUtil.firstCharToLowerCase(aClass.getSimpleName()): comment.value());

        BeanScope scope = aClass.getAnnotation(BeanScope.class);
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
