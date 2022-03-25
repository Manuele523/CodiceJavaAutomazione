import OggettoTest.ObjectClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

/*
 * @author m.montrasi
 * This class can be use for TEST to populate any object with fictitious values.
 *
 * obj in methods startAutomaticPopulation, is for example, new ObjectClass()
 * 'setBoolean' set the value of default Boolean
 *
 */
public class AutomaticPopulation {

    private enum TypeMatch {
        MAP, LIST
    }

    private static final String EMPTY_STRING = "", SINGLE_SPACE = " ", DOT = ".", COMMA = ",", STRING_LIST = "java.util.List", STRING_MAP = "java.util.Map", STRING_MINOR = "<", STRING_MAJOR = ">", OPEN_PARENTHESES = "(", CLOSE_PARENTHESES = ")";
    private static final Map<String, Class<?>> mapNoWrapper = new HashMap<>();
    private static final Map<TypeEnum, Object> mapEnumValue = new HashMap<>();

    @Test
    public void start() {
        System.out.println(startAutomaticPopulation(new ObjectClass()));
    }

    public <T> T startAutomaticPopulation(T object) {
        setDefaultValue();
        populateDynamicObject(mapMethodsAndFieldsOfClass(object), object, object.getClass());
        return object;
    }

    private void setDefaultValue() {
        mapNoWrapper.put("int", int.class);
        mapNoWrapper.put("boolean", boolean.class);
        mapNoWrapper.put("long", long.class);
        mapNoWrapper.put("double", double.class);
        mapNoWrapper.put("byte[]", byte[].class);

        mapEnumValue.put(TypeEnum.DATE, new Date(0L));
        mapEnumValue.put(TypeEnum.BIGDECIMAL, new BigDecimal(1));
        mapEnumValue.put(TypeEnum.STRING, "9999");
        mapEnumValue.put(TypeEnum.DOUBLE, 5.5);
        mapEnumValue.put(TypeEnum.LONG, 1L);
        mapEnumValue.put(TypeEnum.INTEGER, 2);
        mapEnumValue.put(TypeEnum.SHORT, (short) 3);
        mapEnumValue.put(TypeEnum.BOOLEAN, false);
        mapEnumValue.put(TypeEnum.BYTE, "byteArray".getBytes());
    }

    private Map<String, Object> mapMethodsAndFieldsOfClass(Object object) {
        Map<String, Object> map = new HashMap<>();
        List<String> mthSet = new ArrayList<>();

        Arrays.stream(object.getClass().getMethods()).forEach(method -> {
            if (isSetter(method))
                mthSet.add(replaceListOrMapInMethod(method.toString().substring(method.toString().lastIndexOf(".set") + 1).trim(), method));
        });

        mthSet.forEach(method -> {
            String key = method.substring(0, method.lastIndexOf(OPEN_PARENTHESES));
            key = key.substring(0, 1).toLowerCase().concat(key.substring(1));
            String type = method.substring(method.lastIndexOf(OPEN_PARENTHESES) + 1, method.lastIndexOf(CLOSE_PARENTHESES));
            List<String> list = new ArrayList<>();

            if (type.contains(COMMA) && !type.contains(STRING_MAP)) {
                Arrays.stream(type.split(COMMA)).forEach(str -> {
                    if (!EMPTY_STRING.equals(str))
                        list.add(str.trim());
                });
            }
            map.put(key, !list.isEmpty() ? list : type);
        });
        return map;
    }

    private void populateDynamicObject(Map<String, Object> setters, Object obj, Class<?> clazz) {
        setters.forEach((key, type) -> {
            try {
                String strType = type.toString(), param1 = strType, param2 = null;
                Method method;
                TypeMatch typeMatch = null;
                if (type.equals(EMPTY_STRING)) {
                    method = clazz.getMethod(key);
                    method.invoke(obj);
                } else {
                    if (strType.contains(STRING_MAP)) {
                        String[] strSplit = strType.split(COMMA + SINGLE_SPACE);
                        typeMatch = TypeMatch.MAP;
                        param1 = replaceMinorAndMajorInListOrMap(strSplit[0], TypeMatch.MAP);
                        param2 = replaceMinorAndMajorInListOrMap(strSplit[1], TypeMatch.MAP);
                    } else if (strType.contains(STRING_LIST)) {
                        typeMatch = TypeMatch.LIST;
                        param1 = replaceMinorAndMajorInListOrMap(strType, TypeMatch.LIST);
                    }
                    Class<?> fnClazz = strType.contains(STRING_MAP)
                            ? Class.forName(STRING_MAP) : strType.contains(STRING_LIST)
                            ? Class.forName(STRING_LIST) : (mapNoWrapper.containsKey(strType)
                            ? mapNoWrapper.get(strType) : Class.forName(strType));

                    method = clazz.getMethod(key, fnClazz);
                    method.setAccessible(true);
                    checkType(param1, param2, method, obj, typeMatch);
                }
            } catch (Exception e) {
                System.out.println("Error during the method 'populateDynamicObject' with error: " + e.getMessage());
            }
        });
    }

