/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "recuperarContrasenaServlet", urlPatterns = {"/recuperarContrasenaServlet"})
public class recuperarContrasenaServlet extends HttpServlet {

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
       String usuario = request.getParameter("usuario");
       String respuesta = request.getParameter("respuesta");
       HttpSession sesion = request.getSession();
       RequestDispatcher rd = null;
        //primero tratamos si es la vez que se invoca el usuario para obtener la respuesta
       if (usuario!=null)
       {
           sesion.setAttribute("usuario", usuario); //pasamos usuario
           rd = this.getServletContext().getRequestDispatcher("/recuperarContrasena.jsp"); //redirigimos para obtener respuesta
       }
       else if (respuesta!=null)
       {
           usuario = (String)sesion.getAttribute("usuario");
           if (usuario.equals(respuesta)) //si se acierta con la respuesta
           {
               rd = this.getServletContext().getRequestDispatcher("/nuevaContrasena.jsp");
           }
           else //si la respuesta es incorrecta
           {
               sesion.removeAttribute("usuario"); //borramos el usuario para comenzar el proceso
               rd = rd = this.getServletContext().getRequestDispatcher("/recuperarContrasena.jsp");
           }
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