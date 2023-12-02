package live.midreamsheep.frame.sioc.scan.parse;

import live.midreamsheep.frame.sioc.api.annotation.meta.HandlerFlag;
import live.midreamsheep.frame.sioc.api.annotation.meta.SIocFlag;
import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinitionImpl;
import live.midreamsheep.frame.sioc.scan.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.scan.processor.HandlerProcessor;
import live.midreamsheep.frame.sioc.scan.processor.ProcessorManager;
import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegister;
import live.midreamsheep.frame.sioc.scan.inter.ClassParserToDefinition;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public class CoreClassParserToDefinition implements ClassParserToDefinition {
    @Override
    public HandlerSet parse(Set<Class<?>> classes) {
        Set<ClassMetaDefinition> classMetaDefinitions = parseClass(classes);
        //过滤出带有HandlerFlag的类
        List<ClassMetaDefinition> processors = classMetaDefinitions.stream().filter(classMetaDefinition -> classMetaDefinition.getAnnotationInfo().getAnnotation(HandlerFlag.class) != null).collect(Collectors.toList());
        insertHandler(processors);
        //过滤出带有SIocFlag的类
        List<ClassMetaDefinition> targets = classMetaDefinitions.stream().filter(classMetaDefinition -> classMetaDefinition.getAnnotationInfo().getAnnotation(SIocFlag.class) != null).collect(Collectors.toList());
        //进行处理
        return generateHandlerSet(targets);
    }

    private HandlerSet generateHandlerSet(List<ClassMetaDefinition> targets){
        List<ContextHandler> contextHandlers = new LinkedList<>();
        for (ClassMetaDefinition target : targets) {
            for (Map.Entry<Class<?>, Annotation> classAnnotationEntry : target.getAnnotationInfo().getAnnotations().entrySet()) {
                Annotation value = classAnnotationEntry.getValue();
                HandlerProcessor processor = ProcessorManager.getProcessor(value.annotationType());
                if (processor != null) {
                    processor.process(target,contextHandlers);
                }
            }
        }
        HandlerSet handlerSet = new HandlerSet();
        handlerSet.setContextHandlers(contextHandlers.toArray(new ContextHandler[0]));
        return handlerSet;
    }



    private void insertHandler(List<ClassMetaDefinition> handles){
        HandlerProcessor processor = ProcessorManager.getProcessor(HandlerRegister.class);
        for (ClassMetaDefinition handle : handles) {
            processor.process(handle,null);
        }
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
        if (annotationInfo.getAnnotation(SIocFlag.class) != null||annotationInfo.getAnnotation(HandlerFlag.class) != null) {
            classMetaDefinition.initMethodInfo();
            classMetaDefinition.initFieldInfo();
            return classMetaDefinition;
        }
        return null;
    }
}
