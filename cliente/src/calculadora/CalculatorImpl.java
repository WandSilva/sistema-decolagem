/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wanderson
 */
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    public CalculatorImpl() throws RemoteException {
        super();
    }

    public long add(long a, long b)
            throws java.rmi.RemoteException {
        return a + b;
    }

    public long sub(long a, long b)
            throws java.rmi.RemoteException {
        return a - b;
    }

    public long mul(long a, long b)
            throws java.rmi.RemoteException {
        return a * b;
    }

    public long div(long a, long b)
            throws java.rmi.RemoteException {
        return a / b;
    }
} 
