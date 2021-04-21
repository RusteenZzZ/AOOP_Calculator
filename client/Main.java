package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.SwingUtilities;

import services.Evaluate;

public class Main {
  public static final int PORT = 3000;
  public static final String SERVICE_NAME = "Evaluate";
  public static final String MACHINE = "localhost";

  public static void main(String args[]) {
    try {
      Registry registry = LocateRegistry.getRegistry(MACHINE, PORT);
      Evaluate evaluate = (Evaluate) registry.lookup(SERVICE_NAME);

      SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
          new Calculator(evaluate);
        }
      });
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
