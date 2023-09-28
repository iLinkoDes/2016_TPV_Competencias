/*
 * Clase para establecer la conexión con MySQL
 * utilizando parametros como el password, usuario
 * nombre de la base de datos y el servidor.
 */
package SerigrafiaTorres;

// Importando librerias de SQL y swing
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Ian 
 */
public class ConexionMySQL {
    
    // Datos de conexion completa aqui tus datos
    private String servidor = "localhost";
    private String base = "competencias";
    private String usuario = "serigrafia_torres";
    private String clave = "serigrafia_torres";
    
    private static Connection conexion;
    
    public ConexionMySQL(){
        
    }
    
    public ConexionMySQL(String usr, String pwd, String svr, String nomBD){
        this.servidor = svr;
        this.usuario = usr;
        this.clave = pwd;
        this.base = nomBD;
    }
    
    public boolean crearConexion(String servidor, String usuario, String password, String base, boolean veriConexion){
        // Variable de tipo Connection
        
        try{
            // Se establece la conexión con el servidor solicitando los datos
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://" + servidor + ":3306/" + base, usuario, password);
            
            // Opcionalmente se puede mostrar un mensaje para verificar que la conexión funciono (poniendo en true el ultimo parametro)
            if(veriConexion == true){
                JOptionPane.showMessageDialog(null, "Se ha realizado la conexión con éxito!","MySQL en " + servidor,JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error: " + e.getMessage(),"Error al conectar " + servidor,JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean crearConexion(boolean veriConexion){
        boolean conEstablecida = crearConexion(this.servidor, this.usuario, this.clave, this.base, veriConexion);
        return conEstablecida;
    }
    
    public ResultSet ejecutarConsulta(String SQL){
        try{
            Statement estado = conexion.createStatement();
            ResultSet resultado = estado.executeQuery(SQL);
            return resultado;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.getMessage(), "Error de consulta SQL", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public boolean actualizarRegistro(String SQL){
        try{
            Statement estado = conexion.createStatement();
            estado.executeUpdate(SQL);
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.getMessage(), "Error de consulta SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean cerrarConexion(){
        try{
            conexion.close();
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error al cerrar la conexión: " + e.getMessage(), "Error al cerrar la conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void setUsuario(String usr){
        this.usuario = usr;
    }
    
    public void setServidor(String svr){
        this.servidor = svr;
    }
    
    public void setClave(String pwd){
        this.clave = pwd;
    }
    
    public void setBase(String nbd){
        this.base = nbd;
    }
}