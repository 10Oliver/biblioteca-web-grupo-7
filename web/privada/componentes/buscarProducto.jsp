<%-- 
    Document   : buscarProducto
    Created on : 12-05-2023, 08:47:06 PM
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
        <div class="my-5">
  <div class="mt-10 flex w-full justify-center">
    <input type="text" class="w-80 rounded-lg bg-slate-200 px-2 py-3" name="" placeholder="Ingresa el texto a buscar" id="" />
    <select class="ml-3 rounded-lg bg-slate-400 px-3" name="" id="">
      <option value="">Titulo del producto</option>
      <option value="">Código del producto</option>
    </select>
  </div>
  <div class="mt-10">
    <div class="mx-10">
      <!-- begin: Empieza cabezado de la tabla-->
      <div class="flex items-center bg-violet-300 py-2">
        <div class="w-3/12 px-2">Código del producto</div>
        <div class="w-6/12 px-2">Titulo</div>
        <div class="w-3/12 px-2">Estante</div>
      </div>
      <div class="flex items-center bg-blue-50 py-2">
        <div class="w-3/12 px-2">LIB123456</div>
        <div class="w-6/12 px-2">El titulo del libro</div>
        <div class="w-3/12 px-2">Estante de literatura</div>
      </div>
    </div>
  </div>
</div>

    </body>
</html>
