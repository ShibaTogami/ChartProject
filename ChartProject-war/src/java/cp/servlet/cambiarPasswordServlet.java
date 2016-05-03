/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.UsuarioFacade;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shiba
 */
@WebServlet(name = "cambiarPasswordServlet", urlPatterns = {"/cambiarPasswordServlet"})
public class cambiarPasswordServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

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
        HttpSession sesion = request.getSession();
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        RequestDispatcher rd = null;
        if (!pass1.equals(pass2)) //si son diferentes vuelta a la selección de contraseña
        {
            sesion.setAttribute("error", "Passwords diferentes");
            rd = this.getServletContext().getRequestDispatcher("/nuevaContrasena.jsp");
        }
        else //si coinciden se cambia
        {
            //cambiamos la contraseña al usuario tanto en la sesion como en la BD
            Usuario user = (Usuario)sesion.getAttribute("usuario");
            user.setPassword(pass1);
            usuarioFacade.edit(user);
            rd = this.getServletContext().getRequestDispatcher("/principal.jsp");
        }
        rd.forward(request, response);
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
