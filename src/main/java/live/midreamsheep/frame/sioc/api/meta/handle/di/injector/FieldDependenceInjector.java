package live.midreamsheep.frame.sioc.api.meta.handle.di.injector;

import live.midreamsheep.frame.sioc.api.meta.ApplicationContext;
import live.midreamsheep.frame.sioc.api.meta.handle.di.DependenceInjector;
import lombok.Setter;

import java.lang.reflect.Field;

public class FieldDependenceInjector extends DependenceInjector {

    private final Field field;

    @Setter
    private String dependenceName;
    @Setter
    private Class<?> dependenceClass;

    public FieldDependenceInjector(Field field) {
        this.field = field;
    }

    @Override
    public boolean inject(ApplicationContext context) {
        //对field进行注入
        field.setAccessible(true);
        try {
            Object dependence;
            if (!dependenceName.isEmpty()) {
                dependence = context.getBean(dependenceName, dependenceClass);
            } else {
                dependence = context.getBean(dependenceClass);
            }
            field.set(bean, dependence);
            if (field.get(bean) != null) {
                return true;
            }
        } catch (IllegalAccessException e) {
            //TODO
        }
        return false;
    }

}
