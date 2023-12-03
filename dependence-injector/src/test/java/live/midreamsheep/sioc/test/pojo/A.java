package live.midreamsheep.sioc.test.pojo;

import live.midreamsheep.frame.sioc.di.annotation.basic.bean.Comment;

@Comment
public class A implements AInter {
    public void say(){
        System.out.println("A");
    }
}
