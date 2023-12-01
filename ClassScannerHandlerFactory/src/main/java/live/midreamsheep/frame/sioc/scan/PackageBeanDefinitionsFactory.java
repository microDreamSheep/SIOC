package live.midreamsheep.frame.sioc.scan;

import live.midreamsheep.frame.sioc.api.builder.bean.BeanHandlerFactory;
import live.midreamsheep.frame.sioc.api.handle.HandlerManager;
import live.midreamsheep.frame.sioc.scan.inter.ClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.inter.ClassesScanner;
import live.midreamsheep.frame.sioc.scan.parse.clazz.ClassMetaDefinition;
import lombok.AllArgsConstructor;

import java.util.Set;

/**
 * 默认通过包扫描器实现对对象定义的扫描加解析
 * 先通过{@link ClassesScanner}获取所有的类，再通过{@link ClassParserToDefinition}解析成对象定义
 * @author midreamsheep
 * @since 2023/10/28
 * */
@AllArgsConstructor
public class PackageBeanDefinitionsFactory implements BeanHandlerFactory {

    private ClassesScanner classesScanner;
    private ClassParserToDefinition definitionsParser;

    /**
     * 增加一个扫描器
     * */
    public void addClassesScanner(ClassesScanner classesScanner) {
        this.classesScanner.next(classesScanner);
    }

    @Override
    public HandlerManager generateHandlerManager() {
        return definitionsParser.parse(classesScanner.scan());
    }
}
