package live.midreamsheep.frame.sioc.api.handle;

/**
 * HandlerLevel,用于对handler排序时进行标记
 * Generate先于Decorate
 * */
public enum HandlerLevel {
    /**bean生成等handle*/
    GENERATE,
    /**对bean的修饰等功能，比如依赖注入属性*/
    DECORATE
    ;
}
