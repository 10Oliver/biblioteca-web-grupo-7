<%-- 
    Document   : usuarios
    Created on : 11-30-2023, 09:24:56 PM
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
        <div>
            <div class="bg-green-200 py-2 text-center">
                <p>Usuarios</p>
            </div>
            <div>
                <div class="mt-10 flex justify-end">
                    <a href="" class="mr-20 bg-blue-400 px-3 py-1 rounded-md">Agregar</a>
                </div>
                <div class="mt-5 px-5">
                    <div class="bg-violet-300 grid grid-cols-12 gap-4 py-2">
                        <div class="col-span-3">Nombre de usuario</div>
                        <div class="col-span-3">Correo electrónico</div>
                        <div class="col-span-2">Fecha de nacimiento</div>
                        <div class="col-span-1">Teléfono</div>
                        <div class="col-span-2">Rol</div>
                        <div class="col-span-1">Acciones</div>
                    </div>
                    <div class="bg-violet-100 grid grid-cols-12 gap-4 py-1">
                        <div class="col-span-3">usuario_ejemplo</div>
                        <div class="col-span-3">correo@correo.com</div>
                        <div class="col-span-2">2023-10-10</div>
                        <div class="col-span-1">74531839</div>
                        <div class="col-span-2">Administrador</div>
                        <div class="col-span-1">Editar</div>
                    </div>
                    <div class="bg-violet-0 grid grid-cols-12 gap-4 py-1">
                        <div class="col-span-3">usuario_ejemplo</div>
                        <div class="col-span-3">correo@correo.com</div>
                        <div class="col-span-2">2023-10-10</div>
                        <div class="col-span-1">74531839</div>
                        <div class="col-span-2">Administrador</div>
                        <div class="col-span-1">Editar</div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
