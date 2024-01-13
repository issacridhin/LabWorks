/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ridhi
 */
public class Mavenproject2 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mycompany.mavenproject2.Issac").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mavenproject2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    class Issac{
    static{
        System.out.println("Static Block");
    }
    {
        System.out.println("Instance Block");
    }
    }
}

