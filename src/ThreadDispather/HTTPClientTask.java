package ThreadDispather;

import java.net.Socket;

public class HTTPClientTask extends ThreadedTask {

    Socket socket;
    public HTTPClientTask(Socket client) {
        socket = client;
    }

    @Override
    public void start() {
        // TODO: 10.05.2021 Сделать HTTP файловый проводник
    }

    @Override
    public String getName() {
        return "Http Client task";
    }
}
