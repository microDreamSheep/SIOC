package live.midreamsheep.frame.sioc.di.bean.field;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.field.FieldInter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FieldInjectHandler extends AbstractContextHandler {

    private final long id;
    private final FieldInter fieldInter;

    @Override
    public void handle(ApplicationContext context) {

    }

}
