<%-- 
    Document   : principal
    Created on : 02-may-2016, 11:39:40
    Author     : pablo
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="cp.entity.Proyecto"%>
<%@page import="cp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    
    if(session.getAttribute("usuario") == null) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
    
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Proyecto> proyectos = (List<Proyecto>)usuario.getProyectoCollection();
    %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos</title>
    </head>
    <body>
        <a href="perfilServlet">Perfil de usuario</a></br>
        <a href="nuevoProyecto.jsp">Crear un nuevo proyecto</a>
            <h1>Tus proyectos actuales</h1>
            <%
                int i = 1;
                for (Proyecto proyecto: proyectos) {
                    %>
                    <a href="proyectoServlet?idProyecto=<%=proyecto.getIdProyecto()%>"><%=proyecto.getNombre()%> </a>
                    &nbsp;
                    
                    <%
                        if ((i % 4) == 0) {
                            %></br><%
                        }
                        i++;
                }
                
                %>
                </br></br>
                <a href="logoutServlet">Logout</a>
    </body>
</html>
