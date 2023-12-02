package live.midreamsheep.frame.sioc.api.handle;

/**
 *
 * */
public enum HandlerLevel {
    /**bean生成等handle*/
    GENERATE,
    /**对bean的修饰等功能，比如依赖注入属性*/
    DECORATE
    ;
}
