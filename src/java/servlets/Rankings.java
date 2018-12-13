/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.RentaCarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Coche;
import modelo.RankingNumFacturas;

/**
 *
 * @author julio
 */
public class Rankings extends HttpServlet {

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

        if ("RANKING TIENDA POR NUM FACTURAS".equals(request.getParameter("numFacturas"))) {
            try {
                List<RankingNumFacturas> ranking = rentacarDAO.RankingFacturas();
                request.setAttribute("rankingFacturas", ranking);
                request.getRequestDispatcher("/rankings.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/rankings.jsp").forward(request, response);
            }
        }
        if ("TOP 3 TIENDAS MAYOR FACTURACION".equals(request.getParameter("facturacion"))) {
            try {
                List<RankingNumFacturas> rankingFact = rentacarDAO.RankingFacturacion();
                request.setAttribute("rankingFacturacion", rankingFact);
                request.getRequestDispatcher("/rankings.jsp").forward(request, response);
            } catch (SQLException ex) {
                request.setAttribute("status", ex);
                request.getRequestDispatcher("/rankings.jsp").forward(request, response);
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
