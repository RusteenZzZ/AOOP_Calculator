package client;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.util.LinkedHashMap;

import static client.KeyboardButtonType.*;

public class Keyboard extends JPanel {
  private ButtonClickListener listener;
  private LinkedHashMap<String, KeyboardButtonType> buttonsData = new LinkedHashMap<String, KeyboardButtonType>() {
    {
      put("AC", CLEAR);
      put("C", DELETE);
      put("(", OPERATION);
      put(")", OPERATION);
      put("+", OPERATION);
      put("-", OPERATION);
      put("/", OPERATION);
      put("x", OPERATION);
      put("%", OPERATION);
      put("1", OPERAND);
      put("2", OPERAND);
      put("3", OPERAND);
      put("4", OPERAND);
      put("5", OPERAND);
      put("6", OPERAND);
      put("7", OPERAND);
      put("8", OPERAND);
      put("9", OPERAND);
      put("0", OPERAND);
      put("=", EQUAL);
    }
  };

  public Keyboard() {
    GridLayout layout = new GridLayout(5, 4);
    layout.setHgap(2);
    layout.setVgap(2);
    this.setLayout(layout);

    this.buttonsInit();
  }

  public void buttonsInit() {
    buttonsData.forEach((label, type) -> {
      String text = label;

      KeyboardButton btn = new KeyboardButton(label, text, type);

      btn.setFont(new Font("Calibri", Font.PLAIN, 35));
      btn.setFocusPainted(false);
      btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      btn.setForeground(new Color(0, 0, 0));

      if (type == OPERATION) {
        btn.setBackground(new Color(150, 150, 244));
      } else if (type == OPERAND) {
        btn.setBackground(new Color(241, 243, 244));
      } else if (type == EQUAL) {
        btn.setBackground(new Color(0, 100, 0));
      } else if (type == DELETE){
        btn.setBackground(new Color(240, 100, 30));
      } else if (type == CLEAR){
        btn.setBackground(new Color(200, 50, 50));
      }

      btn.addActionListener(ev -> {
        ButtonClickEvent e = new ButtonClickEvent(btn, label, text, type);
        this.listener.ButtonClickEventOccurred(e);
      });

      this.add(btn);
    });
  }

  /**
   * @param listener the listener to set
   */
  public void setListener(ButtonClickListener listener) {
    this.listener = listener;
  }
}
