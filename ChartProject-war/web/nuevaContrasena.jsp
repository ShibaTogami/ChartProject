<%-- 
    Document   : nuevaContrasena
    Created on : 02-may-2016, 16:31:51
    Author     : shiba
--%>

<%@page import="cp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart Project (Escoger Nueva Contraseña)</title>
    </head>
    <%HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario)sesion.getAttribute("usuario");%>
    <body>
        <%String error = (String)session.getAttribute("error");
        if (error!=null && error.equals("Passwords diferentes"))
        {%>
        <h2> Error: Contraseñas Diferentes.</h2>    
        <%}%>
        Escoja una nueva contrase&ntilde;a para el usuario <%=usuario.getNickname()%>:
        <br/>
        <form action="cambiarPasswordServlet" method="post">
            <input type="password" name="pass1"/>
            <br/> Repita la nueva contra&ntilde;a:
            <br/><input type="password" name="pass2"/>
            <input type="submit" value="enviar"/>
        </form>
    </body>
</html>
