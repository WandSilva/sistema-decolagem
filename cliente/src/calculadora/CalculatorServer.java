/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author wanderson
 */
public class CalculatorServer { 
   public CalculatorServer() { 
      try {
          LocateRegistry.createRegistry(1099);
            Calculator c = new CalculatorImpl(); 
            Naming.bind("CalculadoraService", (Remote) c);
      } catch (Exception e) { 
            System.out.println("Trouble: " + e); 
      }
   } 
   public static void main(String args[]) { 
         new CalculatorServer();
   }
} 
