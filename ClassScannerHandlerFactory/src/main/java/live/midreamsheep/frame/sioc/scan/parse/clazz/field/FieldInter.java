package live.midreamsheep.frame.sioc.scan.parse.clazz.field;

import live.midreamsheep.frame.sioc.scan.parse.clazz.annotation.AnnotationInfo;

public interface FieldInter {
    String getFieldName();
    Class<?> getFieldType();
    //获取注解信息
    AnnotationInfo getAnnotationInfo();
}
