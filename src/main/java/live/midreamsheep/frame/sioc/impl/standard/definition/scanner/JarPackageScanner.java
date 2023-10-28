package live.midreamsheep.frame.sioc.impl.standard.definition.scanner;

import live.midreamsheep.frame.sioc.api.scanner.ClassesAbstractScanner;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class JarPackageScanner extends ClassesAbstractScanner {

    private String basePackage;

    @Override
    public Set<Class<?>> doScan() {
        return null;
    }

}
