package messenger;

import javax.swing.*;

class TextPanel extends JPanel {
    private JTextField textField;
    private JScrollPane scrollPane;

    TextPanel(JTextArea chatArea){
        scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        textField = new JTextField();
        setLayout(null);
        add(textField);
        add(scrollPane);
    }

    JTextField getTextField() {
        return this.textField;
    }

    JScrollPane getScrollPane() {
        return this.scrollPane;
    }
}