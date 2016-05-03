/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.servlet;

import cp.entity.Tarea;
import cp.entity.TareaPK;
import cp.ejb.TareaFacade;
import cp.entity.Tarea;
import cp.entity.TareaPK_;
import cp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rocio
 */
@WebServlet(name = "GuardarTareaServlet", urlPatterns = {"/GuardarTareaServlet"})
public class GuardarTareaServlet extends HttpServlet {
    @EJB
    TareaFacade tareaFacade;
    

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
        String tareaId, proyectoId;
        Tarea tarea;
        
        proyectoId = request.getParameter("proyectoId");
        tareaId = request.getParameter("tareaId");
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        
        if("".equals(tareaId)){
            tarea = new Tarea();
            
            tarea.setFechaInicio(new Date());
        }else{
            tarea = tareaFacade.find(new TareaPK(new BigInteger(tareaId), new BigInteger(proyectoId)));
        }
        
        tarea.setNombre(request.getParameter("nombre"));
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setEstado(request.getParameter("estado"));
        tarea.setPrioridad(new BigInteger(request.getParameter("prioridad")));
        
        if("".equals(tareaId)){
            this.tareaFacade.create(tarea);
            String str = "El usuario "+usuario.getNickname() + " ha creado la tarea " + tarea.getNombre();
        }else{
            this.tareaFacade.edit(tarea);
            String str = "El usuario " + usuario.getNickname() + " ha actualizado la tarea " + tarea.getNombre();
            request.setAttribute("strTarea", str);
        }
        
        request.setAttribute("tarea", tarea);
        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/");
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
