package live.midreamsheep.frame.sioc.di.bean.field;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.api.handle.HandlerLevel;
import live.midreamsheep.frame.sioc.scan.clazz.field.FieldInter;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;

@AllArgsConstructor
public class FieldInjectHandler extends AbstractContextHandler {

    private final long id;
    private final FieldInter fieldInter;

    @Override
    public void handle(ApplicationContext context) {
        Object bean = context.getBean(id);
        //通过反射注入数据
        Field field = fieldInter.getField();
        field.setAccessible(true);
        try {
            field.set(bean,context.getBean(fieldInter.getFieldType()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HandlerLevel getHandlerLevel() {
        return HandlerLevel.DECORATE;
    }
}
