<%-- 
    Document   : paginaPrincipal
    Created on : 11-29-2023, 06:39:34 PM
    Author     : Oliver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca - Colegio Amigos De Don Bosco</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>
        <div class="flex h-screen flex-col bg-slate-100">
            <div class="flex flex-row items-center justify-between bg-blue-400 px-5 py-3 font-bold text-white">
                <span class="text-lg">Biblioteca - Colegio Amigos De Don Bosco</span>
                <a href="publica/login.jsp" class="rounded-md bg-blue-950 px-3 py-2">Iniciar sesión</a>
            </div>
            <div class="grid flex-1 grid-cols-12 bg-green-100">
                <div class="col-span-3 col-start-1 bg-white">Categorias</div>
                <div class="col-span-9 col-start-4 bg-purple-100">Contenido</div>
            </div>
        </div>
    </body>
</html>
