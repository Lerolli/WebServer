package Serializator;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class Converter {

    private static HashMap<String, Integer> codeOfTypeVariable = new HashMap<>(){{
        put("int", 1);
        put("byte", 2);
        put("short", 3);
        put("long", 4);
        put("double", 5);
        put("char", 6);
        put("bool", 7);
        put("java.lang.String", 8);
        put("float", 9);
    }};

    public static HashMap<String, Integer> lengthType = new HashMap<>() {{
        put("int", 4);
        put("byte", 1);
        put("short", 2);
        put("long", 8);
        put("double", 8);
        put("char", 1);
        put("bool", 4);
        put("float", 4);
    }};

    public static byte[] convertTypeToByte (String type){
        if (codeOfTypeVariable.containsKey(type)){
            switch (codeOfTypeVariable.get(type)){
                case 1:{
                    return  ByteBuffer.allocate(4).putInt(1).array();
                }

                case 2:{
                    return  ByteBuffer.allocate(4).putInt(2).array();
                }

                case 3:{
                    return  ByteBuffer.allocate(4).putInt(3).array();
                }

                case 4:{
                    return  ByteBuffer.allocate(4).putInt(4).array();
                }

                case 5:{
                    return  ByteBuffer.allocate(4).putInt(5).array();
                }

                case 6:{
                    return  ByteBuffer.allocate(4).putInt(6).array();
                }
                case 7:{
                    return ByteBuffer.allocate(4).putInt(7).array();
                }
                case 8:{
                    return ByteBuffer.allocate(4).putInt(8).array();
                }

                case 9:{
                    return ByteBuffer.allocate(4).putInt(9).array();
                }
                default:
                    return null;
            }
        }
        return null;
    }


    public static String byteToString (byte[] bytes){
        String str = "";
        try {
            str = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    };

    public static int byteToInt (byte[] bytes){
        return ByteBuffer.wrap(bytes).getInt();
    }
}
