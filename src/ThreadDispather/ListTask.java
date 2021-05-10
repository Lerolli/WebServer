package ThreadDispather;

import FileWorker.CommandFactory;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class ListTask extends ThreadedTask {
    Socket socket;

    public ListTask(Socket socket){
        this.socket = socket;
        start();
    }


    @Override
    public void start() {
        try {
            CommandFactory.createInstance(CommandFactory.Commands.List, socket).start();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "ListTask";
    }
}
