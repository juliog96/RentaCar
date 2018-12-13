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
import modelo.Coche;
import modelo.Tienda;
import modelo.Usuario;

/**
 *
 * @author julio
 */
public class Modificaciones extends HttpServlet {

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
        String user = (String) request.getSession(false).getAttribute("user");
        RentaCarDAO rentacarDAO = new RentaCarDAO();

        //Estos if hacen posible que en el mismo jsp se puedan hacer todos los updates.
        if ("MODIFICAR COCHE".equals(request.getParameter("coche"))) {
            String ciudad = "true";
            request.setAttribute("selectCoche", ciudad);
            try {
                List<Coche> coches = rentacarDAO.selectAllCoches();
                request.setAttribute("coches", coches);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }
        if ("MODIFICAR TIENDA".equals(request.getParameter("tienda"))) {
            String tienda = "true";
            request.setAttribute("selectTienda", tienda);
            try {
                List<Tienda> tiendas = rentacarDAO.selectAllTiendas();
                request.setAttribute("tiendas", tiendas);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }
        if ("BUSCAR".equals(request.getParameter("FindCar"))) {
            String cocheId = request.getParameter("coche");
            Coche car = new Coche();
            int numCoche = Integer.parseInt(cocheId);
            car.setId(numCoche);
            String datosCoche = "true";
            request.setAttribute("datosCoche", datosCoche);
            try {
                Coche coche = rentacarDAO.selectCoche(car);
                request.setAttribute("infoCoche", coche);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }
        if ("BUSCAR".equals(request.getParameter("FindShop"))) {
            String tiendaId = request.getParameter("tienda");
            Tienda shop = new Tienda();
            int numTienda = Integer.parseInt(tiendaId);
            shop.setId(numTienda);
            String datosTienda = "true";
            request.setAttribute("datosTienda", datosTienda);
            try {
                Tienda tienda = rentacarDAO.selectTienda(shop);
                request.setAttribute("infoTienda", tienda);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }
        if ("CAMBIAR".equals(request.getParameter("ChangeCar"))) {
            String precio = request.getParameter("precio");
            String cocheId = request.getParameter("idCoche");
            Coche car = new Coche();
            int numCoche = Integer.parseInt(cocheId);
            int precioCoche = Integer.parseInt(precio);
            car.setId(numCoche);
            car.setPrecio(precioCoche);
            try {
                rentacarDAO.updateCoche(car);
                request.setAttribute("status", "Modificación Coche - Realizada Correctamente");
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }
        if ("CAMBIAR".equals(request.getParameter("ChangeShop"))) {
            String empleados = request.getParameter("empleados");
            String tiendaId = request.getParameter("idTienda");
            Tienda shop = new Tienda();
            int numTienda = Integer.parseInt(tiendaId);
            int empleadosTienda = Integer.parseInt(empleados);
            shop.setId(numTienda);
            shop.setEmpleados(empleadosTienda);
            try {
                rentacarDAO.updateTienda(shop);
                request.setAttribute("status", "Modificación Tienda - Realizada Correctamente");
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/modificaciones.jsp").forward(request, response);
            }
        }

//      ******************************* Perfil ***************************************
        if ("Perfil".equals(request.getParameter("Perfil"))) {
            try {
                Usuario aux = new Usuario();
                aux.setUsername(user);

                Usuario Perfil = rentacarDAO.UsuarioByUsername(aux);
                request.setAttribute("perfil", Perfil);
                request.getRequestDispatcher("/perfil.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
        }
        if ("Modificar".equals(request.getParameter("ModPerfil"))) {
            try {
                String nombre = request.getParameter("nombre");
                String password = request.getParameter("password");
                String apellido = request.getParameter("apellido");
                String cp = request.getParameter("cp");
                String telefono = request.getParameter("telefono");

                Usuario aux = new Usuario();
                aux.setUsername(user);
                aux.setPassword(password);
                aux.setNombre(nombre);
                aux.setApellido(apellido);
                aux.setCp(cp);
                aux.setTelefono(telefono);

                rentacarDAO.updatePerfil(aux);
                request.setAttribute("status", "Perfil modificado correctamente");
                Usuario Perfil = rentacarDAO.UsuarioByUsername(aux);
                request.setAttribute("perfil", Perfil);
                request.getRequestDispatcher("/perfil.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
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
