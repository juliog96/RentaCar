/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Exception.MiExcepcion;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Coche;

/**
 *
 * @author julio
 */
public class RentaCarDAO {

    private Connection conexion;

    // Función que da de alta un usuario 

    public void insertarUsuario(Usuario usu) throws SQLException, MiExcepcion {
        conectar();
        if (existeUsuario(usu)) {
            throw new MiExcepcion("ERROR: Ya existe un usuario con ese Dni / User");
        } else {
            String insert = "insert into usuario values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setInt(1, 0);
            ps.setString(2, usu.getDni());
            ps.setString(3, usu.getUsername());
            ps.setString(4, usu.getPassword());
            ps.setString(5, usu.getNombre());
            ps.setString(6, usu.getApellido());
            ps.setString(7, usu.getCp());
            ps.setString(8, usu.getTelefono());
            ps.setString(9, usu.getTipo());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

    private boolean existeUsuario(Usuario usu) throws SQLException {
//        conectar();
        String select = "select * from usuario where dni='" + usu.getDni() + "'";
        String select2 = "select * from usuario where user='" + usu.getDni() + "'";
        Statement st = conexion.createStatement();
        boolean verdad = false;
        boolean existe = false;
        boolean existe2 = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        ResultSet rs2 = st.executeQuery(select2);
        if (rs2.next()) {
            existe2 = true;
        }
        if (existe == true && existe2 == true) {
            verdad = true;
        }
        rs.close();
        rs2.close();
        st.close();
//        desconectar();
        return verdad;
    }
    
    private void insertarCoche(Coche c){
    
    }

//     ********************* Conectar / Desconectar ****************************
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/rentacar";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
