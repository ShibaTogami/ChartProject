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

            Proyecto proyecto = (Proyecto) session.getAttribute("Proyecto");

        %>
        <h1>Proyecto: <%= proyecto.getNombre()%></h1><br/>
        Fecha de inicio: <% proyecto.getFechaInicio().toString().substring(0, 9); %><br/>
        Participantes:<br/>
        <table>
            <%
                List<Usuario> usuarios = (List<Usuario>) proyecto.getUsuarioCollection();
                int tsize = usuarios.size();
                int users = 0;
                while (users < tsize) {

            %>

            <tr>

                <%                    for (int i = 0; i < 6; i++) {
                        try {

                %>
                <td><%= usuarios.get(users).getNickname()%></td>
                <%
                            users++;
                        } catch (RuntimeException e) {
                            users = tsize;
                            break;
                        }

                    }

                %>

            </tr>

            <%                        }

            %>
        </table><br/>

        Descripcion:<br/>
        <%= proyecto.getDescripcion() %><br/>
        Comentarios:<br/>
        <%

            List<Comentario> comentarios = (List<Comentario>) proyecto.getComentarioCollection();
            int tam = comentarios.size();
            int comment = 0;

        %>
        <table>
            <%                while(comment < tam) {
                try{

            %>

            <tr>

                <td>

                    <%=                       comentarios.get(comment).getNickname().getNickname()
                    %>
                    <br/>
                    <%=
                        comentarios.get(comment).getTexto()
                    %>

                </td>

            </tr>

            <%                comment++; }
                            catch(RuntimeException e){
                            break;
                        }
                        }
            %>

        </table><br/>

        <h5>introduzca un comentario nuevo:</h5><br/>
        <form name="comentario" action="subirComentario" method="post">
            <textarea name="comment" rows="10" cols="30"></textarea>
            <input type="submit" value="enviar">
        </form>
        
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
