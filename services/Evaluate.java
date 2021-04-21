package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import utils.InvalidExpression;

public interface Evaluate extends Remote {
    public double evaluate(ArrayList<String> expression) throws RemoteException, InvalidExpression;
}