<%-- 
    Document   : inventario
    Created on : 11-30-2023, 09:26:28 PM
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
        <div class="grid h-full grid-cols-2 grid-rows-6 p-10">
            <div class="col-span-2 row-start-1 row-end-3 mx-3">
                <div class="rounded-xl bg-white px-5 py-8">
                    <div class="mb-5"><h3 class="text-xl font-bold">Gestión de inventario</h3></div>
                    <div>
                        <p>En este apartado, podrás gestionar los productos que la biblioteca posee, tanto para agregar nuevos productos fisicos como libros, así como digitales como peliculas, además de facilitar a los clientes la búsqueda de estos.</p>
                    </div>
                </div>
            </div>

            <div class="row-span-2 row-start-3 row-end-7 bg-indigo-100 rounded-lg p-5 flex flex-col items-center mx-3">
                <p>Para registrar un nuevo producto, has click aquí.</p>
                <div class="mt-7">
                    <a class="rounded-lg bg-violet-400 px-5 py-3 ">Registrar producto</a>
                </div>
            </div>
            <div class="row-span-2 row-start-3 row-end-7 bg-blue-100 rounded-lg p-5 flex flex-col items-center mx-3">
                <p>Para buscar un producto ya existente, haz click aquí.</p>
                <div class="mt-7">
                    <a href="${contextPath}/registarProducto.do" class="rounded-lg bg-violet-400 px-5 py-3">Buscar producto</a>
                </div>
            </div>
        </div>

    </body>
</html>
