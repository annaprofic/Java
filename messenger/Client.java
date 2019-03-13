package messenger;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends JFrame {

    private final int ID;
    private int toID;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;

    private TextPanel textPanel;
    private JTextArea chatArea;
    private JList<String> list;

    private Client(int id) throws IOException {
        this.toID = id;
        this.ID = id;

        this.setTitle("Client " + ID);
        this.setSize(500, 400);
        this.setLayout(null);

        JLabel usersLabel = new JLabel("Users");
        JButton sendButton = new JButton("Send");
        socket = new Socket("localhost", Server.PORT);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        chatArea = new JTextArea();
        textPanel = new TextPanel(chatArea);
        list = new JList<>();
        usersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setBounds(0, 0, 375, 310);
        sendButton.setBounds(385, 330, 100, 40);
        textPanel.setBounds(0, 0, 385, 380);
        textPanel.getTextField().setBounds(2, 330, 381, 40);
        textPanel.getScrollPane().setBounds(5, 5, 375, 315);
        list.setBounds(385, 35, 100, 285);
        usersLabel.setBounds(385, 5, 100, 25);

        this.add(sendButton);
        this.add(textPanel);
        this.add(list);
        this.add(usersLabel);

        // Wysyla ID do ServerThreada
        objectOutputStream.writeObject(ID);

        // Uruchomienie watku readera
        ClientReader reader = new ClientReader(this);
        reader.start();

        list.getSelectionModel().addListSelectionListener(e -> {
            String temp;
            if((temp = list.getSelectedValue()) != null)
                    toID = Integer.parseInt(temp);
            else this.toID = this.ID;
        });

        sendButton.addActionListener(e -> {
            String temp = textPanel.getTextField().getText();

            if (!temp.equals("")) {
                Message message = new Message(ID, toID, false, temp);

                textPanel.getTextField().setText("");
                try {
                    if (this.ID != message.getReceiverID()) chatArea.append(this.ID + ": " + message.toString() + "\n");
                    objectOutputStream.writeObject(message);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    Socket getSocket() {
        return socket;
    }

    JTextArea getChatArea() {
        return chatArea;
    }

    JList<String> getList() {
        return list;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("USAGE > java messenger/Client [id]");
            System.exit(-1);
        }

        SwingUtilities.invokeLater(() -> {
            try {
                new Client(Integer.parseInt(args[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}