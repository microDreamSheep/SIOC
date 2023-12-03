package live.midreamsheep.sioc.test.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Injector;
import lombok.Getter;

@Comment
public class B {
    @Injector
    @Getter
    AInter a;
}
