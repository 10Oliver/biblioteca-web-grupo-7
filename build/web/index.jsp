<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/configuraciones.jsp"%>
</head>
<body class="flex flex-col h-screen bg-slate-100">
    <!-- Franja de color en la parte superior -->
    <div class="bg-blue-400 px-5 py-3 font-bold text-white flex justify-between items-center fixed w-full">
        <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
        <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
    </div>

    <!-- Campo de búsqueda y botón de filtro -->
    <div class="mt-32 mx-auto flex items-center">
        <!-- Campo de búsqueda -->
        <input type="text" class="rounded-md border-2 border-gray-300 px-4 py-2" placeholder="Buscar...">

        <!-- Botón de filtro -->
        <button class="ml-4 rounded-md bg-blue-950 px-4 py-2 text-white">Filtro</button>
    </div>

    <!-- Contenido principal -->
    <div class="flex flex-1 items-center justify-center mt-[-180px]"> <!-- Ajusté 'mt-[-100px]' para un margen superior más negativo -->
        <!-- Cuadros de Categorías en el lado izquierdo -->
        <div class="p-4">
            <a href="#" class="rounded-md bg-blue-950 px-3 py-2 mb-2 text-white block">Categoría 1</a>
            <a href="#" class="rounded-md bg-blue-950 px-3 py-2 mb-2 text-white block">Categoría 2</a>
            <a href="#" class="rounded-md bg-blue-950 px-3 py-2 mb-2 text-white block">Categoría 3</a>
            <a href="#" class="rounded-md bg-blue-950 px-3 py-2 mb-2 text-white block">Categoría 4</a>
        </div>

        <!-- Contenido en el centro -->
        <div class="flex-1 p-4 flex flex-row items-center justify-center">
            <!-- Imagen a la izquierda con margen derecho -->
            <img src="ruta_de_la_imagen.jpg" alt="Descripción de la imagen" class="rounded-full h-32 w-32 mr-4">

            <!-- Contenedor para el título y texto -->
            <div>
                <!-- Título modificado -->
                <h2 class="text-3xl font-bold mb-2 mt-0">Título</h2>

                <!-- Texto justo debajo del título -->
                <p class="ml-2 mb-4">Texto</p>
            </div>
        </div>
    </div>
</body>
</html>
