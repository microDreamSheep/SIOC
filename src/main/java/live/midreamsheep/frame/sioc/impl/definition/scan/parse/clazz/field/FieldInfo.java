package live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.field;

import live.midreamsheep.frame.sioc.impl.definition.scan.parse.clazz.field.field.NormalField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldInfo {

    private final Map<String, FieldInter> fieldInterMap = new HashMap<>();

    public void init(Class<?> aClass){
        getFields(aClass).forEach(this::addFieldInter);
    }

    public void addFieldInter(FieldInter fieldInter){
        fieldInterMap.put(fieldInter.getFieldName(), fieldInter);
    }

    public FieldInter getFieldInter(String name){
        return fieldInterMap.get(name);
    }

    public List<FieldInter> getFieldInterList(){
        return new ArrayList<>(fieldInterMap.values());
    }

    private List<FieldInter> getFields(Class<?> aClass){
        List<FieldInter> fieldInterList = new ArrayList<>();
        for (Field field : aClass.getFields()) {
            fieldInterList.add(new NormalField(field));
        }
        return fieldInterList;
    }


}
