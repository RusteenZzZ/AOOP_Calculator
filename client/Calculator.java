package client;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import services.Evaluate;

import static client.KeyboardButtonType.*;
import static utils.NodeType.isDouble;
import static utils.NodeType.isOperator;
import static utils.NodeType.isOperand;
import static utils.Operator.isBinaryOperator;
import static javax.swing.JOptionPane.*;

public class Calculator extends JFrame {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 500;

  public static boolean isResult = false;

  private ArrayList<String> expression;
  private Screen screen;

  public Calculator(Evaluate evaluate) {
    this.expression = new ArrayList<String>();

    this.setTitle("Calculator");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    this.setLayout(new BorderLayout());

    screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));

    screen.setListener(new InputChangeListener() {

      @Override
      public void inputChangeEventOccurred(InputChangeEvent ev) {
        KeyEvent e = ev.getEvent();
        char c = e.getKeyChar();
        String s = String.valueOf(c);
        int code = e.getKeyCode();

        if (Character.isDigit(c)) {
          handleAddition(s, OPERAND);
        } else if (isOperator(s)) {
          handleAddition(s, OPERATION);
        } else if (code == KeyEvent.VK_BACK_SPACE) {
          removeLast();
        } else if (code == KeyEvent.VK_ENTER) {
          handleSubmit(evaluate);
        }
        e.consume();
      }
    });

    this.add(screen, BorderLayout.PAGE_START);

    Keyboard keyboard = new Keyboard();
    keyboard.setPreferredSize(new Dimension(600, 350));
    keyboard.setListener(new ButtonClickListener() {

      @Override
      public void ButtonClickEventOccurred(ButtonClickEvent e) {
        KeyboardButtonType type = e.getType();
        String text = e.getText();

        if (type == EQUAL) {
          handleSubmit(evaluate);
        } else if (type == CLEAR) {
          handleClear();
        } else if(type == DELETE){
          removeLast();
        } else {
          handleAddition(text, type);
        }
      }
    });

    this.add(keyboard, BorderLayout.CENTER);

    this.pack();

    setLocationRelativeTo(null);
    setVisible(true);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int confirmed = showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit Program",
            YES_NO_OPTION);

        if (confirmed == YES_OPTION) {
          dispose();
        }
      }
    });
  }

  private void handleAddition(String text, KeyboardButtonType type) {
    if (isResult)
      handleClear();
    isResult = false;

    int length = expression.size();
    boolean added = false;
    boolean isOperatorChange = false;
    boolean isSpecialValueChange = false;
    boolean isForbiddenToEdit = false;
    String lastElement;
    
    // Forbidding adding sequential binary operators
    if(length > 0 && !isForbiddenToEdit){
      lastElement = expression.get(length - 1);
      if(isBinaryOperator(text) && isBinaryOperator(lastElement)){
        added = true;
        isOperatorChange = true;
        String currentText = screen.getText();
        String rem = expression.remove(length - 1);
        
        if(length > 1 && expression.get(length - 2) == "0" && rem == "-") expression.remove(length - 2);
        if(length > 1 && text == "-" && !(isOperand(expression.get(length - 2)))) expression.add("0");
        expression.add(text);
        screen.setText(currentText.substring(0, currentText.length() - 1) + text);
        
        length = expression.size();
      }
    }

    // Adding '0' before '-' since '-' is the operator that needs 2 operands in case
    // of
    // filling the beginning of the expression
    if (text.equals("-") && length == 0 && !isForbiddenToEdit) {
      added = true;
      expression.add("0");
      expression.add("-");
    }
    if (length > 0 && !isForbiddenToEdit) {
      lastElement = expression.get(length - 1);
      // Adding '0' before '-' since '-' is the operator that needs 2 operands in case
      // of
      // filling the middle of the expression
      if (lastElement.equals("(") && text.equals("-")) {
        added = true;
        expression.add("0");
        expression.add("-");
      } else if (isDouble(lastElement) && type == OPERAND || text.equals(".")) {
        // If the last inserted element is number and if it doesn't have decimal point
        // and the current text is decimal point or the current text is a number
        // then concatenate current text with the last one
        if ((!lastElement.contains(".") && text.equals(".")) || type == OPERAND) {
          added = true;
          expression.set(length - 1, lastElement + text);
        }
      }
    }
    if (!added) {
      int stringLength = text.length();

      // Separate braces from the operators
      if (stringLength > 1 && text.charAt(stringLength - 1) == '(') {
        if (stringLength > 2 && text.charAt(stringLength - 2) == '^') {

          // case of e^(
          expression.add(text.substring(0, stringLength - 2));
          expression.add("^");
        } else {
          // case of sin(, cos(, etc.
          expression.add(text.substring(0, stringLength - 1));
        }
        expression.add("(");

        added = true;
      } else {
        expression.add(text);
      }
    }
    String currentText = screen.getText();

    if (!isOperatorChange && !isSpecialValueChange && !isForbiddenToEdit) {
      screen.setText(currentText + text);
    }
  }

  private void handleSubmit(Evaluate evaluate) {
    isResult = true;
    int length = expression.size();
    if (length == 0) {
      screen.setText("0");
    } else if (length > 1) {
      try {
        String result = String.valueOf(evaluate.evaluate(expression));
        expression.clear();
        expression.add(result);
        screen.setText(result);
      } catch (Exception ex) {
        showMessageDialog(null, ex.getMessage());
        handleClear();
      }
    }
    // screen.setText("0");
  }

  private void handleClear() {
    screen.setText("");
    expression.clear();
  }

  private void removeLast() {
    int length = expression.size();
    if (length == 0)
      return;
    String removed = expression.remove(length - 1);
    String currentText = screen.getText();
    // Making sure to delete whole element not just one char of it in case of
    // multichared element
    screen.setText(currentText.substring(0, currentText.length() - removed.length()));
  }
}
