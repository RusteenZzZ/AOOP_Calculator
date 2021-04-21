package client;

import java.util.EventListener;

public interface InputChangeListener extends EventListener {
    public void inputChangeEventOccurred(InputChangeEvent e);

}