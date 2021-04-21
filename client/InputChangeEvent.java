package client;

import java.util.EventObject;
import java.awt.event.KeyEvent;

public class InputChangeEvent extends EventObject {
    private KeyEvent e;

    public InputChangeEvent(Object source, KeyEvent e) {
        super(source);
        this.e = e;
    }

    public KeyEvent getEvent() {
        return this.e;
    }

    public Object getSource() {
        return super.getSource();
    }
}