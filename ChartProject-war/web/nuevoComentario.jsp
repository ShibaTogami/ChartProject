<%-- 
    Document   : nuevoComentario
    Created on : 08-may-2016, 16:28:53
    Author     : nacho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart</title>
    </head>
    <body>
        <h1>Introduzca su comentario:</h1><br/>
        <h5>introduzca un comentario nuevo:</h5><br/>
        <form name="comentario" action="subirComentario" method="post">
            <textarea name="comment" rows="10" cols="30"></textarea><br/>
            <input type="submit" value="enviar">
        </form>
    </body>
</html>
