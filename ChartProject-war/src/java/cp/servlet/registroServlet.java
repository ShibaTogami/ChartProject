/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.UsuarioFacade;
import cp.entity.Usuario;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "registroServlet", urlPatterns = {"/registroServlet"})
public class registroServlet extends HttpServlet {

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
        RequestDispatcher rd = null;
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String confirmaPassword = request.getParameter("confirmaPassword");
        String email = request.getParameter("email");
        String confirmaEmail = request.getParameter("confirmaEmail");
        String preguntaSecreta = request.getParameter("preguntaSecreta");
        String respuestaSecreta = request.getParameter("respuestaSecreta");
        Date fechaRegistro = new Date();
        HttpSession sesion = request.getSession();

        Usuario usuarioTemporal = new Usuario(usuario);//creamos usuario temporal para guardar los datos
        //primero comprobamos que coinciden contraseñas y mails
        if (!password.equals(confirmaPassword)) //si las contraseñas son diferentes
        {
            sesion.setAttribute("error", "password"); //mandamos mensaje de error

            usuarioTemporal.setPassword("");

            rd = this.getServletContext().getRequestDispatcher("/registro.jsp");
        }
        if (!email.equals(confirmaEmail))//si difieren los mails
        {
            if (sesion.getAttribute("error").equals("password")) //si ha fallado también la contraseña
            {
                sesion.setAttribute("error", "email y password");
            } else //si solo fallan los mails
            {
                sesion.setAttribute("error", "email");
            }
            usuarioTemporal.setEmail("");
            rd = this.getServletContext().getRequestDispatcher("/registro.jsp");
        }
        sesion.setAttribute("usuarioTemporal", usuarioTemporal);

        //si no ha habido ningun error
        if (sesion.getAttribute("error") == null) {
            //convertimos usuario temporal en usuario permanente y redirigimos a perfilServlet
            usuarioTemporal.setEmail(email);
            usuarioTemporal.setFechaRegistro(fechaRegistro);
            usuarioTemporal.setPassword(password);
            usuarioTemporal.setFechaRegistro(fechaRegistro);
            usuarioTemporal.setPregunta(preguntaSecreta);
            usuarioTemporal.setRespuesta(respuestaSecreta);
            usuarioFacade.create(usuarioTemporal);
            sesion.setAttribute("usuario", usuarioTemporal);
            rd = this.getServletContext().getRequestDispatcher("/perfilServlet");
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
