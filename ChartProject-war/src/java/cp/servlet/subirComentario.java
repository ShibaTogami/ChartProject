/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.ComentarioFacade;
import cp.ejb.ProyectoFacade;
import cp.entity.Comentario;
import cp.entity.Proyecto;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
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
 * @author nacho
 */

@WebServlet(name = "subirComentario", urlPatterns = {"/subirComentario"})
public class subirComentario extends HttpServlet {
    
        @EJB
        ComentarioFacade comentarioFacade;
        ProyectoFacade proyectoFacade;
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
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        Proyecto proyecto = (Proyecto) sesion.getAttribute("Proyecto");
        String comentario = (String) request.getParameter("comment");
        Comentario comment = new Comentario();
        comment.setIdProyecto2(proyecto);
        comment.setNickname(user);
        comment.setTexto(comentario);
        comentarioFacade.create(comment);
        List<Comentario> aux =(List<Comentario>) proyecto.getComentarioCollection();
        aux.add(comment);
        proyecto.setComentarioCollection(aux);
        sesion.setAttribute("Proyecto", proyecto);
        request.setAttribute("comentarios", proyecto.getComentarioCollection());

        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/proyecto.jsp");
        rd.forward(request,response);
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
