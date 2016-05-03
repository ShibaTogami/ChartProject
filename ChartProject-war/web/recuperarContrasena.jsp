<%-- 
    Document   : recuperarContrasena
    Created on : 02-may-2016, 13:28:36
    Author     : shiba
--%>

<%@page import="cp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart Project (Recuperaci&oacute;n de contrase&ntilde;a)</title>
    </head>
    <body>
        <%HttpSession sesion = request.getSession();
        Usuario usuario =(Usuario) sesion.getAttribute("usuario");
        if (usuario == null)
        {%>
        <form action="recuperarContrasenaServlet" method="post">
            <br/>Introduzca usuario cuya contrase&ntilde;a quiera recuperar
            <br/><input type="text" name="usuario"/>
            <br/><input type="submit" value="enviar">
        </form>
        <%} else {%>
        <form action="recuperarContrasenaServlet" method="post">
            <br/>Introduzca la respuesta a la pregunta secreta: "<%=usuario.getPregunta()%>"
            <br/><input type="text" name="respuesta"/>
            <br/><input type="submit" value="enviar">
        </form>
        <%}%>
        <%--queda tratar el caso de que envie mal el usuario o la respuesta--%>
    </body>
</html>
