package Serializator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Serialization implements ISerialize {

    private HashMap<Byte, String> codeOfTypeVariable = new HashMap<>(){};


    public <T extends Object> byte[] Serialize(T o){

        if (o == null)
            return new byte[]{(byte) '(', (byte) ')'};

        var result = new ByteArrayOutputStream();
        result.write((byte)'(');
        var oClass = o.getClass();
        var nameBytes = oClass.getName().getBytes();
        try {
            result.write(nameBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var field : oClass.getDeclaredFields()){
            field.setAccessible(true);
            var fieldType = field.getType();
            var fieldName = field.getName();

        }

        return o.toString().getBytes(StandardCharsets.UTF_8);
    }

    public <T extends Object> T Deserialize (byte[] raw){
        return null;
    }
    public void AddCustom(Serialization custom){

    }
}
