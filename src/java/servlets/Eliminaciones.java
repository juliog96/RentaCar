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
public class Eliminaciones extends HttpServlet {

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

        //Estos if hacen posible que en el mismo jsp se puedan hacer todos los deletes.
        if ("ELIMINAR CIUDAD".equals(request.getParameter("ciudad"))) {
            String ciudad = "true";
            request.setAttribute("selectCiudad", ciudad);
            try {
                List<Ciudad> ciudades = rentacarDAO.selectAllCiudades();
                request.setAttribute("ciudades", ciudades);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR COCHE".equals(request.getParameter("coche"))) {
            String coche = "true";
            request.setAttribute("selectCoche", coche);
            try {
                List<Coche> coches = rentacarDAO.selectAllCoches();
                request.setAttribute("coches", coches);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR LOCALIZACION".equals(request.getParameter("localizacion"))) {
            String localizacion = "true";
            request.setAttribute("selectLocalizacion", localizacion);
            try {
                List<Localizacion> localizaciones = rentacarDAO.selectAllLocalizaciones();
                request.setAttribute("localizaciones", localizaciones);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR TIENDA".equals(request.getParameter("tienda"))) {
            String tienda = "true";
            request.setAttribute("selectTienda", tienda);
            try {
                List<Tienda> tiendas = rentacarDAO.selectAllTiendas();
                request.setAttribute("tiendas", tiendas);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }

        //Deletes a la BBDD.
        if ("ELIMINAR".equals(request.getParameter("DeleteCity"))) {
            String city = request.getParameter("ciudad");
            int idCiudad = Integer.parseInt(city);
            Ciudad c = new Ciudad(idCiudad);
            try {
                //Repetimos metodo para que pueda eliminar otra ciudad, una vez eliminada una.
                String ciudad = "true";
                request.setAttribute("selectCiudad", ciudad);
                //Eliminamos la ciudad.
                rentacarDAO.eliminarCiudad(c);
                request.setAttribute("status", "Ciudad eliminada correctamente");
                //Y hacemos la consulta de las ciudades. 
                List<Ciudad> ciudades = rentacarDAO.selectAllCiudades();
                request.setAttribute("ciudades", ciudades);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR".equals(request.getParameter("DeleteCar"))) {
            String cocheSelec = request.getParameter("coche");
            int idCoche = Integer.parseInt(cocheSelec);
            Coche c = new Coche();
            c.setId(idCoche);
            try {
                //Repetimos metodo para que pueda eliminar otro coche, una vez eliminado uno.
                String coche = "true";
                request.setAttribute("selectCoche", coche);
                //Eliminamos el coche.
                rentacarDAO.eliminarCoche(c);
                request.setAttribute("status", "Coche eliminado correctamente");
                //Y hacemos la consulta de los coches. 
                List<Coche> coches = rentacarDAO.selectAllCoches();
                request.setAttribute("coches", coches);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR".equals(request.getParameter("DeletePlace"))) {
            String localizacionSelec = request.getParameter("localizacion");
            int idLocalizacion = Integer.parseInt(localizacionSelec);
            Localizacion l = new Localizacion();
            l.setId(idLocalizacion);
            try {
                //Repetimos metodo para que pueda eliminar otra localizacion, una vez eliminada una.
                String localizacion = "true";
                request.setAttribute("selectLocalizacion", localizacion);
                //Eliminamos la localizacion.
                rentacarDAO.eliminarLocalizacion(l);
                request.setAttribute("status", "Localización eliminada correctamente");
                //Y hacemos la consulta de las localizaciones. 
                List<Localizacion> localizaciones = rentacarDAO.selectAllLocalizaciones();
                request.setAttribute("localizaciones", localizaciones);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", "Tiene que eliminar la tienda a la que esta asociada");
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            }
        }
        if ("ELIMINAR".equals(request.getParameter("DeleteShop"))) {
            String tiendaSelec = request.getParameter("tienda");
            int idTienda = Integer.parseInt(tiendaSelec);
            Tienda t = new Tienda();
            t.setId(idTienda);
            try {
                //Repetimos metodo para que pueda eliminar otra tienda, una vez eliminada una.
                String tienda = "true";
                request.setAttribute("selectTienda", tienda);
                //Eliminamos la tienda.
                rentacarDAO.eliminarTienda(t);
                request.setAttribute("status", "Tienda eliminada correctamente");
                //Y hacemos la consulta de las tiendas
                List<Tienda> tiendas = rentacarDAO.selectAllTiendas();
                request.setAttribute("tiendas", tiendas);
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/eliminaciones.jsp").forward(request, response);
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
