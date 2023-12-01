package live.midreamsheep.frame.sioc.scan.parse;

import live.midreamsheep.frame.sioc.api.annotation.meta.HandlerFlag;
import live.midreamsheep.frame.sioc.api.annotation.meta.SIocFlag;
import live.midreamsheep.frame.sioc.api.handle.HandlerManager;
import live.midreamsheep.frame.sioc.scan.inter.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.parse.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.parse.clazz.ClassMetaDefinitionImpl;
import live.midreamsheep.frame.sioc.scan.parse.clazz.annotation.AnnotationInfo;

import java.util.HashSet;
import java.util.Set;

public class CoreClassParserToDefinition implements ClassParserToDefinition {
    @Override
    public HandlerManager parse(Set<Class<?>> classes) {
        Set<ClassMetaDefinition> classMetaDefinitions = parseClass(classes);
        ClassMetaDefinition[] beanDefinitions = new ClassMetaDefinition[0];
        //过滤出带有HandlerFlag的类
        ClassMetaDefinition[] handlerClassDefinitions = classMetaDefinitions.stream().filter(classMetaDefinition -> classMetaDefinition.getAnnotationInfo().getAnnotation(HandlerFlag.class) != null).toArray(ClassMetaDefinition[]::new);
        //过滤出带有SIocFlag的类
        ClassMetaDefinition[] sIocClassDefinitions = classMetaDefinitions.stream().filter(classMetaDefinition -> classMetaDefinition.getAnnotationInfo().getAnnotation(SIocFlag.class) != null).toArray(ClassMetaDefinition[]::new);

        return null;
    }

    private void insertHandler(){
        //TODO
    }

    private Set<ClassMetaDefinition> parseClass(Set<Class<?>> classes) {
        Set<ClassMetaDefinition> classMetaDefinitions = new HashSet<>();
        for (Class<?> aClass : classes) {
            ClassMetaDefinition classMetaDefinition = parse(aClass);
            if (classMetaDefinition != null) {
                classMetaDefinitions.add(classMetaDefinition);
            }
        }
        return classMetaDefinitions;
    }

    private ClassMetaDefinition parse(Class<?> aClass) {
        ClassMetaDefinition classMetaDefinition = new ClassMetaDefinitionImpl(aClass);
        classMetaDefinition.initAnnotationInfo();
        //判断是否被标记
        AnnotationInfo annotationInfo = classMetaDefinition.getAnnotationInfo();
        if (annotationInfo.getAnnotation(SIocFlag.class) != null&&annotationInfo.getAnnotation(HandlerFlag.class) != null) {
            classMetaDefinition.initMethodInfo();
            classMetaDefinition.initFieldInfo();
            return classMetaDefinition;
        }
        return null;
    }
}
