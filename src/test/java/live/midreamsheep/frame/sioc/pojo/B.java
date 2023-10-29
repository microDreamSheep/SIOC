package live.midreamsheep.frame.sioc.pojo;

import live.midreamsheep.frame.sioc.api.annotation.injector.Comment;
import live.midreamsheep.frame.sioc.api.annotation.injector.Injector;
import lombok.Getter;

@Service
public class B implements BInter {
    @Getter
    private String name = "hello world";

    @Injector
    @Getter
    private A a;
}
