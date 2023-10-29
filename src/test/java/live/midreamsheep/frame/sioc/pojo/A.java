package live.midreamsheep.frame.sioc.pojo;

import live.midreamsheep.frame.sioc.api.annotation.injector.Comment;
import live.midreamsheep.frame.sioc.api.annotation.injector.Injector;
import lombok.Getter;

@Comment
public class A {
    @Injector
    @Getter
    private BInter b;

    @Getter
    private String name = "hello world---A";
}
