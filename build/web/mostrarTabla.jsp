<%-- 
    Document   : mostrarTabla
    Created on : 12-09-2023, 01:44:47 PM
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/configuraciones.jsp"%>
        <style>
        body {
            font-family: Arial, sans-serif;
        }

        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 2rem; /* Ajustado el margen superior */
        }

        label {
            margin-bottom: 0.5rem;
        }

        input[type="text"] {
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .center-button {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
<body class="flex flex-col h-screen bg-slate-100">
    <!-- Franja de color en la parte superior -->
    <div class="bg-blue-400 px-5 py-3 font-bold text-white flex justify-between items-center fixed w-full">
        <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
        <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
    </div>
    
    
     <!-- Contenido de la tabla -->
    <div class="form-container">
        
        <h1> -</h1>
        <h1> -</h1>
       
        <h1> -</h1>
       
        <h2>Resultados de la búsqueda:</h2>
       
        <!-- Tabla para mostrar los resultados -->
        
        <table>
            <thead>
                <tr>
                    <th>Nombre  </th>
                    <th>Autor  </th>
                    <th>Codigo  </th>
                </tr>
            </thead>
            <tbody>
                <%-- Creamos la tabla para buscar  --%>
                <c:forEach var="material" items="${resultados}">
                    <tr>
                        <td> Kevin </td>
                        <td> Arturo  </td>
                        <td> KV000   </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    
    
     
    
    
    
    