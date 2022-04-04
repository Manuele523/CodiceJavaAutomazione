import lombok.Getter;
import lombok.ToString;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;

@Getter
@ToString
public enum TypeEnum {
    STRING("string"),
    DATE("date"),
    INTEGER("integer"),
    INT("int"),
    DOUBLE("double"),
    LONG("long"),
    BIGDECIMAL("bigdecimal"),
    SHORT("short"),
    BOOLEAN("boolean"),
    BYTE("byte"),
    NULL("null"),
    isMAP("map"),
    isENUM("isenum"),
    isOBJECT("isobject");

    private String enume;
    private static List<String> lsNoWrapper = new ArrayList<>();

    TypeEnum(String name) {
        this.enume = name;
    }

    private static void setDefaultValue(){
        lsNoWrapper.add("int");
        lsNoWrapper.add("boolean");
        lsNoWrapper.add("long");
        lsNoWrapper.add("double");
        lsNoWrapper.add("short");
        lsNoWrapper.add("byte[]");
    }

    public static TypeEnum checkTypeEnum(String chkEnum, Class<?> param1) throws ClassNotFoundException {
        setDefaultValue();
        if (lsNoWrapper.contains(chkEnum)) {
            return chkEnum.contains("[]") ? TypeEnum.valueOf("BYTE") : TypeEnum.valueOf(chkEnum.toUpperCase());
        }
        for (TypeEnum myEnum : TypeEnum.values()) {
            if (param1 != null) {
                if (myEnum.getEnume().equalsIgnoreCase(chkEnum.toLowerCase())) {
                    return myEnum;
                } else if (param1.isEnum()) {
                    return isENUM;
                } else {
                    List<Class<?>> lsClass = getAllClass();
                    if (!lsClass.contains(param1)) {
                        return isOBJECT;
                    }
                }
            }
        }
        return NULL;
    }

    private static List<Class<?>> getAllClass() {
        List<Class<?>> lsClass = new ArrayList<>();

        lsClass.add(String.class);
        lsClass.add(Date.class);
        lsClass.add(Integer.class);
        lsClass.add(Long.class);
        lsClass.add(BigDecimal.class);
        lsClass.add(Short.class);
        lsClass.add(Boolean.class);
        lsClass.add(XMLGregorianCalendar.class);
        lsClass.add(Map.class);
        lsClass.add(Number.class);
        lsClass.add(Double.class);
        lsClass.add(Byte.class);

        return lsClass;
    }
}
