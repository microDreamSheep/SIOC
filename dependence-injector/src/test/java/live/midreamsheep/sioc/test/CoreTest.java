package live.midreamsheep.sioc.test;

import cn.hutool.core.util.ClassUtil;
import live.midreamsheep.frame.sioc.api.builder.application.sort.core.CoreDependenciesSorter;
import live.midreamsheep.frame.sioc.api.context.CoreBeanHandlerInjector;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.context.application.CoreApplicationContext;
import live.midreamsheep.frame.sioc.api.context.factory.CoreBeanFactory;
import live.midreamsheep.frame.sioc.api.handle.HandlerSet;
import live.midreamsheep.frame.sioc.scan.inter.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.parse.CoreClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.processor.core.register.HandlerRegister;
import live.midreamsheep.sioc.test.pojo.B;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Set;

public class CoreTest {
    @Test
    public void test(){
        Set<Class<?>> classes = ClassUtil.scanPackage();
        ClassParserToDefinition classParserToDefinition = new CoreClassParserToDefinition();
        HandlerSet parse = classParserToDefinition.parse(classes);
        HandlerSet sort = new CoreDependenciesSorter().sort(parse);
        CoreApplicationContext coreApplicationContext = new CoreApplicationContext(new CoreBeanFactory(),5);
        ApplicationContext inject = new CoreBeanHandlerInjector().inject(coreApplicationContext, sort);
        B bean = inject.getBean(B.class);
        bean.getA().getB().getA().say();
    }
}
