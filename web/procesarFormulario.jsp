<%-- 
    Document   : procesarFormulario
    Created on : 12-09-2023, 10:10:25 AM
    Author     : kevin
--%>

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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        
          <div class="bg-blue-400 px-5 py-3 font-bold text-white flex justify-between items-center fixed w-full">
        <span class="text-lg">Biblioteca - Colegio Ami  gos De Don Bosco</span>
        <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
    </div>
        
    <tittle>
        <h1> dd  </h1>
        <h1> dd </h1>
        <h1> dd </h1>
        <h1> Esta es la prubea a recorre lo demás </h1>
        
        <% 
        System.out.println (" prueba ");
        System.out.println (" Estoy capturando la info pasada");
        %>
        
    
</html>
