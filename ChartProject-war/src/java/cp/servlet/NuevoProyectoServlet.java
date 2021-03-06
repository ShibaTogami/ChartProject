/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.ComentarioFacade;
import cp.ejb.ProyectoFacade;
import cp.ejb.UsuarioFacade;
import cp.entity.Comentario;
import cp.entity.Proyecto;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
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
@WebServlet(name = "nuevoProyectoServlet", urlPatterns = {"/nuevoProyectoServlet"})
public class NuevoProyectoServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private ProyectoFacade proyectoFacade;
    
    @EJB
    ComentarioFacade comentarioFacade;
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
        
        if(request.getAttribute("nombreProyecto") != null && session.getAttribute("usuario") != null) {
            long idaux = proyectoFacade.findMaxId().longValue();
            idaux++;
            BigDecimal id = BigDecimal.valueOf(idaux);
           
            Proyecto proyecto = new Proyecto(id);
            proyecto.setLider((Usuario) session.getAttribute("usuario"));
            Date date = new Date();
                
            proyecto.setFechaInicio(date);
            
            proyecto.setNombre((String) request.getAttribute("nombreProyecto"));
            if(session.getAttribute("participantes") != null) {
                proyecto.setUsuarioCollection((Collection<Usuario>) session.getAttribute("participantes"));
            }
            
            if(request.getAttribute("descripcionProyecto") != null) {
                proyecto.setDescripcion((String) request.getAttribute("descripcionProyecto"));
            }
            
            this.proyectoFacade.create(proyecto);
            
            
            
            
            
            Collection<Usuario> participantes = proyecto.getUsuarioCollection();
            if(participantes == null) {
                participantes = new ArrayList<Usuario>();
            }
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            participantes.add(usuario);
            proyecto.setUsuarioCollection(participantes);
            
            for(Usuario usu: participantes) {
                Collection<Proyecto> participando = usu.getProyectoCollection();
                participando.add(proyecto);
                usu.setProyectoCollection(participando);
                usuarioFacade.edit(usu);
            }
            
            Collection<Proyecto> liderando = usuario.getProyectoCollection1();
            liderando.add(proyecto);
            usuario.setProyectoCollection1(liderando);
            usuarioFacade.edit(usuario);
            Comentario comment = new Comentario();
            comment.setIdProyecto1(proyecto);
            comment.setNickname(usuario);
            String str = "El usuario " + usuario.getNickname() + " creó el proyecto";
            comment.setTexto(str);
            comentarioFacade.create(comment);
            List<Comentario> aux;
            if( proyecto.getComentarioCollection1() != null){
                aux =(List<Comentario>) proyecto.getComentarioCollection1();
            aux.add(comment);
            }
            else{
                aux = new ArrayList<Comentario>();
                aux.add(comment);
            }
            proyecto.setComentarioCollection1(aux);
            session.setAttribute("comentarios2", aux);
            session.setAttribute("participantes", null); //Lo borramos para los siguientes proyectos.
            
            RequestDispatcher rd;
            
            rd = getServletContext().getRequestDispatcher("/proyectoServlet?id=" + id);
            rd.forward(request, response);
        } else {
            //Redirigir al auxiliar servlet
            RequestDispatcher rd;
            
            rd = getServletContext().getRequestDispatcher("/auxiliarServlet");
            rd.forward(request, response);
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