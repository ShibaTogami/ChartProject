/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.ejb.ProyectoFacade;
import cp.entity.Tarea;
import cp.entity.TareaPK;
import cp.ejb.TareaFacade;
import cp.entity.Proyecto;
import cp.entity.Tarea;
import cp.entity.TareaPK_;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
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
@WebServlet(name = "GuardarTareaServlet", urlPatterns = {"/GuardarTareaServlet"})
public class GuardarTareaServlet extends HttpServlet {

    @EJB
    TareaFacade tareaFacade;
    @EJB
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
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        Proyecto p = (Proyecto) sesion.getAttribute("Proyecto");
        Tarea t = (Tarea) sesion.getAttribute("tarea");

        String tareaId, proyectoId;
        Tarea tarea;

        proyectoId = request.getParameter("idProyecto");
        tareaId = request.getParameter("idTarea");
        
        BigDecimal idProyecto = new BigDecimal(proyectoId);
        
        Proyecto proyecto = this.proyectoFacade.find(idProyecto);

        if ("".equals(tareaId) || tareaId == null) {
            tarea = new Tarea();
            tarea.setTareaPK(new TareaPK());
            tarea.getTareaPK().setIdProyecto(proyecto.getIdProyecto().toBigInteger());
            tarea.setFechaInicio(new Date());
            tarea.setProyecto(proyecto);
        } else {
            BigDecimal idTarea = new BigDecimal(tareaId);
            tarea = tareaFacade.find(new TareaPK(idTarea.toBigInteger(), idProyecto.toBigInteger()));
        }

        tarea.setNombre(request.getParameter("nombre"));
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setEstado(request.getParameter("estado"));
        tarea.setPrioridad(new BigInteger(request.getParameter("prioridad")));
        
        Collection<Tarea> coleccionProyecto = p.getTareaCollection();

        if ("".equals(tareaId)|| tareaId == null) {
            this.tareaFacade.create(tarea);
            String str = "El usuario " + usuario.getNickname() + " ha creado la tarea " + tarea.getNombre();
            request.setAttribute("strTarea", str);
        } else {
            this.tareaFacade.edit(tarea);
            String str = "El usuario " + usuario.getNickname() + " ha actualizado la tarea " + tarea.getNombre();
            request.setAttribute("strTarea", str);
            coleccionProyecto.remove(t);
        }
        
        
        coleccionProyecto.add(tarea);
        p.setTareaCollection(coleccionProyecto);
        sesion.setAttribute("Proyecto", p);

        request.setAttribute("tarea", tarea);

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
