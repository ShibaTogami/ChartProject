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
        <h1>Usuario <%= user.getNickname() %></h1>
        Email: <%= user.getEmail() %><br/>
        Web: <%= user.getWebPersonal() %><br/>
        Miembro desde: <%= user.getFechaRegistro()%><br/>
        Ultima conexion: <%= user.getUltimaConexion()%> <br/>
        Karma: <%= user.getKarma()%><br/><br/>
        <a href="LogoutServlet">Logout</a><br/>
            
        
        <%--
            Todo lo que se va a mostrar a continuacion sería necesario enlazarlo
            con el correspondiente servlet para acceder directamente al proyecto
            Muestro los proyectos en los que está involucrado
        --%>
        <table>
        <tr>
            <th>Proyectos</th>
        </tr>
        
        <% for(Proyecto pr : proyectos){ %>
        
        <tr>
            <th>
                <%= pr.getNombre() %>
            </th>
        </tr>
        
        <%}%>
         </table>
                
        <%--
            Muestro los proyectos que lidera, en el caso de que lidere alguno
        --%>
        <% if(!lidera.isEmpty()){ %>
            <table>
            <tr>
                <th>Lidera</th>
            </tr>
        
            <% for(Proyecto pr : lidera){ %>
        
            <tr>
                <th>
                    <%= pr.getNombre() %>
                </th>
            </tr>
        
            <%}%>
        
            </table>
        <%}%>
        
        <%--
            Muestro los comentarios realizados por el usuario
        --%>
        <table>
         <tr>
            <th>Comentarios realizados</th>
        </tr>
        
        <% for(Comentario cm : comentarios){ %>
        
        <tr>
            <th>
                <%= cm.getTexto() %>
            </th>
        </tr>
        
        <%}%>
        
        </table>
        
    </body>
</html>
