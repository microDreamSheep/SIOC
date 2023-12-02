package live.midreamsheep.frame.sioc.scan.clazz.method;

import live.midreamsheep.frame.sioc.scan.clazz.annotation.AnnotationInfo;


public interface MethodInter {

    String getMethodName();
    Class<?> getReturnType();
    Class<?>[] getParameterTypes();
    Class<?>[] getExceptionTypes();

    AnnotationInfo getAnnotationInfo();
}
