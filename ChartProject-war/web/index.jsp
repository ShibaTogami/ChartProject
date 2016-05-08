<%-- 
    Document   : index
    Created on : 01-may-2016, 17:11:00
    Author     : shiba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart Project</title>
    </head>
    <body>
        <h1>Pagina principal</h1>
        <%HttpSession sesion = request.getSession();
            String retorno = (String) sesion.getAttribute("retorno");
        if (retorno!=null)
        {
            if (retorno.equals("caracteres")) {%>
            <h1>Esa contrase&ntilde;a conten&iacute;a caracteres no permitidos</h1>
            <%} else if (retorno.equals("usuario")) {%>
            <h1>Usuario no registrado!!</h1>
            <%} else if (retorno.equals("password")) {%>
            <h1>Contrase&ntilde;a Erronea!!</h1>
            <%}
            sesion.removeAttribute("retorno");
        }%>
        <form action="loginServlet" method="post">
            Usuario: <input type="text" size="20" name="usuario"/>
            Contrase&ntilde;a: <input type="password" size="20" name="password"/>
            <input type="submit" value="Log in">
        </form>
        <h6><a href="registro.jsp">Registro</a>    <a href="recuperarContrasena.jsp">Olvid&eacute; mi contrase&ntilde;a</a>
            <%sesion.removeAttribute("retorno");%>
    </body>
</html>
