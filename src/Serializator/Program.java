package Serializator;

import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var serialization = new Serialization();
        var packet = new Packet();
        var serializeResult = serialization.Serialize(packet);
        var deserializeResult = serialization.Deserialize(serializeResult);
        System.out.println(packet.equals(deserializeResult));
    }


}
