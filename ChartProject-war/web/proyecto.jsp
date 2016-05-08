<%-- 
    Document   : proyecto
    Created on : 01-may-2016, 21:08:35
    Author     : nacho
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        %>
        <h1>Proyecto: <%= proyecto.getNombre()%></h1><br/>
        Fecha de inicio: <%= formato.format(proyecto.getFechaInicio())%><br/>
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
            List<Comentario> comentarios;
            if((List<Comentario>) request.getAttribute("comentarios")!= null){
               comentarios = (List<Comentario>) request.getAttribute("comentarios");
            }
            else{
               comentarios = (List<Comentario>) proyecto.getComentarioCollection();
            }


        %>
        <table>
            <%                for(Comentario c : comentarios){


            %>

            <tr>

                <td>

                    <%=                       c.getNickname().getNickname()
                    %>
                    <br/>
                    <%=
                        c.getTexto()
                    %>

                </td>

            </tr>

                             
                      <%      
                        }
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

                    <%=                       tareas.get(i).getNombre()
                    %>
                    <br/>
                    <%= tareas.get(i).getDescripcion()
                        
                        %>
                    <br/>
                    <%=
                        tareas.get(i).getEstado()

                    %>
                    <br/>
                    <button><a href="EliminarTareaServlet?tareaId=<%=tareas.get(i).getTareaPK().getIdTarea()%>&proyectoId=<%=tareas.get(i).getTareaPK().getIdProyecto()%>">Eliminar</a></button>
                       <button><a href="CrearTareaServlet?idTarea=<%=tareas.get(i).getTareaPK().getIdTarea()%>&idProyecto=<%=tareas.get(i).getTareaPK().getIdProyecto()%>">Editar</a></button>

                </td>

            </tr>

            <%                }
            %>
        </table><br/>

        Timeline:<br/>
        en proceso<br/>
        <br/>
        <button><a href="CrearTareaServlet?idProyecto=<%=proyecto.getIdProyecto().toString()%>">añadir tarea</a></button>
        <button><a href="cargaPersonas">añadir persona</a></button>
        <button><a href="nuevoComentario.jsp">añadir comentario</a></button>
        <button><a href="principal.jsp">Volver</a></button>

    </body>
</html>
