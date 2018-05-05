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
import java.util.ArrayList;
import java.util.List;
import modelo.Ciudad;
import modelo.Coche;
import modelo.Factura;
import modelo.Localizacion;
import modelo.Tienda;

/**
 *
 * @author julio
 */
public class RentaCarDAO {

    private Connection conexion;

    //     ******************** Inserts **************************
    public void insertarFactura(Factura f) throws SQLException, MiExcepcion {
        conectar();
        String insert = "insert into factura values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, 0);
        ps.setString(2, f.getFecha());
        ps.setString(3, f.getEntrega());
        ps.setString(4, f.getRecogida());
        ps.setInt(5, f.getPrecio());
        ps.setInt(6, f.getCoche().getId());
        ps.setString(7, f.getUsuario().getUsername());
        ps.setInt(8, f.getTienda().getId());
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    // Función que da de alta un usuario 
    public void insertarUsuario(Usuario usu) throws SQLException, MiExcepcion {
        conectar();
        if (existeUsuario(usu)) {
            throw new MiExcepcion("ERROR: Ya existe un usuario con ese Dni / User");
        } else {
            String insert = "insert into usuario values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setString(1, usu.getUsername());
            ps.setString(2, usu.getDni());
            ps.setString(3, usu.getPassword());
            ps.setString(4, usu.getNombre());
            ps.setString(5, usu.getApellido());
            ps.setString(6, usu.getCp());
            ps.setString(7, usu.getTelefono());
            ps.setString(8, usu.getTipo());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

    //Funcion para login del usuario, devolviendo un boolean que
    //marcara el redireccionamiento de un usuario o un admin 
    //segun el tipo que esta especificado en la bbdd.
    public boolean login(Usuario usu) throws SQLException, MiExcepcion {
        conectar();
        boolean tipo = false;
        String bdPass;
        String tipoUser;
        String user = "select * from usuario where user='" + usu.getUsername() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(user);
        if (rs.next()) {
            bdPass = rs.getString("password");
            tipoUser = rs.getString("tipo");
            if (!bdPass.equals(usu.getPassword())) {
                throw new MiExcepcion("ERROR: Contraseña mal introducida");
            } else {
                if (tipoUser.equals("User")) {
                    tipo = true;
                }
            }
        } else {
            throw new MiExcepcion("ERROR: Usuario no existe");
        }
        rs.close();
        st.close();
        desconectar();
        return tipo;
    }

//     ******************** Consultas **************************
    public List<Ciudad> selectAllCiudades() throws SQLException {
        conectar();
        String select = "select * from ciudad";
        List<Ciudad> ciudades = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setNombre(rs.getString("nombre"));
                ciudades.add(ciudad);
            }
        }
        desconectar();
        return ciudades;
    }

    public List<Tienda> selectTiendasByCiudad(Ciudad c) throws SQLException {
        conectar();
        String select = "select tienda.id, tienda.nombre, tienda.empleados, localizacion.direccion from ((tienda\n"
                + "inner join localizacion on tienda.localizacion_id = localizacion.id)\n"
                + "inner join ciudad on localizacion.ciudad_id = ciudad.id)\n"
                + "where ciudad.nombre ='" + c.getNombre() + "'";
        List<Tienda> tiendas = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Tienda t = new Tienda();
                Localizacion l = new Localizacion();
                l.setDireccion(rs.getString("localizacion.direccion"));
                t.setId(rs.getInt("tienda.id"));
                t.setNombre(rs.getString("tienda.nombre"));
                t.setLocalizacion(l);
                tiendas.add(t);
            }
        }
        desconectar();
        return tiendas;
    }

    public List<Coche> selectCochesByTienda(Tienda t, String fechaI) throws SQLException {
        conectar();
        String select = "select coche.id, coche.matricula, coche.modelo, coche.plazas, coche.combustible, coche.precio from factura\n"
                + "inner join coche on factura.coche_id = coche.id\n"
                + "inner join tienda on factura.tienda_id = tienda.id\n"
                + "where factura.recogida < \'" + fechaI + "\"' and factura.tienda_id = '" + t.getId() + "'\n"
                + "group by coche.id";
        List<Coche> coches = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Coche c = new Coche();
                c.setId(rs.getInt("coche.id"));
                c.setMatricula(rs.getString("coche.matricula"));
                c.setModelo(rs.getString("coche.modelo"));
                c.setPlazas(rs.getInt("coche.plazas"));
                c.setCombustible(rs.getString("coche.combustible"));
                c.setPrecio(rs.getInt("coche.precio"));
                coches.add(c);
            }
        }
        desconectar();
        return coches;
    }

//     ********************* Extras ****************************
//     !!Hay que abrir conexion antes de llamar a este metodo¡¡
    private boolean existeUsuario(Usuario usu) throws SQLException {
//        conectar();
        String select = "select * from usuario where dni='" + usu.getDni() + "'";
        String select2 = "select * from usuario where user='" + usu.getUsername() + "'";
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
        if (existe == true || existe2 == true) {
            verdad = true;
        }
        rs.close();
        rs2.close();
        st.close();
//        desconectar();
        return verdad;
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
