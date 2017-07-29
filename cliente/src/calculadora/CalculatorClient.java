/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 * @author wanderson
 */

import javax.swing.*;
import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator c = (Calculator) Naming.lookup("rmi://127.0.0.1:1099/CalculadoraService");
            String aux = JOptionPane.showInputDialog("Informe os operandos separados por espaço");
            String aux2[] = aux.split(" ");
            int x = Integer.parseInt(aux2[0]);
            int y = Integer.parseInt(aux2[1]);

            JOptionPane.showMessageDialog(null, "\nSoma: "+c.add(x,y)+
                    "\nSub: "+c.sub(x,y)+
                    "\nMult: "+c.mul(x,y)+
                    "\nDiv: "+c.div(x,y));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
} 
