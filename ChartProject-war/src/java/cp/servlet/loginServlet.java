/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.UsuarioFacade;
import cp.entity.Usuario;
import java.io.IOException;
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
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        HttpSession sesion = request.getSession();
        RequestDispatcher rd = null;
        boolean caracteres = true;

        
        //comprobaremos si el password tiene caracteres que podria crear problemas
        //de seguridad
        for (char aux : password.toCharArray()) {
            if (aux == '\'' || aux == '"' || aux == '+' || aux == '-' || aux == '*' || aux == '/') {
                caracteres = false;
            }
        }

        if (!caracteres) //si hay caracteres maliciosos
        {
            sesion.setAttribute("retorno", "true");
            rd = this.getServletContext().getRequestDispatcher("/index.jsp");
        } else //consultamos el pass
        {
            Usuario user = usuarioFacade.getUsuarioPorNickname(usuario);

            if (!user.getPassword().equals(password)) //si no corresponden
            {
                sesion.setAttribute("retorno", "true");
                rd = this.getServletContext().getRequestDispatcher("/index.jsp");
            } else //si coinciden
            {
                sesion.setAttribute("usuario", user);
                rd = this.getServletContext().getRequestDispatcher("/principal.jsp");
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
