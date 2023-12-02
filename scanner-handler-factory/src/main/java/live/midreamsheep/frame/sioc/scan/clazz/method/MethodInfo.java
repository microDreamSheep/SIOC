package live.midreamsheep.frame.sioc.scan.clazz.method;

import live.midreamsheep.frame.sioc.scan.clazz.annotation.AnnotationInfo;
import live.midreamsheep.frame.sioc.scan.clazz.method.method.ConstructorMethodInfo;
import live.midreamsheep.frame.sioc.scan.clazz.method.method.NormalMethodInfo;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MethodInfo {
    private final Map<String,MethodInter> normalMethodInfoMap = new HashMap<>();
    private final Map<String,MethodInter> constructorMethodMap = new HashMap<>();


    public MethodInter getMethodInter(String name) {
        return normalMethodInfoMap.get(name);
    }

    public void addMethodInter(String name, MethodInter methodInter) {
        normalMethodInfoMap.put(name, methodInter);
    }

    public List<MethodInter> getMethodInterList() {
        return new ArrayList<>(normalMethodInfoMap.values());
    }

    public List<MethodInter> getConstructorList() {
        return new ArrayList<>(constructorMethodMap.values());
    }

    public void addMethodInter(MethodInter methodInter) {
        addMethodInter(methodInter.getMethodName(), methodInter);
    }

    public void addConstructorMethodInter(MethodInter methodInter) {
        constructorMethodMap.put(methodInter.getMethodName(), methodInter);
    }

    public void init(Class<?> aClass){
        Method[] methods = aClass.getMethods();
        getNormalMethodInfos(methods).forEach(this::addMethodInter);

        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        getConstructorMethodInfos(declaredConstructors).forEach(this::addConstructorMethodInter);

    }

    private List<NormalMethodInfo> getNormalMethodInfos(Method[] methods) {
        List<NormalMethodInfo> normalMethodInfos = new ArrayList<>();
        for (Method method : methods) {
            normalMethodInfos.add(new NormalMethodInfo(method));
        }
        return normalMethodInfos;
    }

    private List<ConstructorMethodInfo> getConstructorMethodInfos(Constructor<?>[] constructors) {
        List<ConstructorMethodInfo> constructorMethodInfos = new ArrayList<>();
        for (Constructor<?> constructor : constructors) {
            constructorMethodInfos.add(new ConstructorMethodInfo(constructor));
        }
        return constructorMethodInfos;
    }


}
