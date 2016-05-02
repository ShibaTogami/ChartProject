<%-- 
    Document   : proyecto
    Created on : 01-may-2016, 21:08:35
    Author     : nacho
--%>

<%@page import="cp.entity.Tarea"%>
<%@page import="cp.entity.Comentario"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cp.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="cp.entity.Proyecto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart</title>
    </head>
    <body>
        <%

            Proyecto proyecto = (Proyecto) request.getAttribute("Proyecto");

        %>
        <h1>Proyecto: <%= proyecto.getNombre()%></h1><br/>
        Fecha de inicio: <% proyecto.getFechaInicio().toString().substring(0, 9); %><br/>
        Participantes:<br/>
        <table>
            <%
                Usuario[] usuarios = (Usuario[]) proyecto.getUsuarioCollection().toArray();
                int tsize = usuarios.length;
                for (int i = 0; i < tsize / 6 + 1; i++) {
            %>

            <tr>

                <%
                    for (int j = 0; j < 6; j++) {
                %>
                <td><a href="perfilServlet?user=<%=usuarios[i]%>"><%= usuarios[i].getNickname()%></a></td>
                    <%
                        }

                    %>

            </tr>

            <%                        }

            %>
        </table><br/>

        Descripcion:<br/>
        <% proyecto.getDescripcion(); %><br/>
        Comentarios:<br/>
        <%

            Comentario[] comentarios = (Comentario[]) proyecto.getComentarioCollection().toArray();

        %>
        <table>
            <%                for (int i = 0; i < comentarios.length; i++) {

            %>

            <tr>

                <td>

                    <%                        comentarios[i].getNickname().getNickname();
                    %>
                    <br/>
                    <%
                        comentarios[i].getTexto();

                    %>

                </td>

            </tr>

            <%                }
            %>

        </table><br/>

        Tareas:<br/>
        <%
            Tarea[] tareas = (Tarea[]) proyecto.getTareaCollection().toArray();

        %>
        <table>
            <%                for (int i = 0; i < tareas.length; i++) {

            %>

            <tr>

                <td>

                    <%                        tareas[i].getNombre();
                    %>
                    <br/>
                    <%
                        tareas[i].getEstado();

                    %>

                </td>

            </tr>

            <%                }
            %>
        </table><br/>
        
        Timeline:<br/>
        en proceso<br/>
       <br/>
       <button><a href="ServletPrueba">añadir tarea</a></button>
       <button><a href="cargaPersonas">añadir persona</a></button>

    </body>
</html>
