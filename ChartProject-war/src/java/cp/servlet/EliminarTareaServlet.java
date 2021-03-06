package cp.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cp.ejb.ComentarioFacade;
import cp.ejb.TareaFacade;
import cp.entity.Comentario;
import cp.entity.Proyecto;
import cp.entity.Tarea;
import cp.entity.TareaPK;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
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
 * @author rocio
 */
@WebServlet(urlPatterns = {"/EliminarTareaServlet"})
public class EliminarTareaServlet extends HttpServlet {

    @EJB
    TareaFacade tareaFacade;
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
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        Proyecto p = (Proyecto) sesion.getAttribute("Proyecto");

        String tareaId = request.getParameter("tareaId");
        String proyectoId = request.getParameter("proyectoId");
        TareaPK tareaPK = new TareaPK();
        tareaPK.setIdProyecto(new BigInteger(proyectoId));
        tareaPK.setIdTarea(new BigInteger(tareaId));

        Tarea tarea = this.tareaFacade.find(tareaPK);

        this.tareaFacade.remove(tarea);

        List<Comentario> listaComentarios = this.comentarioFacade.findAll();
        try {
            for (Comentario c : listaComentarios) {
                if (c.getTarea().equals(tarea)) {
                    this.comentarioFacade.remove(c);
                }
            }
        } catch (RuntimeException e) {

        }

        Collection<Tarea> coleccionProyecto = p.getTareaCollection();
        coleccionProyecto.remove(tarea);
        p.setTareaCollection(coleccionProyecto);
        Comentario comment = new Comentario();
        comment.setIdProyecto1(p);
        comment.setNickname(usuario);
        String str = "El usuario " + usuario.getNickname() + " ha eliminado la tarea " + tarea.getNombre();
        comment.setTexto(str);
        comentarioFacade.create(comment);
        List<Comentario> aux =(List<Comentario>) p.getComentarioCollection1();
        aux.add(comment);
        p.setComentarioCollection1(aux);
        sesion.setAttribute("Proyecto", p);
        request.setAttribute("comentarios2", p.getComentarioCollection1());

        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/proyecto.jsp");
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
