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
            String idProyecto = request.getParameter("idProyecto");
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
            session.setAttribute("tarea", tarea);
        %>
        <h1>Crear/Editar tarea</h1>
        <form method="post" action="GuardarTareaServlet">
            ProyectoId = <%=idProyecto%>
            <input type="hidden" name="idProyecto" value="<%=idProyecto%>"/></br>
            <% if (!"".equals(idTarea)) {%>
            TareaId = <%=idTarea%></br>
            <input type="hidden" name="idTarea" value="<%= idTarea%>"/></br>
            <%}%>

            Nombre: <input name="nombre" value="<%=nombre%>"/></br>
            Fecha de inicio: <%=fechaInicio%>
            Estado: <select name="estado">
                <% if (estado.equalsIgnoreCase("To Do")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="To Do" <%=selected%>>To Do</option>
                <% if (estado.equalsIgnoreCase(
                            "In Process")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="In Process" <%=selected%>>In Process</option>

                <% if (estado.equalsIgnoreCase(
                            "Done")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="Done" <%=selected%>>Done</option>
                <% if (estado.equalsIgnoreCase(
                            "Testing")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>%>
                <option value="Testing" <%=selected%>>Testing</option>

            </select></br>
            Prioridad: <select name="prioridad">
                <% selected = "";

                    if (prioridad.equalsIgnoreCase(
                            "1")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="1" <%=selected%>>Alta</option>
                <%if (prioridad.equalsIgnoreCase(
                            "2")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="2" <%=selected%>>Media</option>
                <%if (prioridad.equalsIgnoreCase(
                            "3")) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }%>
                <option value="3" <%=selected%>>Baja</option>
            </select></br>
            Descripción: <textarea name="descripcion" rows="10" cols="30">Introduzca una descripci&oacute;n de la tarea</textarea> </br>
            <button>Guardar</button> </t>
    </form>
                <button><a href="proyectoServlet?idProyecto=<%=idProyecto%>">Cancelar</a></button>
</body>
</html>
