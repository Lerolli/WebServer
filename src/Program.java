import WebServer.MainServer;

public class Program {

    public static void main(String[] args){
        var server = new MainServer(8081);
        server.startServer();
    }
}