package live.midreamsheep.frame.sioc;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import live.midreamsheep.frame.sioc.impl.standard.application.StandardApplicationContextImpl;
import live.midreamsheep.frame.sioc.impl.standard.definition.parse.StandardClassParserToDefinition;
import live.midreamsheep.frame.sioc.impl.standard.parser.StandardBeanParserImpl;
import live.midreamsheep.frame.sioc.pojo.A;
import live.midreamsheep.frame.sioc.pojo.B;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ProjectTest {


    @Test
    public void startTest(){
        ApplicationContext context = new StandardApplicationContextImpl();
        StandardClassParserToDefinition standardClassParserToDefinition = new StandardClassParserToDefinition();
        Set<Class<?>> set = new HashSet<>();
        set.add(A.class);
        set.add(B.class);

        Set<BeanDefinition> parse = standardClassParserToDefinition.parse(set);

        StandardBeanParserImpl standardBeanParser = new StandardBeanParserImpl();
        ApplicationContext parse1 = standardBeanParser.parse(context, parse);


        A a = parse1.getBean(A.class);

        System.out.println(a.getB().getName());
        System.out.println(a.getB().getA().getName());
    }

}
