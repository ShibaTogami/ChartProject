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
    if(request.getAttribute("participantes") != null) {
        participantes = (List<Usuario>) request.getAttribute("participantes");
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
        Nombre: <input type="text" maxlength="50" size="20" name="nombreProyecto" value="
        <%if (request.getAttribute("nombreProyecto") != null) {%>
        <%=request.getAttribute("nombreProyecto")%> 
        <%}%>"></br>              
        Fecha de inicio: <input type="text" maxlength="10" name="fechaInicioProyecto" value="
        <%if (request.getAttribute("fechaInicioProyecto") != null) {%>
        <%=request.getAttribute("fechaInicioProyecto")%>
        <%} else {%>
        dd/mm/aaaa
        <% }%>"></br>
        Participantes: 
        <%
            if (participantes != null) {
                for (Usuario usuario: participantes) {
                    %><%=usuario.getNickname()%>&nsbp;<%
                }
            }
            %>
            </br>
            A&ntilde;adir participante: <input type="text" maxlength="30" size="20" name="nuevoParticipanteProyecto"></br>
            <input type="submit" value="Anadir" name="boton">
            
            Descripci&oacu&oacute;n: </br>
            <textarea name="descripcionProyecto" rows="100" cols="30">
            <%if(request.getAttribute("descripcionProyecto") != null) {%>
            <%=request.getAttribute("descripcionProyecto")%>
            <% } else {%>
            Escriba una descripci&oacute;n para los nuevos participantes del proyecto.
            <% } %>
            </textarea></br>
            <input type="submit" value="Guardar" name="boton">
            &nbsp;
            
            
        </form>
            <form name="input" action="principal.jsp" method="post">
                &nbsp;<button>Cancelar</button>
            </form>
            
        
    </body>
</html>
