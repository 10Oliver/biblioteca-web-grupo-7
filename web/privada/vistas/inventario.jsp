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
        <div class="bg-slate-200 h-full flex-col items-center flex">
            <div class="bg-red-200 my-5">
                <button class="mt-3 justify-center rounded-md border border-transparent bg-violet-600 px-4 py-3 text-base font-medium text-white shadow-sm hover:bg-violet-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Agregar nuevos productos</button>
            </div>
            <div class="bg-red-200 my-5">
                <button class="mt-3 justify-center rounded-md border border-transparent bg-violet-600 px-4 py-3 text-base font-medium text-white shadow-sm hover:bg-violet-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Buscar productos existentes</button>
            </div>
        </div>
    </body>
</html>
