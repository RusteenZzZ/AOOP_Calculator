package client;

import java.util.EventObject;

public class ButtonClickEvent extends EventObject {
    private String label, text;
    private KeyboardButtonType type;

    public ButtonClickEvent(Object source, String label, String text, KeyboardButtonType type) {
        super(source);
        this.label = label;
        this.text = text;
        this.type = type;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the type
     */
    public KeyboardButtonType getType() {
        return type;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
}