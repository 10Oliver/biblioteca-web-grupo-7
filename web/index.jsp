<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/configuraciones.jsp"%>
    <script>
        function mostrarCamposAdicionales() {
            var tipoMaterial = document.getElementById("tipoMaterial");
            var contenedorLibro = document.getElementById("contenedorLibro");

            if (tipoMaterial.value === "libro") {
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
        <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
        <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
    </div>

    <!-- Contenedor para pregunta de búsqueda y JComboBox con margen inferior -->
    <div class="flex flex-col items-center mt-20">
        <label for="tipoMaterial" class="mb-2">Escoja que material desea buscar:</label>
        <select id="tipoMaterial" name="tipoMaterial" class="border p-2" onchange="mostrarCamposAdicionales()">
            <option value="cd">CD</option>
            <option value="dvd">DVD</option>
            <option value="libro">Libro</option>
            <option value="revista">Revista</option>
        </select>
    </div>

     <!--Crea el textfield para que el usuario escriba: -->
    
     <h1> Ingrese le texto a buscar</h1>
         <form action="procesarFormulario.jsp" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br>
        <label for="nombre">Autor:</label>
        <input type="text" id="autor" name="autor" required>
        <br>
        <button type="submit" > Enviar   </button>
    </form>
     
    
    <%-- Obtener el valor del campo de texto enviado por el formulario 
    
    <% String nombre = request.getParameter("nombre"); %>

    <h2>¡Hola, <%= nombre %>!</h2>
        --%>
    
    <!-- Contenedor para campos adicionales de Libro 
    <div id="contenedorLibro" style="display: none;" class="flex flex-col items-center mt-4">
        <label for="nombreLibro" class="mb-2">Nombre del libro:</label>
        <input type="text" id="nombreLibro" name="nombreLibro" class="border p-2 mb-2" />

        <label for="autorLibro" class="mb-2">Autor del libro:</label>
        <input type="text" id="autorLibro" name="autorLibros" class="border p-2" />
    </div>
-->
</body>
</html>
