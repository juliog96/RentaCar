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
import modelo.RankingNumFacturas;
import modelo.Tienda;

/**
 *
 * @author julio
 */
public class RentaCarDAO {

    private Connection conexion;

    //     ******************** Inserts **************************
    public void insertarTienda(Tienda t) throws SQLException, MiExcepcion {
        conectar();
        if (existeTienda(t)) {
            throw new MiExcepcion("ERROR: Ya existe una tienda con ese nombre");
        } else {
            String insert = "insert into tienda values (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setInt(1, 0);
            ps.setString(2, t.getNombre());
            ps.setInt(3, t.getEmpleados());
            ps.setInt(4, t.getLocalizacion().getId());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

    public void insertarLocalizacion(Localizacion l) throws SQLException, MiExcepcion {
        conectar();
        if (existeLocalizacion(l)) {
            throw new MiExcepcion("ERROR: Ya existe una localizacion con esa direccion");
        } else {
            String insert = "insert into localizacion values (?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setInt(1, 0);
            ps.setString(2, l.getDireccion());
            ps.setInt(3, l.getCiudad().getId());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

    public void insertarCiudad(Ciudad c) throws SQLException, MiExcepcion {
        conectar();
        if (existeCiudad(c)) {
            throw new MiExcepcion("ERROR: Ya existe una ciudad con ese nombre");
        } else {
            String insert = "insert into ciudad values (?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setInt(1, 0);
            ps.setString(2, c.getNombre());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

    public void insertarCoche(Coche c) throws SQLException, MiExcepcion {
        conectar();
        if (existeCoche(c)) {
            throw new MiExcepcion("ERROR: Ya existe un coche con esa matricula");
        } else {
            String insert = "insert into coche values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);
            ps.setInt(1, 0);
            ps.setString(2, c.getMatricula());
            ps.setString(3, c.getModelo());
            ps.setString(4, c.getPlazas());
            ps.setString(5, c.getCombustible());
            ps.setInt(6, c.getPrecio());
            ps.setInt(7, c.getTienda().getId());
            ps.executeUpdate();
            ps.close();
        }
        desconectar();
    }

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

//     ********************* Updates **************************
    public void updatePerfil(Usuario u) throws SQLException {
        conectar();
        String update = "update usuario set password = '" + u.getPassword() + "', nombre = '" + u.getNombre() + "', apellido = '" + u.getApellido() + "', c_p = '" + u.getCp() + "', telefono = '" + u.getTelefono() + "'  where user= '" + u.getUsername() + "'";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void updateCoche(Coche c) throws SQLException {
        conectar();
        String update = "update coche set precio = '" + c.getPrecio() + "' where id= '" + c.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void updateTienda(Tienda t) throws SQLException {
        conectar();
        String update = "update tienda set empleados = '" + t.getEmpleados() + "' where id= '" + t.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

//     ********************* Deletes **************************
    public void eliminarCiudad(Ciudad c) throws SQLException {
        conectar();
        String delete = "delete from ciudad where id= '" + c.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void eliminarCoche(Coche c) throws SQLException {
        conectar();
        String delete = "delete from coche where id= '" + c.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void eliminarLocalizacion(Localizacion l) throws SQLException {
        conectar();
        String delete = "delete from localizacion where id= '" + l.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

    public void eliminarTienda(Tienda t) throws SQLException {
        conectar();
        String delete = "delete from tienda where id= '" + t.getId() + "'";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.executeUpdate();
        ps.close();
        desconectar();
    }

//     ******************** Consultas **************************
    public List<RankingNumFacturas> RankingFacturas() throws SQLException {
        conectar();
        String select = "select count(factura.id), tienda.nombre from factura\n"
                + "inner join tienda on tienda.id = tienda_id\n"
                + "group by tienda_id order by count(factura.id) desc";
        List<RankingNumFacturas> ranking = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        int cont = 1;
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                RankingNumFacturas r = new RankingNumFacturas();
                r.setId(cont);
                r.setTienda(rs.getString("tienda.nombre"));
                r.setFacturas(rs.getInt("count(factura.id)"));
                ranking.add(r);
                cont++;
            }
        }
        desconectar();
        return ranking;
    }

    public List<RankingNumFacturas> RankingFacturacion() throws SQLException {
        conectar();
        String select = "select sum(precio), tienda.nombre from factura\n"
                + "inner join tienda on tienda.id = tienda_id\n"
                + "group by tienda_id order by sum(precio) desc limit 3";
        List<RankingNumFacturas> ranking = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        int cont = 1;
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                RankingNumFacturas r = new RankingNumFacturas();
                r.setId(cont);
                r.setTienda(rs.getString("tienda.nombre"));
                r.setFacturas(rs.getInt("sum(precio)"));
                ranking.add(r);
                cont++;
            }
        }
        desconectar();
        return ranking;
    }

    public Usuario UsuarioByUsername(Usuario u) throws SQLException {
        conectar();
        String select = "select * from usuario where user = '" + u.getUsername() + "'";
        Usuario usuario = new Usuario();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            usuario.setPassword(rs.getString("password"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCp(rs.getString("c_p"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setTipo(rs.getString("tipo"));
        }
        desconectar();
        return usuario;
    }

    public Coche selectCoche(Coche c) throws SQLException {
        conectar();
        String select = "select * from coche where id = '" + c.getId() + "'";
        Coche car = new Coche();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            car.setId(rs.getInt("id"));
            car.setPrecio(rs.getInt("precio"));
        }
        desconectar();
        return car;
    }

    public Tienda selectTienda(Tienda t) throws SQLException {
        conectar();
        String select = "select * from Tienda where id = '" + t.getId() + "'";
        Tienda shop = new Tienda();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            shop.setId(rs.getInt("id"));
            shop.setEmpleados(rs.getInt("empleados"));
        }
        desconectar();
        return shop;
    }

    public List<Coche> selectAllCoches() throws SQLException {
        conectar();
        String select = "select * from coche";
        List<Coche> coches = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Coche c = new Coche();
                c.setId(rs.getInt("id"));
                c.setMatricula(rs.getString("matricula"));
                c.setModelo(rs.getString("modelo"));
                c.setPrecio(rs.getInt("precio"));
                coches.add(c);
            }
        }
        desconectar();
        return coches;
    }

    public List<Localizacion> selectAllLocalizaciones() throws SQLException {
        conectar();
        String select = "select * from localizacion";
        List<Localizacion> localizaciones = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Localizacion l = new Localizacion();
                l.setId(rs.getInt("id"));
                l.setDireccion(rs.getString("direccion"));
                localizaciones.add(l);
            }
        }
        desconectar();
        return localizaciones;
    }

    public List<Localizacion> selectAllLocalizacionesDisp() throws SQLException {
        conectar();
        String select = "select localizacion.id, localizacion.direccion from localizacion\n"
                + "left join tienda on localizacion.id = tienda.localizacion_id\n"
                + "where tienda.localizacion_id is null";
        List<Localizacion> localizaciones = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Localizacion l = new Localizacion();
                l.setId(rs.getInt("localizacion.id"));
                l.setDireccion(rs.getString("localizacion.direccion"));
                localizaciones.add(l);
            }
        }
        desconectar();
        return localizaciones;
    }

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
                ciudad.setId(rs.getInt("id"));
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

    public List<Coche> selectCochesByTienda(Tienda t, String fechaF) throws SQLException {
        conectar();
        String select = "select distinct coche.* from coche left join factura on "
                + "coche_id = coche.id where coche.tienda_id = '" + t.getId() + "' and (recogida is null or recogida < \'" + fechaF + "\"')";
        List<Coche> coches = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Coche c = new Coche();
                c.setId(rs.getInt("id"));
                c.setMatricula(rs.getString("matricula"));
                c.setModelo(rs.getString("modelo"));
                c.setPlazas(rs.getString("plazas"));
                c.setCombustible(rs.getString("combustible"));
                c.setPrecio(rs.getInt("precio"));
                coches.add(c);
            }
        }
        desconectar();
        return coches;
    }

    public List<Tienda> selectAllTiendas() throws SQLException {
        conectar();
        String select = "select id, nombre from tienda";
        List<Tienda> tiendas = new ArrayList<>();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.first()) {//recorre el resultset al siguiente registro si es que existen
            rs.beforeFirst();//regresa el puntero al primer registro
            while (rs.next()) {
                Tienda t = new Tienda();
                t.setId(rs.getInt("tienda.id"));
                t.setNombre(rs.getString("tienda.nombre"));
                tiendas.add(t);
            }
        }
        desconectar();
        return tiendas;
    }

//     ********************* Extras ****************************
//     !!Hay que abrir conexion antes de llamar a estos metodo¡¡
    private boolean existeTienda(Tienda t) throws SQLException {
//        conectar();
        String select = "select * from tienda where nombre='" + t.getNombre() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
//        desconectar();
        return existe;
    }

    private boolean existeLocalizacion(Localizacion l) throws SQLException {
//        conectar();
        String select = "select * from localizacion where direccion='" + l.getDireccion() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
//        desconectar();
        return existe;
    }

    private boolean existeCiudad(Ciudad c) throws SQLException {
//        conectar();
        String select = "select * from ciudad where nombre='" + c.getNombre() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
//        desconectar();
        return existe;
    }

    private boolean existeCoche(Coche c) throws SQLException {
//        conectar();
        String select = "select * from coche where matricula='" + c.getMatricula() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
//        desconectar();
        return existe;
    }

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
