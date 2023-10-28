package live.midreamsheep.frame.sioc.impl.standard;

import live.midreamsheep.frame.sioc.ApplicationContextBuilder;
import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.definition.BeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.api.meta.parser.BeanDependenciesInjector;
import live.midreamsheep.frame.sioc.impl.standard.application.StandardApplicationContextImpl;
import live.midreamsheep.frame.sioc.impl.standard.definition.PackageBeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.impl.standard.definition.parse.StandardClassParserToDefinition;
import live.midreamsheep.frame.sioc.impl.standard.definition.scanner.JarPackageScanner;
import live.midreamsheep.frame.sioc.impl.standard.parser.StandardBeanParserImpl;
import lombok.Data;

@Data
public class StandardApplicationBuilder {
    private String basePackage;

    public ApplicationContext build() {
        ApplicationContextBuilder applicationContextBuilder = new ApplicationContextBuilder();
        //实例化Application
        ApplicationContext applicationContext = new StandardApplicationContextImpl();
        //实例化DefinitionParser
        BeanDependenciesInjector standardDefinitionParser = new StandardBeanParserImpl();
        //实例化ClassDefinitionsScanner
        BeanDefinitionsFactory packageDefinitionScanner = new PackageBeanDefinitionsFactory(new JarPackageScanner(basePackage),new StandardClassParserToDefinition());

        applicationContextBuilder.setApplicationContext(applicationContext);
        applicationContextBuilder.setDefinitionParser(standardDefinitionParser);
        applicationContextBuilder.setClassDefinitionsBuilder(packageDefinitionScanner);

        return applicationContextBuilder.build();
    }
}
