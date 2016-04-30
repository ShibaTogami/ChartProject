<%-- 
    Document   : nuevaTarea
    Created on : 29-abr-2016, 20:30:06
    Author     : rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cp.entity.Tarea" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear/Editar tarea</title>
    </head>
    <body>
        <% Tarea tarea = (Tarea) request.getAttribute("tarea");
            String idTarea = "";
            String idProyecto = "";
            String nombre = "";
            String fechaInicio = "";
            String descripcion = "";
            String estado = "";
            String prioridad = "";
            if (tarea != null) {
                idTarea = tarea.getTareaPK().getIdTarea().toString();
                idProyecto = tarea.getTareaPK().getIdProyecto().toString();
                nombre = tarea.getNombre();
                fechaInicio = tarea.getFechaInicio().toString();
                descripcion = tarea.getDescripcion();
                estado = tarea.getEstado();
                prioridad = tarea.getPrioridad().toString();
            }
            String selected = "";
        %>
        <h1>Crear/Editar tarea</h1>
        <form method="post" action="GuardarTareaServlet">
            ProyectoId = <%=idProyecto%>
            <% if (!"".equals(idTarea)) {%>
            TareaId = <%=idTarea%></br>
            <input type="hidden" name="id" value="<%= idTarea%>" />
            <%}%>

            Nombre: <input name="nombre" value="<%=nombre%>"/></br>
            Fecha de inicio: <input name="fechaInicio" value="<%=fechaInicio%>"/></br>
            Descripción: <textarea name="descripcion" value="<%=descripcion%>"/></br>
            Estado: <select name="estado">
                <% if (estado.equalsIgnoreCase("To Do")) {
                        selected = "selected";%>
                <option value="To Do" selected="<%=selected%>">To Do</option>
                <%}%>
                <% if (estado.equalsIgnoreCase("In Process")) {
                        selected = "selected";%>
                <option value="In Process" selected="<%=selected%>">In Process</option>
                <%}%>
                <% if (estado.equalsIgnoreCase("Done")) {
                        selected = "selected";%>
                <option value="Done" selected="<%=selected%>">Done</option>
                <%}%>
                <% if (estado.equalsIgnoreCase("Testing")) {
                        selected = "selected";%>
                <option value="Testing" selected="<%=selected%>">Testing</option>
                <%}%>
            </select>
            Prioridad: <select name="prioridad">
                <% selected = "";
                    if (prioridad.equalsIgnoreCase("1")) {
                        selected = "selected";%>
                <option value="1" selected="<%=selected%>">Alta</option>
                <%}%>
                <%if (prioridad.equalsIgnoreCase("2")) {
                        selected = "selected";%>
                <option value="2" selected="<%=selected%>">Media</option>
                <%}%>
                <%if (prioridad.equalsIgnoreCase("3")) {
                        selected = "selected";%>
                <option value="3" selected="<%=selected%>">Baja</option>
                <%}%>
            </select>
            <button>Guardar</button> </t>
    </form>
    <button><a href="/proyecto.jsp">Cancelar</a></button>
</body>
</html>
