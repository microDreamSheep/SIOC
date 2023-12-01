package live.midreamsheep.frame.sioc.scan.parse.clazz;

import live.midreamsheep.frame.sioc.scan.parse.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.scan.parse.clazz.field.FieldInfo;
import live.midreamsheep.frame.sioc.scan.parse.clazz.method.MethodInfo;
import lombok.Data;

@Data
public class ClassMetaDefinitionImpl implements ClassMetaDefinition{

    private final Class<?> aClass;
    private final AnnotationInfo annotationInfo = new AnnotationInfo();
    private final MethodInfo methodInfo = new MethodInfo();
    private final FieldInfo fieldInfo = new FieldInfo();


    public ClassMetaDefinitionImpl(Class<?> aClass) {
        this.aClass = aClass;
    }


    @Override
    public void initAnnotationInfo() {
        annotationInfo.init(aClass);
    }


    @Override
    public void initMethodInfo() {
        methodInfo.init(aClass);
    }

    @Override
    public void initFieldInfo() {

    }
}
