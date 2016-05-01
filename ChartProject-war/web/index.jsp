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
        boolean retorno = new Boolean((String)sesion.getAttribute("retorno"));
        if (retorno==true)
        {%>
        <h1>Contrase&ntilde;a Erronea!!</h1>
        <%}
        %>
        <form action="loginServlet" method="post">
            Usuario: <input type="text" size="20" name="usuario"/>
            Contrase&ntilde;a: <input type="password" size="20" name="password"/>
            <input type="submit" value="Log in">
        </form>
        <%sesion.removeAttribute("retorno");%>
    </body>
</html>
