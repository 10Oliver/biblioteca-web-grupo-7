<%-- 
    Document   : vistaPrivada
    Created on : 11-29-2023, 08:13:48 PM
    Author     : Oliver
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Integer vistaSeleccionada = (Integer) request.getAttribute("vistaSeleccionada");
    List<Map<String, String>> listaUsuarios = (List<Map<String, String>>) request.getAttribute("listaUsuarios");

    if (vistaSeleccionada == null) {
        vistaSeleccionada = 0; // Este es tu valor por defecto
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/configuraciones.jsp"%>
    </head>
    <body>
        <div class="flex h-screen flex-col bg-slate-100">
            <div class="flex flex-row items-center justify-between bg-blue-400 px-5 py-3 font-bold text-white">
                <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
                <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Cerrar sesión</a>
            </div>
            <div class="grid flex-1 grid-cols-12">
                <div class="col-span-3 col-start-1 flex flex-col items-center justify-center bg-white">
                    <a class="my-1 w-full bg-teal-600 py-3 pl-3 font-medium text-white hover:bg-teal-500 focus:bg-teal-400 focus:text-gray-800" href="${contextPath}/panel.do?vista=usuario">Usuarios</a>
                    <a class="my-1 w-full bg-teal-600 py-3 pl-3 font-medium text-white hover:bg-teal-500 focus:bg-teal-400 focus:text-gray-800" href="${contextPath}/panel.do?vista=inventario">Inventario</a>
                    <a class="my-1 w-full bg-teal-600 py-3 pl-3 font-medium text-white hover:bg-teal-500 focus:bg-teal-400 focus:text-gray-800" href="${contextPath}/panel.do?vista=prestamo">Préstamo</a>
                    <a class="my-1 w-full bg-teal-600 py-3 pl-3 font-medium text-white hover:bg-teal-500 focus:bg-teal-400 focus:text-gray-800" href="${contextPath}/panel.do?vista=configuracion">Configuraciones</a>
                </div>
                <div id="contenedor" class="col-span-9 col-start-4">
                    <% if (vistaSeleccionada == 1) {%>
                    <jsp:include page="./vistas/usuarios.jsp">
                        <jsp:param name="listaUsuarios" value="${listaUsuarios}" />
                    </jsp:include>
                    <%} else if (vistaSeleccionada == 2) {%>
                    <%@ include file="./vistas/inventario.jsp"%>
                    <%} else if (vistaSeleccionada == 3) {%>
                    <%@ include file="./vistas/prestamo.jsp"%>
                    <%} else if (vistaSeleccionada == 4) {%>
                    <%@ include file="./vistas/configuraciones.jsp"%>
                    <%} else {%>
                    <h1>Sorry mi estimado</h1
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
</script>