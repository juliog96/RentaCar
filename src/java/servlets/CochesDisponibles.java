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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ciudad;
import modelo.Coche;
import modelo.Factura;
import modelo.Tienda;
import modelo.Usuario;

/**
 *
 * @author julio
 */
public class CochesDisponibles extends HttpServlet {

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
        if ("Consultar".equals(request.getParameter("consultar"))) {
            String tienda = request.getParameter("tienda");
            String f_inicial = request.getParameter("f_inicio");
            String f_final = request.getParameter("f_final");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            //Convertimos los String a LocalDate
            LocalDate dateInicial = LocalDate.parse(f_inicial, formatter);
            LocalDate dateFinal = LocalDate.parse(f_final, formatter);
            String FechaIni = dateInicial.format(formatter2);
            String FechaFin = dateFinal.format(formatter2);
            //Calculo de los dias
            int daysBetween = (int) ChronoUnit.DAYS.between(dateInicial, dateFinal);
            request.setAttribute("dias", daysBetween);

            Tienda t = new Tienda();
            t.setId(Integer.parseInt(tienda));
            try {
                List<Coche> cars = rentacarDAO.selectCochesByTienda(t, FechaIni);
                request.setAttribute("coches", cars);
                request.setAttribute("fechaInicio", FechaIni);
                request.setAttribute("fechaFinal", FechaFin);
                request.setAttribute("tiendaSelect", tienda);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex.getMessage());
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
        }
        //Si no entra en el if de consultar, insertamos la factura ya que tendremos todos los datos.
        String coche = request.getParameter("coche");
        String precioTotal = request.getParameter("precioTotal");
        String tienda = request.getParameter("tiendaSeleccionada");
        String FechaIni = request.getParameter("DateInicio");
        String FechaFin = request.getParameter("DateFinal");

        int Precio = Integer.parseInt(precioTotal);
        //Cogemos la Fecha Actual.
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String fechaActual = hourdateFormat.format(date);
        //Creamos un objeto tipo Coche con su id
        Coche c = new Coche();
        c.setId(Integer.parseInt(coche));
        //Creamos un objeto tipo Tienda con su id
        Tienda t = new Tienda();
        t.setId(Integer.parseInt(tienda));
        //Creamo un objeto tipo Usuario con su user
        Usuario u = new Usuario();
        u.setUsername(user);
        //Y creamos la factura
        Factura f = new Factura(0, fechaActual, FechaIni, FechaFin, Precio, c, u, t);
        try {
            rentacarDAO.insertarFactura(f);
            request.setAttribute("status", "Alquiler realizado correctamente");
            List<Ciudad> ciudades = rentacarDAO.selectAllCiudades();
            request.setAttribute("ciudades", ciudades);
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        } catch (SQLException | MiExcepcion ex) {
            request.setAttribute("status", ex.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
