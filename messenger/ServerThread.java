package messenger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private int ID;

    ServerThread(Socket socket) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());

        this.ID = (int)inputStream.readObject();
        int i = 0, size = Server.getServerThreads().size();

        for(; i < size; i++) {
            if(Server.getServerThreads().get(i).getKey() == this.ID)
                break;
        }
        if(i == size) {
            Server.getServerThreads().add(new OrderedPair<>(this.ID, this));

            for(int j = 0; j < size; j++) {
                OrderedPair<Integer, ServerThread> temp = Server.getServerThreads().get(j);
                // Wysyłanie do innych watkow ID tego klienta
                temp.getValue().outputStream.writeObject(new Message(this.ID, temp.getKey(), true, ""));

                // Wysyłanie do siebie samego
                outputStream.writeObject(new Message(temp.getKey(), this.ID, true, ""));
            }

            outputStream.writeObject(new Message(this.ID, this.ID, true, ""));
        }
        else {
            Server.getServerThreads().get(i).setValue(this);

            for(int j = 0; j < size; j++) {
                outputStream.writeObject(new Message(Server.getServerThreads().get(j).getKey(), this.ID, true, ""));
            }
        }
    }

    @Override
    public void run() {
        Message m;
        // Odczytanie wszystkich buforowanych wiadomości
        try {
            int i = 0;
            while(i < Server.getBufferedMessages().size()) {
                if(Server.getBufferedMessages().get(i).getKey() == this.ID) {
                    this.outputStream.writeObject(new Message(Server.getBufferedMessages().get(i).getValue()));
                    Server.getBufferedMessages().remove(i);
                } else i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                m = (Message) inputStream.readObject();

                int size = Server.getServerThreads().size();
                for(int i = 0; i < size; i++) {
                    if(m.getReceiverID() == Server.getServerThreads().get(i).getKey()) {
                        if(Server.getServerThreads().get(i).getValue() != null)
                            Server.getServerThreads().get(i).getValue().outputStream.writeObject(m);

                        else Server.getBufferedMessages().add(new OrderedPair<>(m.getReceiverID(), m));
                        break;
                    }
                }
            }
        } catch (EOFException | SocketException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < Server.getServerThreads().size(); i++) {
            if(Server.getServerThreads().get(i).getKey() == this.ID) {
                Server.getServerThreads().get(i).setValue(null);
                break;
            }
        }

        try {
            inputStream.close();
            if (outputStream != null) {
                outputStream.close();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
