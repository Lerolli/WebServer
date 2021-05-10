package FileWorker;

import java.net.Socket;

public class CommandFactory {

    public enum Commands {
        MD5Hash,
        Size,
        List,
    }

    public static ICommand createInstance(Commands command, Socket s) {
        return switch (command){
            case List -> new FileCommand(new ListExecutor(), true, new WebResulter(s));
            case MD5Hash -> new FileCommand(new Md5Executor(), false, new WebResulter(s));
            case Size -> new FileCommand(new SizeExecutor(),true, new WebResulter(s));
        };
    }
}
