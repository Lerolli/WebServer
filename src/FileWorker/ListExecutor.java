package FileWorker;

import java.io.File;
import java.security.NoSuchAlgorithmException;

public class ListExecutor implements IExecutable {

    @Override
    public String process(File f) {
        return f.getName();
    }
}
