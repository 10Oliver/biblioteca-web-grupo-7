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
    <script>
        function mostrarCamposAdicionales() {
            var tipoMaterial = document.getElementById("tipoMaterial");
            var contenedorLibro = document.getElementById("contenedorLibro");
            var mensajeTextoBuscar = document.getElementById("mensajeTextoBuscar");
            var tipoMaterialSeleccionado = tipoMaterial.options[tipoMaterial.selectedIndex].text.toLowerCase();

            mensajeTextoBuscar.innerHTML = "Ingrese el texto a buscar para " + tipoMaterialSeleccionado + ":";

            if (tipoMaterialSeleccionado === "libro" || tipoMaterialSeleccionado === "revista") {
                contenedorLibro.style.display = "block";
            } else {
                contenedorLibro.style.display = "none";
            }
        }
    </script>
</head>
<body class="flex flex-col h-screen bg-slate-100">
    <!-- Franja de color en la parte superior -->
    <div class="bg-blue-400 px-5 py-3 font-bold text-white flex justify-between items-center fixed w-full">
        <span class="text-lg">Biblioteca - Colegio Ami  gos De Don Bosco</span>
        <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
    </div>

    <!-- Contenedor para pregunta de búsqueda y JComboBox con margen inferior -->
    <div class="flex flex-col items-center mt-20">
        <label for="tipoMaterial" class="mb-2">Escoja qué material desea buscar:</label>
        <select id="tipoMaterial" name="tipoMaterial" class="border p-2" onchange="mostrarCamposAdicionales()">
            <option value="cd">CD</option>
            <option value="dvd">DVD</option>
            <option value="libro">Libros</option>
            <option value="revista">Revistas</option>
        </select>
    </div>

    <!-- Formulario de búsqueda -->
    <div class="form-container">
        <p id="mensajeTextoBuscar" style="margin-bottom: 0;">Ingrese el texto a buscar:</p> <!-- Ajustado el margen inferior -->
        <form action="procesarFormulario.jsp" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            <br>
            <label for="autor">Autor:</label>
            <input type="text" id="autor" name="autor" required>
            <br>
            <div class="center-button">
                <button type="submit" class="rounded-md bg-blue-950 px-3 py-2 text-white">Enviar</button>
                
            </div>
        </form>
    </div>
    
    <%   
         String fnombre = request.getParameter("nombre");
        String fautor = request.getParameter("autor");
        System.out.println(fnombre);
        System.out.println(fautor);
    %>
            
    <%-- kevv >
    
</body>
</html>
