package live.midreamsheep.frame.sioc;

import live.midreamsheep.frame.sioc.scan.parse.CoreClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.processor.core.bean.InjectorProcessor;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HandleTest {

    @Test
    public void NormalTest(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(InjectorProcessor.class);
        classes.add(asdsad.class);
        new CoreClassParserToDefinition().parse(classes);
    }
}
