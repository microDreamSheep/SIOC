package live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz;

import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.field.FieldInfo;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.method.MethodInfo;

public class ClassMetaDefinitionImpl implements ClassMetaDefinition{


    private final Class<?> aClass;
    private final AnnotationInfo annotationInfo = new AnnotationInfo();

    public ClassMetaDefinitionImpl(Class<?> aClass) {
        this.aClass = aClass;
    }


    @Override
    public AnnotationInfo getAnnotationInfo() {
        return annotationInfo;
    }

    @Override
    public void initAnnotationInfo() {
        annotationInfo.init(aClass);
    }

    @Override
    public MethodInfo getMethodInfo() {
        return null;
    }

    @Override
    public void initMethodInfo() {

    }

    @Override
    public FieldInfo getFieldInfo() {
        return null;
    }

    @Override
    public void initFieldInfo() {

    }
}
