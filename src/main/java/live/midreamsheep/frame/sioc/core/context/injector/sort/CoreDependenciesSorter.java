package live.midreamsheep.frame.sioc.core.context.injector.sort;

import live.midreamsheep.frame.sioc.api.builder.injector.sort.DependenciesSorter;
import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.api.handle.HandlerLevel;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO 优化算法，目前只是简单的依赖排序
 * @author midreamsheep
 */
public class CoreDependenciesSorter implements DependenciesSorter {
    @Override
    public HandlerSet sort(HandlerSet handlerSet) {
        List<ContextHandler> contextHandlers = new LinkedList<>(Arrays.asList(handlerSet.getContextHandlers()));

        List<ContextHandler> generate = contextHandlers.stream().filter(contextHandler -> contextHandler.getHandlerLevel() == HandlerLevel.GENERATE).collect(Collectors.toList());
        List<ContextHandler> decorate = contextHandlers.stream().filter(contextHandler -> contextHandler.getHandlerLevel() == HandlerLevel.DECORATE).collect(Collectors.toList());


        List<ContextHandler> result = new LinkedList<>();
        SuperClassManager superClassManager = new SuperClassManager();

        boolean isOk = false;
        //循环处理，直到所有的contextHandler都被处理完
        while(!generate.isEmpty()){
            //pointer用于记录当前处理的位置，如果处理失败，pointer++，如果处理成功，pointer--，因为处理成功后，contextHandlers会减少一个元素
            int pointer = 0;
            for (int i = 0; i < generate.size(); i++) {
                //获取当前处理的contextHandler
                ContextHandler contextHandler = generate.get(pointer);
                //如果依赖关系满足，就将其加入到result中，并从contextHandlers中移除
                if(isDependenciesOk(contextHandler, superClassManager.superClasses)){
                    result.add(contextHandler);
                    generate.remove(contextHandler);
                    superClassManager.addClasses(contextHandler.toGenerate());
                    pointer--;
                    isOk= true;
                }
                pointer++;
            }
            if(!isOk){
                throw new RuntimeException("依赖关系不满足");
            }
            isOk = false;
        }
        //将decorate加入到result中
        result.addAll(decorate);
        handlerSet.setContextHandlers(result.toArray(new ContextHandler[0]));
        return handlerSet;
    }

    private boolean isDependenciesOk(ContextHandler contextHandler, Set<Class<?>> superClasses){
        for (Class<?> aClass : contextHandler.getDependencies()) {
            if(!superClasses.contains(aClass)){
                return false;
            }
        }
        return true;
    }

    static class SuperClassManager{
        Set<Class<?>> superClasses = new HashSet<>();

        public void addClasses(Class<?>[] classes){
            for (Class<?> aClass : classes) {
                addClass(aClass);
            }
        }

        public void addClass(Class<?> clazz){
            superClasses.addAll(getAllClass(clazz));
            superClasses.add(clazz);
        }

        private List<Class<?>> getAllClass(Class<?> aClass){
            List<Class<?>> classes = new LinkedList<>();
            Class<?> superclass = aClass.getSuperclass();
            if(superclass!= Object.class&&superclass!=null){
                classes.add(superclass);
                classes.addAll(getAllClass(superclass));
            }
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                classes.add(anInterface);
                classes.addAll(getAllClass(anInterface));
            }
            return classes;
        }
    }
}
