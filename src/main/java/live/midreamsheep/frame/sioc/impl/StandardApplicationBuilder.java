package live.midreamsheep.frame.sioc.impl;

import live.midreamsheep.frame.sioc.ApplicationContextBuilder;
import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.definition.BeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.api.meta.parser.BeanDependenciesInjector;
import live.midreamsheep.frame.sioc.impl.context.CoreApplicationContextImpl;
import live.midreamsheep.frame.sioc.impl.definition.scan.PackageBeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.impl.definition.scan.parse.CoreClassParserToDefinition;
import live.midreamsheep.frame.sioc.impl.definition.scan.scanner.JarPackageScanner;
import live.midreamsheep.frame.sioc.impl.parser.CoreBeanParserImpl;
import lombok.Data;

@Data
public class StandardApplicationBuilder {
    private String basePackage;

    public ApplicationContext build() {
        ApplicationContextBuilder applicationContextBuilder = new ApplicationContextBuilder();
        //实例化Application
        ApplicationContext applicationContext = new CoreApplicationContextImpl();
        //实例化DefinitionParser
        BeanDependenciesInjector standardDefinitionParser = new CoreBeanParserImpl();
        //实例化ClassDefinitionsScanner
        BeanDefinitionsFactory packageDefinitionScanner = new PackageBeanDefinitionsFactory(new JarPackageScanner(basePackage),new CoreClassParserToDefinition());

        applicationContextBuilder.setApplicationContext(applicationContext);
        applicationContextBuilder.setDefinitionParser(standardDefinitionParser);
        applicationContextBuilder.setClassDefinitionsBuilder(packageDefinitionScanner);

        return applicationContextBuilder.build();
    }
}
