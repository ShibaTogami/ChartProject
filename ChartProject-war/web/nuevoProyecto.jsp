<%-- 
    Document   : nuevoProyecto
    Created on : 02-may-2016, 16:21:30
    Author     : pablo
--%>

<%@page import="cp.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%//bump for interest
    List<Usuario> participantes = null;
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("participantes") != null) {
        participantes = (List<Usuario>) sesion.getAttribute("participantes");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear proyecto</title>
    </head>
    <body>
        <h1>Crear proyecto</h1>
        <form name="input" action="auxiliarServlet" method="post">
            Nombre: <input type="text" maxlength="50" size="20" name="nombreProyecto" value="<%if (request.getAttribute("nombreProyecto") != null) {%><%=request.getAttribute("nombreProyecto")%> 
                           <%}%>"></br>              
            Participantes: 
            <%
                if (participantes != null) {
                    for (Usuario usuario : participantes) {
            %><%=usuario.getNickname()%>&nbsp;<%
                                }
                            }
            %>
            </br>
            A&ntilde;adir participante: <input type="text" maxlength="30" size="20" name="nuevoParticipanteProyecto"></br>
            <input type="submit" value="Anadir" name="boton"></br>

            Descripci&oacute;n: </br>
            <textarea name="descripcionProyecto" rows="100" cols="30"><%if (request.getAttribute("descripcionProyecto") != null) {%><%=request.getAttribute("descripcionProyecto")%>
                <% } else {%>Escriba una descripci&oacute;n para los nuevos participantes del proyecto.
                <% }%>
            </textarea></br>
            <input type="submit" value="Guardar" name="boton">
            &nbsp;


        </form>
        <form name="input" action="principal.jsp" method="post">
            &nbsp;<button>Cancelar</button>
        </form>


    </body>
</html>
