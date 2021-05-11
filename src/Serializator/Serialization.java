package Serializator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Serialization implements ISerialize {

    public <T> byte[] Serialize(T o){

        if (o == null)
            return new byte[]{(byte) '(', (byte) ')'};

        var result = new ByteArrayOutputStream();

        try {

        // ( - начало массива байт, ) - конец массива байт
        result.write((byte)'(');

        // Записываем длину имени класса и имя класса
        var oClass = o.getClass();
        var nameBytes = oClass.getName().getBytes();
        result.write(oClass.getName().length());
        result.write(nameBytes);

        // Проходимся по полям класса
        for (var field : oClass.getDeclaredFields()){
            field.setAccessible(true);

            // Записываем длину имени типа переменной и тип переменной
            var fieldType = field.getType();
            var fieldTypeName = fieldType.getName();
            var TypeToByte = Converter.convertTypeToByte(fieldTypeName);
            if (TypeToByte != null)
                result.write(TypeToByte);
            else
                result.write(Serialize(field.get(o)));
            // Записываем длину имени переменной и имя переменной
            var fieldNameBytes = field.getName().getBytes(StandardCharsets.UTF_8);
            result.write(field.getName().length());
            result.write(fieldNameBytes);

            var valueField = field.get(o);
            if (valueField != null){
                if (Converter.lengthType.containsKey(valueField)) {
                    result.write(Converter.lengthType.get(valueField));
                    result.write((byte[]) valueField);
                }
            }
        }

        result.write(new byte[] {(byte)')'});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result.toByteArray();
    }

    public <T extends Object> T Deserialize (byte[] raw) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (raw.length == 2)
            return null;
        var classBytes = Arrays.copyOfRange(raw, 1, raw.length - 1);
        var nameLengthBytes = Arrays.copyOfRange(classBytes, 0, 4);
        int nameLength = Converter.byteToInt(nameLengthBytes);

        String className = Converter.byteToString(nameLengthBytes);

        Class<?> classObj = Class.forName(className);

        Object object = classObj.getConstructor().newInstance();

        int i = 4 + nameLength;

        return (T) object;
    }
    public void AddCustom(Serialization custom){

    }
}
