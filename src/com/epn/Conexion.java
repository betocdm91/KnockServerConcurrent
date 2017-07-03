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
import static com.epn.KnockKnockServer.contadorClientes;
import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.HTML.Tag.SELECT;

public class Conexion extends Thread {

    private Socket socket = null;
    private Integer cliente;
    private File file;
    String name=null;
    private Statement stm;
    private ResultSet queryBusqueda;

    public Conexion(Socket socket, Integer cliente, File file) {
        super("Conexion");
        this.socket = socket;
        this.cliente = cliente;
        this.file = file;
    }

    private static String printUsage(String mensaje) {
        String resultados = "";
        for (int i = 0; i < mensaje.length(); i++) {
            int dato = mensaje.codePointAt(i);
            dato += 4;
            resultados += String.valueOf((char) dato);
        }
        return resultados;
    }

    public void run() {
        
        
        Controlador conex=new Controlador();
        
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));) {
            
//        try {
//            queryBusqueda = stm.executeQuery("SELECT * FROM " + "Persona" + " WHERE nombre = ‘"+ nombre +"‘ LIMIT 1");
//            queryBusqueda.next();
//            name = queryBusqueda.getString(2);
//            conex.consulta(queryBusqueda);
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
            
            FileWriter bufferedWriter;
            File fileUsuarios = new File("Usuarios.txt");
            FileReader fileReader = new FileReader(fileUsuarios);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ip = "";
            while (bufferedReader.ready()) {
                if ((ip = bufferedReader.readLine()) != null ){//&& ip.equals(this.socket.getInetAddress().toString())) {
                    bufferedWriter = new FileWriter(file);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date iniciaConexion = Calendar.getInstance().getTime();
                    System.out.println("Hola " + this.cliente + " Ip: " + this.socket.getInetAddress() + " Tiempo " + dateFormat.format(iniciaConexion));
                    String datos = ("Cliente " + this.cliente + " Ip: " + this.socket.getInetAddress() + " Tiempo " + dateFormat.format(iniciaConexion) + "\t\n");

                    String inputLine, outputLine;

                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                        datos += inputLine + "\n";
                        outputLine = this.printUsage(inputLine);
                        datos += outputLine + "\n";
                        out.println(outputLine);
                        if (inputLine.equals("Bye")) {
                            break;
                        }
                    }
                    printWriter.println(datos);
                    bufferedWriter.close();
                    printWriter.close();
                    Date finConexion = Calendar.getInstance().getTime();
                    Long tiempo = finConexion.getTime() - iniciaConexion.getTime();
                    bufferedWriter = new FileWriter(file, true);
                    PrintWriter printWriter2 = new PrintWriter(bufferedWriter);
                    printWriter.println("Conexion Cliente " + this.cliente + " duro: " + tiempo / 60);
                    bufferedWriter.close();
                    printWriter.close();
                    printWriter2.close();
                }
            }
            System.out.println("no entro");
            fileReader.close();
            bufferedReader.close();
            System.out.println("contador antes "+contadorClientes);
            descontar();
            System.out.println("contador despues "+contadorClientes);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void descontar() {
        contadorClientes--;
    }
}
