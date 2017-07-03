/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Aspire
 */
public class Controlador {
   private Connection con;
	public Controlador() {
		String url;
		String server = "[DESKTOP-FRPP528]";
		String base = "Universidad"; 
		String usuario = "sa";
		String pass = "root";
		try{
			//Register the JDBC driver for MySQL.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//Define URL of database server for
			// database named mysql on the localhost
			// with the default port number 3306.
			url ="jdbc:sqlserver://" + server + "/"+ base;
			//usar la opcion de reconectar
			//Get a connection to the database for a
			// user named root with a blank password.
			// This user is the default administrator
			// having full privileges to do anything.
			url+="?connectTimeout=7000&socketTimeout=7000";
			DriverManager.setLoginTimeout(1);
			con =DriverManager.getConnection(url,usuario, pass);


		}
		catch(Exception e ) {
			String 	message="<html><p><b>No se ha podido conectar con la base de datos </b></p>" +
					"<p>Verifique los parametros de conexion o el estado de su conexion</p> ";
			JOptionPane.showMessageDialog(new JFrame(), message);
		}		
	}
	public ResultSet consulta (String queryBusqueda){
		Statement comando;
		ResultSet resultado=null;		
		try{
			comando = con.createStatement();
			resultado=comando.executeQuery(queryBusqueda);	
		}
		catch( SQLException e ) {				
			e.getSQLState();				
			String message="<html><p><b>La consulta ejecutada fue: </b>" +queryBusqueda+" </p>" +
					"<p><b>Error de Mysql: </b> "+e.getMessage() +  "</p> " +
					"<p><b>Error codigo: </b>" + e.getErrorCode()+" </p></html>";
			System.out.println(queryBusqueda);
			JOptionPane.showMessageDialog(new JFrame(), message);
		}
		return resultado;
	}
	
	public boolean siguiente (ResultSet rs){
		try { 	
			return rs.next();
		}
		catch(SQLException error ){
			return false;
		} 
	}
	
	/*public boolean insertarB (String insert)
	{
		Statement comando;
		try
		{
			comando = con.createStatement();	
			comando.executeUpdate(insert);
			return true;

		}
		catch( SQLException error ) 
		{
			return false;
		}	

	}



	public String getString (ResultSet datos, String columna){

		try
		{
			return datos.getString(columna);
		}
		catch (SQLException error)
		{

			String message="<html><p><b>Error de Mysql: </b> "+error.getMessage() +  "</p> " +
					"<p><b>Error código: </b>" + error.getErrorCode()+" </p></html>";
			JOptionPane.showMessageDialog(new JFrame(),message);
			return (null);
		}			
	}*/


	/*public java.util.Date obtenerDate (ResultSet datos, String columna){
		try
		{
			return datos.getDate(columna);
		}
		catch (SQLException error)
		{

			String message="<html><p><b>Error de Mysql: </b> "+error.getMessage() +  "</p> " +
					"<p><b>Error código: </b>" + error.getErrorCode()+" </p></html>";
			JOptionPane.showMessageDialog(new JFrame(),message);
			return (null);
		}			
	} */

	public Connection getConeccion(){
		return this.con;
	}
	/*public boolean actualizar(String update) {
		Statement comando;
		try
		{
			comando = con.createStatement();	
			comando.executeUpdate(update);
			return true;

		}
		catch( SQLException error ) 
		{
			return false;
		}	
				
		
	}*/


}
