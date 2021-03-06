<%-- 
    Document   : perfil
    Created on : 01-may-2016, 22:36:05
    Author     : Sergio
--%>

<%@page import="cp.entity.Comentario"%>
<%@page import="java.util.Collection"%>
<%@page import="cp.entity.Proyecto"%>
<%@page import="cp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%
        Usuario user = (Usuario) session.getAttribute("usuario");
        Collection<Proyecto> proyectos = (Collection<Proyecto>)request.getAttribute("proyectos");
        Collection<Proyecto> lidera = (Collection<Proyecto>)request.getAttribute("lidera");
        Collection<Comentario> comentarios = (Collection<Comentario>)request.getAttribute("comentarios");
    %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de perfil</title>
    </head>
    <body>
        <h3>Usuario <%= user.getNickname() %></h3>
        Email: <%= user.getEmail() %><br/>
        Web: <%= user.getWebPersonal() %><br/>
        Miembro desde: <%= user.getFechaRegistro()%><br/>
        Ultima conexion: <%= user.getUltimaConexion()%> <br/>
        Karma: <%= user.getKarma()%><br/>
        <a href="logoutServlet">Logout</a><br/><br/>
            
        
        <%--
            Muestro los proyectos en los que está involucrado
        --%>
        <% if(proyectos != null && !proyectos.isEmpty()){ %> <%--comprobmaos que la coleccion no es nula y vacia--%>
        <table border="1">
        <tr>
            <th><b>Proyectos</b></th>
        </tr>
            <% for(Proyecto pr : proyectos){ %>
            <tr>
                <td>
                    <a href="proyectoServlet?idProyecto=<%= pr.getIdProyecto() %>"><%= pr.getNombre() %></a>
                </td>
            </tr>
            <%}%>
         </table><br/>
        <%}else{%>
        No lidera participa en ning&uacute;n proyecto<br/><br/>
        <%}%>
        
       
        <%--
            Muestro los proyectos que lidera, en el caso de que lidere alguno
        --%>
        <% if(lidera != null && !lidera.isEmpty()){ %>
            <table border="1">
            <tr>
                <th><b>Lidera</b></th>
            </tr>
            <% for(Proyecto pr : lidera){ %>       
            <tr>
                <td>
                    <a href="proyectoServlet?idProyecto=<%= pr.getIdProyecto() %>"><%= pr.getNombre() %></a>
                </td>
            </tr>   
            <%}%>
            </table><br/>
        <%}else{%>
        No lidera ning&uacute;n proyecto<br/><br/>
        <%}%>
        
        <%--
            Muestro los comentarios realizados por el usuario
        --%>
        <% if (comentarios != null && !comentarios.isEmpty()) {%>
        <table>
         <tr>
            <th><b>Comentarios realizados</b></th>
        </tr>
       
        <% for(Comentario cm : comentarios){ %>
        <tr>
            <td>
                <%= cm.getTexto() %>
            </td>
        </tr>
        <%}%>
        </table>
        <%} else {%>
        El usuario aun no ha comentado.
        <%}%>
        </br></br>
        <a href="RedireccionAPrincipalServlet?id=<%= user.getNickname() %>">Volver a principal</a>
        <%--<a href="loginServlet?usuario=<%= user.getNickname() %>&password=<%= user.getPassword() %>">Volver a principal</a>--%>
    </body>
</html>
