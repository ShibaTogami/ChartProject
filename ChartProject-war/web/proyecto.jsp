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
                List<Usuario> usuarios = (List<Usuario>) proyecto.getUsuarioCollection();
                int tsize = usuarios.size();
                for (int i = 0; i < tsize / 6 + 1; i++) {
            %>

            <tr>

                <%
                    for (int j = 0; j < 6; j++) {
                %>
                <td><%= usuarios.get(i).getNickname()%></td>
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

            List<Comentario> comentarios = (List<Comentario>) proyecto.getComentarioCollection();

        %>
        <table>
            <%                for (int i = 0; i < comentarios.size(); i++) {

            %>

            <tr>

                <td>

                    <%                        comentarios.get(i).getNickname().getNickname();
                    %>
                    <br/>
                    <%
                        comentarios.get(i).getTexto();
                    %>

                </td>

            </tr>

            <%                }
            %>

        </table><br/>

        Tareas:<br/>
        <%
            List<Tarea> tareas = (List<Tarea>) proyecto.getTareaCollection();

        %>
        <table>
            <%                for (int i = 0; i < tareas.size(); i++) {

            %>

            <tr>

                <td>

                    <%                        tareas.get(i).getNombre();
                    %>
                    <br/>
                    <%
                        tareas.get(i).getEstado();

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
