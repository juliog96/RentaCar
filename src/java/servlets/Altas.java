/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Exception.MiExcepcion;
import dao.RentaCarDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ciudad;
import modelo.Coche;
import modelo.Localizacion;
import modelo.Tienda;

/**
 *
 * @author julio
 */
public class Altas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RentaCarDAO rentacarDAO = new RentaCarDAO();

        //Estos if hacen posible que en el mismo jsp se puedan hacer todos los inserts.
        if ("ALTA CIUDAD".equals(request.getParameter("ciudad"))) {
            String ciudad = "true";
            request.setAttribute("selectCiudad", ciudad);
            request.getRequestDispatcher("/altas.jsp").forward(request, response);
        }
        if ("ALTA COCHE".equals(request.getParameter("coche"))) {
            String coche = "true";
            request.setAttribute("selectCoche", coche);
            try {
                List<Tienda> tiendas = rentacarDAO.selectAllTiendas();
                request.setAttribute("tiendas", tiendas);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }
        if ("ALTA LOCALIZACION".equals(request.getParameter("localizacion"))) {
            String localizacion = "true";
            request.setAttribute("selectLocalizacion", localizacion);
            try {
                List<Ciudad> local = rentacarDAO.selectAllCiudades();
                request.setAttribute("ciudades", local);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }
        if ("ALTA TIENDA".equals(request.getParameter("tienda"))) {
            String tienda = "true";
            request.setAttribute("selectTienda", tienda);
            try {
                List<Localizacion> t_local = rentacarDAO.selectAllLocalizacionesDisp();
                request.setAttribute("localizaciones", t_local);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }

        //Inserts a la BBDD
        if ("INSERTAR".equals(request.getParameter("InsertCity"))) {
            String nameCity = request.getParameter("nombreCiudad");
            Ciudad c = new Ciudad(nameCity);
            try {
                //Repetimos metodo para que pueda dar de alta otra ciudad, una vez insertada una.
                String ciudad = "true";
                request.setAttribute("selectCiudad", ciudad);
                //Insertamos la ciudad.
                rentacarDAO.insertarCiudad(c);
                request.setAttribute("status", "Ciudad dada de alta correctamente");
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException | MiExcepcion ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }
        if ("INSERTAR".equals(request.getParameter("InsertCar"))) {
            String matricula = request.getParameter("matricula");
            String modelo = request.getParameter("modelo");
            String plazas = request.getParameter("plazas");
            String combustible = request.getParameter("combustible");
            String tienda = request.getParameter("tienda");
            int numTienda = Integer.parseInt(tienda);
            String precio = request.getParameter("precio");
            int numPrecio = Integer.parseInt(precio);
            Tienda t = new Tienda();
            t.setId(numTienda);
            Coche c = new Coche(0, matricula, modelo, plazas, combustible, numPrecio, t);
            try {
                //Repetimos metodo para que pueda dar de alta otro coche, una vez insertado uno.
                String coche = "true";
                request.setAttribute("selectCoche", coche);
                List<Tienda> tiendas = rentacarDAO.selectAllTiendas();
                request.setAttribute("tiendas", tiendas);
                //Insertamos el coche.
                rentacarDAO.insertarCoche(c);
                request.setAttribute("status", "Coche dado de alta correctamente");
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException | MiExcepcion ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }
        if ("INSERTAR".equals(request.getParameter("InsertPlace"))) {
            String direccion = request.getParameter("direccion");
            String ciudad = request.getParameter("ciudad");
            int idCiudad = Integer.parseInt(ciudad);
            Ciudad c = new Ciudad();
            c.setId(idCiudad);
            Localizacion l = new Localizacion(direccion, c);
            try {
                //Repetimos metodo para que pueda dar de alta otra localizacion, una vez insertada una.
                String localizacion = "true";
                request.setAttribute("selectLocalizacion", localizacion);
                List<Ciudad> local = rentacarDAO.selectAllCiudades();
                request.setAttribute("ciudades", local);
                //Insertamos la localizacion.
                rentacarDAO.insertarLocalizacion(l);
                request.setAttribute("status", "Localización dada de alta correctamente");
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException | MiExcepcion ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }
        if ("INSERTAR".equals(request.getParameter("InsertShop"))) {
            String nombre = request.getParameter("nombre");
            String empleados = request.getParameter("empleados");
            int numEmpleados = Integer.parseInt(empleados);
            String localizacion = request.getParameter("localizacion");
            int idLocalizacion = Integer.parseInt(localizacion);
            Localizacion l = new Localizacion(idLocalizacion);
            Tienda t = new Tienda(0, nombre, numEmpleados, l);
            try {
                //Repetimos metodo para que pueda dar de alta otra tienda, una vez insertada una.
                String tienda = "true";
                request.setAttribute("selectTienda", tienda);
                //Insertamos la tienda.
                rentacarDAO.insertarTienda(t);
                request.setAttribute("status", "Tienda dada de alta correctamente");
                //Y hacemos la consulta de las direcciones disponibles
                List<Localizacion> t_local = rentacarDAO.selectAllLocalizacionesDisp();
                request.setAttribute("localizaciones", t_local);
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            } catch (SQLException | MiExcepcion ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/altas.jsp").forward(request, response);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