    private void checkType(String param1, String param2, Method method, Object obj, TypeMatch type) {
        try {
            String strParam1 = param1.substring(param1.lastIndexOf(DOT) + 1);
            String strParam2 = param2 != null ? param2.substring(param2.lastIndexOf(DOT) + 1) : EMPTY_STRING;
            Class<?> clazzParam1 = mapNoWrapper.containsKey(param1) ? null : Class.forName(param1);
            Class<?> clazzParam2 = param2 == null || mapNoWrapper.containsKey(param2) ? null : Class.forName(param2);

            TypeEnum enume = TypeMatch.MAP.equals(type) ? TypeEnum.isMAP : TypeEnum.checkTypeEnum(strParam1, clazzParam1);
            switch (enume) {
                case STRING:
                case DATE:
                case INTEGER:
                case DOUBLE:
                case BYTE:
                case LONG:
                case BIGDECIMAL:
                case SHORT:
                case BOOLEAN:
                    method.invoke(obj, TypeMatch.LIST.equals(type) ? Collections.singletonList(mapEnumValue.get(enume)) : mapEnumValue.get(enume));
                    break;
                case isMAP:
                    Object key = TypeEnum.isOBJECT.equals(TypeEnum.checkTypeEnum(strParam1, clazzParam1))
                            ? startAutomaticPopulation(Objects.requireNonNull(clazzParam1).newInstance()) : mapEnumValue.get(TypeEnum.checkTypeEnum(strParam1, clazzParam1));
                    Object value = TypeEnum.isOBJECT.equals(TypeEnum.checkTypeEnum(strParam2, clazzParam2))
                            ? startAutomaticPopulation(Objects.requireNonNull(clazzParam2).newInstance()) : mapEnumValue.get(TypeEnum.checkTypeEnum(strParam2, clazzParam2));
                    method.invoke(obj, Collections.singletonMap(key, value));
                    break;
                case isENUM:
                    Object[] values = Objects.requireNonNull(clazzParam1).getEnumConstants();
                    Object firstValue = Arrays.stream(values).findFirst().orElse(null);
                    method.invoke(obj, TypeMatch.LIST.equals(type) ? Arrays.asList(values) : firstValue);
                    break;
                case isOBJECT:
                    Object newObj = startAutomaticPopulation(Objects.requireNonNull(clazzParam1).newInstance());
                    method.invoke(obj, TypeMatch.LIST.equals(type) ? Collections.singletonList(clazzParam1.cast(newObj)) : clazzParam1.cast(newObj));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error during the method 'checkType' with error: " + e.getMessage());
        }
    }

    private String replaceListOrMapInMethod(String mth, Method method) {
        return mth.toUpperCase().contains(TypeMatch.LIST.toString()) ? mth.replace(STRING_LIST, method.getGenericParameterTypes()[0].toString()) : mth.toUpperCase().contains(TypeMatch.MAP.toString()) ? mth.replace(STRING_MAP, method.getGenericParameterTypes()[0].toString()) : mth;
    }

    private String replaceMinorAndMajorInListOrMap(String strClean, TypeMatch typeMatch) {
        if (strClean.contains(STRING_MINOR)) {
            strClean = strClean.replace((TypeMatch.LIST.equals(typeMatch) ? STRING_LIST : STRING_MAP) + STRING_MINOR, EMPTY_STRING);
        }
        if (strClean.contains(STRING_MAJOR)) {
            strClean = strClean.replace(STRING_MAJOR, EMPTY_STRING);
        }
        return strClean;
    }

    private boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers()) && void.class.equals(method.getReturnType()) && method.getParameterTypes().length == 1 && (method.getName().matches("^set[A-Z].*") || method.getName().matches("^set[a-z].*"));
    }

}
