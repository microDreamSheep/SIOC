package live.midreamsheep.frame.sioc.scan.scanner;

import live.midreamsheep.frame.sioc.scan.inter.ClassesAbstractScanner;
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
