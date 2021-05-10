package test;

import ThreadDispather.ListTask;
import ThreadDispather.MD5HashTask;
import org.junit.Test;

import java.net.Socket;


class InfoServerTaskTest {

    @Test
    public static void main(String[] args) {
        var socket = new Socket();
        //var list = new ListTask(socket);
        var md5 = new MD5HashTask("file.txt", socket);
    }
}