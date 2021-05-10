package ThreadDispather;

import FileWorker.CommandFactory;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class MD5HashTask extends ThreadedTask {
    Socket socket;
    String filename;
    public MD5HashTask(String filename, Socket socket) {
        this.filename = filename;
        this.socket = socket;
        start();
    }

    @Override
    public void start() {
        try {
            CommandFactory.createInstance(CommandFactory.Commands.MD5Hash, socket).start(filename);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "MD5 hash task";
    }
}
