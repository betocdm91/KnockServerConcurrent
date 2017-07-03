/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epn;

/**
 *
 * @author Aspire
 */

import java.net.*;
import java.io.*;

public class KnockKnockServer {

    public static Integer contadorClientes = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
  
//        if (args.length != 1) {
        //System.err.println("Usage: java KnockKnockServer <port number>");
//            System.exit(1);
//        }

        int portNumber = 4445;// Integer.parseInt(args[0]);
        boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            contadorClientes++;
            while (listening) {
                System.out.println("Contador clientes en el servidor:" + contadorClientes);
                if (contadorClientes <= 10) {
                    new Conexion(serverSocket.accept(), contadorClientes, new File("log" + contadorClientes + ".txt")).start();
                    incrementCount();                  
                } else {
                    while (contadorClientes > 10) {
//                        serverSocket.
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    public static synchronized void incrementCount() {
        contadorClientes++;
    }
}
