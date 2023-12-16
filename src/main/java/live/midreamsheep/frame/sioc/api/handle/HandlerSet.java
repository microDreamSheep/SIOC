package live.midreamsheep.frame.sioc.api.handle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 存储ContextHandler的封装结构
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerSet {
    private ContextHandler[] contextHandlers;
}
