package messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static final int PORT = 10003;
    private static ArrayList<OrderedPair<Integer, ServerThread>> serverThreads = new ArrayList<>();
    private static ArrayList<OrderedPair<Integer, Message>> bufferedMessages = new ArrayList<>();

    static ArrayList<OrderedPair<Integer, ServerThread>> getServerThreads() {
        return serverThreads;
    }

    static ArrayList<OrderedPair<Integer, Message>> getBufferedMessages() {
        return bufferedMessages;
    }

    public static void main(String[] args) {
        runServer();
    }

    private static void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("Server is up & ready to go");
            Socket socket;

            while(true) {
                socket = serverSocket.accept();
                System.out.println("New client request received : " + socket);

                ServerThread serverThread = new ServerThread(socket);

                Thread thread = new Thread(serverThread);

                thread.start();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}