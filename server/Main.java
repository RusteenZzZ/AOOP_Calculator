package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import services.Evaluate;
import services.EvaluateImpl;

public class Main {
    public static int PORT = 3000;
    public static String SERVICE_NAME = "Evaluate";

    public static void main(String[] args) {
        try {
            System.out.println("Creating the service....");
            Evaluate skeleton = (Evaluate) UnicastRemoteObject.exportObject(new EvaluateImpl(), 0);
            Registry registry = LocateRegistry.createRegistry(PORT);

            if (!Arrays.asList(registry.list()).contains(SERVICE_NAME)) {
                System.out.println("Binding the service....");
                registry.bind(SERVICE_NAME, skeleton);
            } else {
                System.out.println("Rebinding the service....");
                registry.rebind(SERVICE_NAME, skeleton);
            }
            System.out.println("Ready");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}