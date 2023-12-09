<%-- 
    Document   : configuraciones
    Created on : 11-30-2023, 09:28:06 PM
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
        <form action="" method="post" class="h-full py-5">
            <div class="grid h-full grid-cols-2 grid-rows-4 px-10">
                <div class="col-span-2 row-start-1 row-end-2 mx-3">
                    <div class="rounded-xl bg-white px-5 py-8">
                        <div class="mb-5">
                            <h3 class="text-xl font-bold">Gestión de parámetros de préstamos</h3>
                        </div>
                        <div>
                            <p>Aquí podrás modificar los parámetros que se utilizarán para que los usuarios puedan solicitar un préstamo.</p>
                        </div>
                    </div>
                </div>
                <div class="col-span-2 row-start-2 row-end-3 m-4 flex flex-col items-center justify-center rounded-lg bg-emerald-100 px-5">
                    <p class="mb-2">Seleccionar el rol al que deseas modificar.</p>
                    <select name="" id="" class="w-60 rounded-lg px-3 py-2">
                        <option value="">Administrador</option>
                        <option value="">Profesor</option>
                        <option value="">Estudiante</option>
                    </select>
                </div>

                <div class="row-start-3 row-end-4 mx-3 flex flex-col items-center rounded-lg bg-indigo-100 p-5">
                    <p>Limite de productos por préstamo.</p>
                    <input type="number" name="" id="" class="mt-4 rounded-lg px-3 py-2" value="0" />
                </div>
                <div class="row-start-3 row-end-4 mx-3 flex flex-col items-center rounded-lg bg-blue-100 p-5">
                    <p>Mora a aplicar para los préstamos retrasados.</p>
                    <input type="number" name="" id="" class="mt-4 rounded-lg px-3 py-2" value="0" />
                </div>
                <div class="col-span-2 row-start-4 mx-3 mt-4 flex items-center justify-center rounded-lg bg-red-100">
                    <button type="submit" class="rounded-lg bg-violet-400 px-3 py-2">Guardar configuración</button>
                </div>
            </div>
        </form>
    </body>
</html>
