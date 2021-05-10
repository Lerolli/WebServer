package ThreadDispather;

import WebServer.MainServer;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class InfoServerTask extends ThreadedTask {
    private final Socket socket;
    public InfoServerTask(Socket client) {
        socket = client;
    }

    @Override
    public void start() {
        try {
            while (true) {
                StringBuilder builder = new StringBuilder();
                byte[] data = new byte[1024];
                int bytes = socket.getInputStream().read(data);
                builder.append(new String(data, StandardCharsets.UTF_8), 0, bytes);
                String[] command = builder.toString().toLowerCase().split(" ");

                switch (command[0]) {

                    // Показывает список всех файлов в директории
                    case "list":
                        ThreadDispatcher.getInstance().addInQueue(new ListTask(socket));
                        break;

                    // Вычисляет hash выбранного файла
                    case "hash":
                        if (command.length > 1) {
                            ThreadDispatcher.getInstance().addInQueue(new MD5HashTask(command[1], socket));
                            break;
                        }
                    // Вычисляет размер выбранного файла
                    case "size":
                        if (command.length > 1) {
                            ThreadDispatcher.getInstance().addInQueue(new SizeTask(command[1], socket));
                            break;
                        }

                    // Пишет статут html сервера
                    case "status":
                        var status = MainServer.getHTTPServerStatus().toString();
                        socket.getOutputStream().write(status.getBytes(StandardCharsets.UTF_8));
                        break;
                    // Останавливает html сервер
                    case "stop":
                        var message = "Server stopped";
                        MainServer.setHTTPServerStatus(MainServer.Status.Stopped);
                        socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
                        break;
                    // Запускает html сервер
                    case "start":
                        MainServer.CreateHTTPServer();
                        MainServer.setHTTPServerStatus(MainServer.Status.Active);
                        socket.getOutputStream().write("Server started".getBytes(StandardCharsets.UTF_8));
                        break;

                    // Закрывает поток с клиентом
                    case "exit":
                        var m = "Goodbye!";
                        socket.getOutputStream().write(m.getBytes(StandardCharsets.UTF_8));
                        socket.shutdownOutput();
                        socket.shutdownInput();
                        break;
                    default:
                        var error = "Error: Command not found";
                        socket.getOutputStream().write(error.getBytes(StandardCharsets.UTF_8));
                        break;
                }
            }
        } catch (IOException ignored) {
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {ex.printStackTrace(); }
        }
    }

    @Override
    public String getName() {
        return "InfoServer Task";
    }
}
