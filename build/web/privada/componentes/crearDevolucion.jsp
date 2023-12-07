<%-- 
    Document   : crearDevolucion
    Created on : 12-06-2023, 07:37:53 PM
    Author     : Oliver
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="grid h-full grid-cols-5 grid-rows-6 px-10">
            <div class="col-span-5 row-span-1 row-start-1 row-end-1 mx-3 flex items-center justify-center">
                <h3 class="text-xl font-bold">Registro de devolución</h3>
            </div>
            <div class="col-span-3 col-start-2 row-span-1 row-start-2 row-end-2 mb-10">
                <form action="" method="post" class="flex w-full justify-center">
                    <button type="submit" class="mr-2 flex h-12 w-12 items-center justify-center rounded-lg bg-blue-300 px-2"><i class="material-icons">search</i></button>
                    <input type="text" class="w-80 rounded-lg bg-slate-200 px-2 py-3" name="" placeholder="Ingresa el código del préstamo" id="" />
                </form>
            </div>

            <div class="col-span-3 row-span-2 row-start-3 row-end-6 mx-3 flex flex-col items-center rounded-lg bg-indigo-100 p-5">
                <p class="font-bold">Productos asignados al préstamo.</p>
                <div class="col-span-5 row-start-4 row-end-6 mx-3 mt-5 w-full">
                    <div class="flex items-center bg-violet-300 py-2">
                        <div class="w-3/12 px-2">Código</div>
                        <div class="w-6/12 px-2">Titulo</div>
                        <div class="w-3/12 px-2">Tipo de producto</div>
                    </div>
                    <div class="flex items-center bg-blue-50 py-2">
                        <div class="w-3/12 px-2">LIB123456</div>
                        <div class="w-6/12 px-2">El titulo del libro</div>
                        <div class="w-3/12 px-2">Libro</div>
                    </div>
                </div>
            </div>
            <div class="col-span-2 row-span-2 row-start-3 row-end-6 mx-3 flex flex-col items-center rounded-lg bg-blue-100 p-5">
                <p class="font-bold">Detalles del préstamo</p>
                <div class="mt-7 flex flex-col w-full">
                    <div>
                        <span class="font-medium">Total de productos préstados:</span>
                        <span>-</span>
                    </div>
                    <div>
                        <span class="font-medium">Fecha de entrega acordada:</span>
                        <span>-</span>
                    </div>
                    <div>
                        <span class="font-medium">Estado del préstamo:</span>
                        <span>-</span>
                    </div>
                    <div>
                        <span class="font-medium">Mora aplicada:</span>
                        <span>-</span>
                    </div>
                </div>
            </div>
            <div class="col-span-5 row-start-6 row-end-6 flex w-full justify-center items-center">
                <a href="" class="bg-indigo-300 px-3 py-2 rounded-lg">Registrar devolución</a>
            </div>
        </div>

    </body>
</html>
