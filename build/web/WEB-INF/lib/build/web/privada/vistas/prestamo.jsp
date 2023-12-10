<%-- 
    Document   : prestamo
    Created on : 11-30-2023, 09:27:38 PM
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
                    <div class="mb-5"><h3 class="text-xl font-bold">Gestión de prestamos y devoluciones</h3></div>
                    <div>
                        <p>En este apartado, podrás crear nuevos préstamos para los maestros y estudiante, o bien, registrar las devoluciones cuando regresen los productos prestados.</p>
                    </div>
                </div>
            </div>

            <div class="row-span-2 row-start-3 row-end-7 mx-3 flex flex-col items-center rounded-lg bg-indigo-100 p-5">
                <p>Para crear un nuevo préstamos, haz click aquí.</p>
                <div class="mt-7">
                    <a href="${contextPath}/prestamo.do" class="rounded-lg bg-violet-400 px-5 py-3">Crear préstamo</a>
                </div>
            </div>
            <div class="row-span-2 row-start-3 row-end-7 mx-3 flex flex-col items-center rounded-lg bg-blue-100 p-5">
                <p>Para registrar la devolución de los productos, haz click aquí.</p>
                <div class="mt-7">
                    <a href="${contextPath}/devolucion.do" class="rounded-lg bg-violet-400 px-5 py-3">Registrar devolución</a>
                </div>
            </div>
        </div>
    </body>
</html>
