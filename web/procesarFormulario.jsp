<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado de la búsqueda</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        /* Franja de "Iniciar sesión" y "Colegio Amigos De Don Bosco" */
        .header-bar {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            text-align: center;
            width: 100%;
            position: fixed;
            top: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* Estilo para el botón de volver */
        a.btn-volver {
            display: inline-block;
            padding: 10px 20px;
            background-color: #2c3e50; /* Cambiado a un tono más oscuro de azul */
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a.btn-volver:hover {
            background-color: #34495e; /* Cambiado a un tono más oscuro al hacer hover */
        }

        .content {
            text-align: center;
            margin-top:-15em; 
        }
    </style>
</head>
<body>
    <!-- Franja de "Iniciar sesión" y "Colegio Amigos De Don Bosco" -->
    <div class="header-bar">
        <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
        <a href="publica/login.jsp" class="btn-volver">Iniciar sesión</a>
    </div>

    <div class="content">
        <h2 style="text-align: center;">Resultado de la búsqueda:</h2>
        
        <p>Tipo de Material: <%= request.getParameter("tipoMaterial") %></p>
        <p>Nombre: <%= request.getParameter("nombre") %></p>
        <p>Autor: <%= request.getParameter("autor") %></p>

        <!-- Estilo mejorado para el botón de volver -->
        <p><a href="index.jsp" class="btn-volver">Volver a la página de búsqueda</a></p>
    </div>
</body>
</html>
