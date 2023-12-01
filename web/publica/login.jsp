<%-- 
    Document   : index
    Created on : 11-28-2023, 09:18:01 PM
    Author     : Oliver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/configuraciones.jsp"%>
    </head>
    <body>
        <div class="flex items-center justify-center w-screen h-screen">
            <div class="w-96 text-center bg-slate-100 rounded-b-lg">
                <div class="bg-slate-600 py-3 rounded-t-lg">
                    <h3 class="text-white">Inicio de sesión</h3>
                </div>
                <div class="flex flex-1 flex-col px-4 py-5 text-left">
                    <div>
                        <label for="usuario" class="block text-sm font-medium leading-6 text-gray-900">Nombre de usuario</label>
                        <div class="mt-2">
                            <input type="text" name="usuario" id="usuario" class="block w-full pl-2 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-slate-400 sm:text-sm sm:leading-6" />
                        </div>
                    </div>
                    <div class="mt-7">
                        <label for="contrasena" class="block text-sm font-medium leading-6 text-gray-900">Contraseña</label>
                        <div class="mt-2">
                            <input type="password" name="contrasena" id="contrasena" class="block w-full rounded-md border-0 py-1.5 pl-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-slate-400 sm:text-sm sm:leading-6" />
                        </div>
                    </div>
                    <div class="mt-7 flex justify-center">
                        <button id="botonInciarSesion" class="mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 bg-slate-500 text-white hover:bg-gray-50" @click="irPrivado()">Iniciar sesión</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.getElementById("botonInciarSesion").addEventListener("click", function () {
                window.location.href = "${contextPath}/privada/panelPrincipal.jsp";
            });
        </script>
    </body>
</html>
