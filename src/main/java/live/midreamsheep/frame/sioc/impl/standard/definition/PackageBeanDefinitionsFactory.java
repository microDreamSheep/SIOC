package live.midreamsheep.frame.sioc.impl.standard.definition;

import live.midreamsheep.frame.sioc.api.meta.definition.BeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.api.scanner.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.api.scanner.ClassesScanner;
import live.midreamsheep.frame.sioc.entity.bean.BeanDefinition;
import lombok.AllArgsConstructor;

import java.util.Set;

/**
 * 默认通过包扫描器实现对对象定义的扫描加解析
 * 先通过{@link ClassesScanner}获取所有的类，再通过{@link ClassParserToDefinition}解析成对象定义
 * @author midreamsheep
 * @since 2023/10/28
 * */
@AllArgsConstructor
public class PackageBeanDefinitionsFactory implements BeanDefinitionsFactory {

    private ClassesScanner classesScanner;
    private ClassParserToDefinition definitionsParser;

    @Override
    public Set<BeanDefinition> getBeanDefinitions() {
        return definitionsParser.parse(classesScanner.scan());
    }

    /**
     * 增加一个扫描器
     * */
    public void addClassesScanner(ClassesScanner classesScanner) {
        this.classesScanner.next(classesScanner);
    }

}
