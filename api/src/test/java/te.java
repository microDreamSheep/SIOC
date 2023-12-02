import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class te {
    @Test
    public void a(){
        String[] asd = new String[]{
                "hello",
                "dawd"
        };
        List<String> collect = Arrays.stream(asd).collect(Collectors.toList());
        collect.remove(0);
        System.out.println(collect);
    }
}
