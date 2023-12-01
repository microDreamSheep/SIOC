package live.midreamsheep.frame.sioc.api.handle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerManager {
    private ContextHandler[] contextHandlers;
}
