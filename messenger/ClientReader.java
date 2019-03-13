package messenger;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

public class ClientReader extends Thread {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private JTextArea chatArea;
    private JList<String> list;
    private DefaultListModel<String> dm;

    ClientReader(Client client) throws IOException {
        this.list = client.getList();
        this.inputStream = new ObjectInputStream(client.getSocket().getInputStream());
        this.outputStream = client.getObjectOutputStream();
        this.chatArea = client.getChatArea();
        this.dm = new DefaultListModel<>();

        client.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    @Override
    public void run() {
        Message message;

        try {
            while(true) {
                message = (Message) inputStream.readObject();
                if(message.isNewID) {
                    dm.addElement(String.valueOf(message.getSenderID()));
                    list.setModel(dm);
                } else chatArea.append(message.getSenderID() + ": " + message.toString() + "\n");
            }
        } catch(SocketException e) {
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}