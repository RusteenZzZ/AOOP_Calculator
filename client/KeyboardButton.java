package client;

import javax.swing.JButton;

public class KeyboardButton extends JButton {
    private String text;
    private KeyboardButtonType type;

    public KeyboardButton(String label, KeyboardButtonType type) {
        super(label);
        this.type = type;
        this.text = label;
    }

    public KeyboardButton(String label, String text, KeyboardButtonType type) {
        super(label);
        this.type = type;
        this.text = text;
    }

    /**
     * @return the type
     */
    public KeyboardButtonType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(KeyboardButtonType type) {
        this.type = type;
    }
}