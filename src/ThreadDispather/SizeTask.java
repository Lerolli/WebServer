package ThreadDispather;

import FileWorker.CommandFactory;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class SizeTask extends ThreadedTask {
    Socket socket;
    String filename;
    public SizeTask(String filename, Socket socket) {
        this.filename = filename;
        this.socket = socket;
        start();
    }

    @Override
    public void start() {
        try {
            CommandFactory.createInstance(CommandFactory.Commands.Size, socket).start(filename);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "size task";
    }
}
