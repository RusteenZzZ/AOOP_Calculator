package client;

import java.util.EventListener;

public interface ButtonClickListener extends EventListener {
    public void ButtonClickEventOccurred(ButtonClickEvent e);
}