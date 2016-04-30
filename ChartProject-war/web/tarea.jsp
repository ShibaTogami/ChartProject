<%-- 
    Document   : tarea
    Created on : 30-abr-2016, 12:35:11
    Author     : rocio
--%>

<%@page import="java.util.List"%>
<%@page import="cp.entity.Comentario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cp.entity.Tarea"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tarea</title>
    </head>
    <body>
        <% Tarea tarea = (Tarea) request.getAttribute("tarea");
        List<Comentario> listaComentarios = (List<Comentario>) request.getAttribute("listaComentarios");
        %>
        <h1><%= tarea.getNombre()%></h1></br>
        Fecha de inicio: <%= tarea.getFechaInicio().toString()%></br>
        Descripcion: <%= tarea.getDescripcion()%></br>
        Estado: <%= tarea.getEstado()%></br>
        Prioridad: <%= tarea.getPrioridad()%></br>
        
        <h2>Timeline de <%= tarea.getNombre()%></h2></br>
        <% for(Comentario c : listaComentarios){%>
            <%= c.getTexto() %></br>
            ------------------------------------------------------------------</br>
        <% } %>
        
        <button><a href="CrearTareaServlet?idTarea=<%=tarea.getTareaPK().getIdTarea()%>&idProyecto=<%=tarea.getTareaPK().getIdProyecto()%>">Editar Tarea</a></button>
        <button><a href="EliminarTareaServlet?idTarea=<%=tarea.getTareaPK().getIdTarea()%>&idProyecto=<%=tarea.getTareaPK().getIdProyecto()%>">Eliminar Tarea</a></button>
    </body>
</html>
