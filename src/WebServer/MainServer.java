package WebServer;

public class MainServer {

    public enum Status {
        Stopped,
        Active,
        NotCreated
    }

    public static WebServer webServer;
    private static WebServer httpServer;

    public MainServer(int port){
        webServer = new WebServer(port);
    }

    public void startServer() {
        webServer.startServer();
    }

    public static void CreateHTTPServer() {
        if (httpServer == null)
        {
            synchronized (webServer) {
                if (httpServer == null) {
                    httpServer = new WebServer(8080);
                    httpServer.startServer();
                }
            }
        }
    }

    public static Status getHTTPServerStatus() {
        if (httpServer != null)
            return httpServer.isStopped ? Status.Stopped : Status.Active;
        else
            return Status.NotCreated;
        }

    public static void setHTTPServerStatus(Status status) {
        if (httpServer != null)
            switch (status) {
                case Stopped:
                    httpServer.isStopped = true;
                    break;

                case Active:
                    httpServer.isStopped = false;
                    break;

                default:
                    break;
            }
    }
}
