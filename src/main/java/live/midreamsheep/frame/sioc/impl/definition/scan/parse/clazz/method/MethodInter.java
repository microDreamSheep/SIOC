package live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.method;

import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.annotation.AnnotationInfo;


public interface MethodInter {

    String getMethodName();
    Class<?> getReturnType();
    Class<?>[] getParameterTypes();
    Class<?>[] getExceptionTypes();

    AnnotationInfo getAnnotationInfo();
}
