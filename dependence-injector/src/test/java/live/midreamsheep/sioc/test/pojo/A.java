package live.midreamsheep.sioc.test.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Injector;
import lombok.Getter;

@Comment
@Getter
public class A implements AInter {

    @Injector
    B b;
    public void say(){
        System.out.println("A");
    }
}
