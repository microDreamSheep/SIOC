package live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz;

import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.field.FieldInfo;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.method.MethodInfo;

public interface ClassMetaDefinition {
    //注解信息
    AnnotationInfo getAnnotationInfo();
    void initAnnotationInfo();
    //方法信息
    MethodInfo getMethodInfo();
    void initMethodInfo();
    //字段信息
    FieldInfo getFieldInfo();
    void initFieldInfo();
}
