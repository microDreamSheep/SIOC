package live.midreamsheep.frame.sioc.di.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreBeanDefinition implements BeanDefinition{
    private String beanName;
    private BeanScope scope;
    private Class<?> beanClass;
    private long beanId;
}
