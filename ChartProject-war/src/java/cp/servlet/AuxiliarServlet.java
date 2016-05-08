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
import java.util.ArrayList;
import java.util.List;
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
 * @author pablo
 */
@WebServlet(name = "auxiliarServlet", urlPatterns = {"/auxiliarServlet"})
public class AuxiliarServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        if(request.getParameter("boton") != null) {
            if(request.getParameter("boton").equals("Anadir")) {
                request.setAttribute("nombreProyecto", request.getParameter("nombreProyecto"));
                request.setAttribute("fechaInicioProyecto", request.getParameter("fechaInicioProyecto"));
                request.setAttribute("descripcionProyecto", request.getParameter("descripcionProyecto"));
                List<Usuario> participantes = new ArrayList<Usuario>();
                if (session.getAttribute("participantes") != null) {
                    participantes = (List<Usuario>) session.getAttribute("participantes");
                }
                //Sacamos el usuario de la BD y lo insertamos (si existe) en la lista de participantes (y en la BD)
                if (request.getParameter("nuevoParticipanteProyecto") != null) {
                    Usuario usuario = (Usuario) usuarioFacade.getUsuarioPorNickname(request.getParameter("nuevoParticipanteProyecto"));
                    if(usuario != null) {
                        participantes.add(usuario);
                    }
                }
                session.setAttribute("participantes", participantes);
                //Lo pasamos por sesion porque al pasarlo a un jsp, luego si lo pasamos a un sevlet se pierde.
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/nuevoProyecto.jsp");
                rd.forward(request, response);
            } else if (request.getParameter("boton").equals("Guardar")) {
                request.setAttribute("nombreProyecto", request.getParameter("nombreProyecto"));
                request.setAttribute("fechaInicioProyecto", request.getParameter("fechaInicioProyecto"));
                request.setAttribute("descripcionProyecto", request.getParameter("descripcionProyecto"));
                
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/nuevoProyectoServlet");
                rd.forward(request, response);
            } else {
                //Error, botón nulo.
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/nuevoProyecto.jsp");
                rd.forward(request, response);
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
