<%-- 
    Document   : addPersona
    Created on : 02-may-2016, 10:34:49
    Author     : nacho
--%>

<%@page import="cp.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>añadir persona</title>
    </head>
    <body>
        <h1>seleccione las personas a añadir:</h1>
        <table>
            <%

                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                List<Usuario> selected = (List<Usuario>) request.getAttribute("seleccion");

                for (Usuario u : usuarios) {

            %>

            <tr>     
                <td>
                    <%    u.getNickname();%>
                </td>
                <td>
                    <button><a href="addUserToListServlet?nombre=<%=u.getNickname()%>">añadir</a></button>
                </td>
            </tr>
            <%}%>
        </table>
        <button><a href="addPersonas">finalizar</a></button>
        <button><a href="proyectoServlet">cancelar</a></button>
    </body>
</html>
