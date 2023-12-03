package live.midreamsheep.sioc.test.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Injector;
import lombok.Getter;

@Getter
@Comment
public class B {
    AInter a;

    @Injector
    public B(AInter a) {
        this.a = a;
    }
}
