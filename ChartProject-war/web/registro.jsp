<%-- 
    Document   : registro
    Created on : 01-may-2016, 19:36:39
    Author     : shiba
--%>

<%@page import="cp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart Project (registro)</title>
    </head>
    <body>
        <h3>Introduzca los datos del usuario:</h3>
        <br/>
        <%HttpSession sesion = request.getSession();
        if (sesion.getAttribute("error")!=null) //si venimos de un error
        {%>
        <br/><h3>Se ha producido un error</h3>
        <% if (sesion.getAttribute("error").equals("password"))
            {%>
            <br/><h3>El password es diferente de su repetici&oacute;n</h3>
            <%} else if (sesion.getAttribute("error").equals("email"))
                {%>
                <br/><h3>El e-mail es diferente de su repetici&oacute;n</h3>       
                <%}
            else
            {%>
                <br/><h3>Tanto el e-mail como el password difieren de sus repeticiones</h3>
            <%}
        sesion.removeAttribute("error");
        }%>
        <%if(sesion.getAttribute("usuarioTemporal")!=null)
          {
            Usuario usuarioTemporal = (Usuario)sesion.getAttribute("usuarioTemporal");%>
           <form method="post" action="registroServlet">
            <table>
                <tr>
                    <th><br/>Nombre de usuario:</th>
                    <th><input type="text" name="usuario" value="<%=usuarioTemporal.getNickname()%>"/></th>    
                </tr>
                <tr>
                    <th><br/>Contrase&ntilde;a:</th>
                    <th><input type="password" name="password" value="<%=usuarioTemporal.getPassword()%>"/></th>
                </tr>
                <tr>
                    <th><br/>Confirmar contrase&ntilde;a:</th>
                    <th><input type="password" name="confirmaPassword"/></th>
                </tr>
                <tr>
                    <th><br/>E-mail:</th>
                    <th><input type="text" name="email" value="<%=usuarioTemporal.getEmail()%>"/></th>
                </tr>
                <tr>
                    <th><br/>confirmar e-mail:</th>
                    <th><input type="text" name="confirmaEmail"/></th>
                </tr>
                <tr>
                    <th><br/>Pregunta secreta:</th>
                    <th><input type="text" name="preguntaSecreta" value="<%=usuarioTemporal.getPregunta()%>"/></th>
                </tr>
                <tr>
                    <th><br/>Respuesta a la pregunta secreta:</th>
                    <th><input type="text" name="respuestaSecreta"/></th>
                </tr>
            </Table>
            <br/><input type="submit" value="Enviar"/>
         </form>   
         <%} else {%>
        <form method="post" action="registroServlet">
            <table>
                <tr>
                    <th><br/>Nombre de usuario:</th>
                    <th><input type="text" name="usuario" value=""/></th>    
                </tr>
                <tr>
                    <th><br/>Contrase&ntilde;a:</th>
                    <th><input type="password" name="password"/></th>
                </tr>
                <tr>
                    <th><br/>Confirmar contrase&ntilde;a:</th>
                    <th><input type="password" name="confirmaPassword"/></th>
                </tr>
                <tr>
                    <th><br/>E-mail:</th>
                    <th><input type="text" name="email"/></th>
                </tr>
                <tr>
                    <th><br/>confirmar e-mail:</th>
                    <th><input type="text" name="confirmaEmail"/></th>
                </tr>
                <tr>
                    <th><br/>Pregunta secreta:</th>
                    <th><input type="text" name="preguntaSecreta"/></th>
                </tr>
                <tr>
                    <th><br/>Respuesta a la pregunta secreta:</th>
                    <th><input type="text" name="respuestaSecreta"/></th>
                </tr>
            </Table>
            <br/><input type="submit" value="Enviar"/> <input type="reset" value="Restablecer Campos">
        </form>
        <%}%>
    </body>
</html>
