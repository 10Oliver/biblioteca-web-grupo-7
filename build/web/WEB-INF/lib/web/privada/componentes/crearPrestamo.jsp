<%-- 
    Document   : crearPrestamo
    Created on : 12-06-2023, 07:37:29 PM
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
        <div class="grid grid-cols-5 grid-rows-10 p-10">
            <div class="row-span-1 col-span-6 row-start- mb-7 mx-3 text-center">
                <h6 class="font-semibold text-xl">Creación de préstamos</h6>
            </div>
            <div class="col-span-2 row-span-2 row-start-2 row-end-4 mx-3 flex flex-col items-start rounded-lg bg-indigo-100 px-4 py-5">
                <p>Registro del usuario y fecha de devolución.</p>
                <div class="my-3 w-full">
                    <form action="" class="flex" method="post">
                        <input type="text" class="w-full rounded-lg px-2 py-2" name="nombreUsuario" id="nombreUsuario" placeholder="Escribe el nombre de usuario..." />
                        <!--<button type="submit" class="ml-2 rounded-lg bg-indigo-500 px-3"><i class="material-icons text-white">search</i></button>-->
                        <!-- Temporal button-->
                        <button id="buscarUsuario" class="ml-2 rounded-lg bg-indigo-500 px-3"><i class="material-icons text-white">search</i></button>
                    </form>
                    <div class="mt-3 flex items-center">
                        <span class="mr-2">Usuario seleccionado:</span>
                        <span id="usuarioSeleccionado" class="flex items-center rounded-lg bg-indigo-800 px-3 py-1 text-white"
                              ><span id="textoChip">Mi usuario</span>
                            <button id="quitarUsuario"><i class="material-icons ml-2 mt-1">close</i></button>
                        </span>
                    </div>
                </div>
                <div class="mt-3 flex w-full flex-col">
                    <span class="mb-2">Selecciona la fecha de devolución</span>
                    <input type="date" name="" id="" class="w-full rounded-lg px-2 py-2" />
                </div>
            </div>
            <div class="col-span-3 row-span-2 row-start-2 row-end-4 mx-3 flex flex-col items-start rounded-lg bg-blue-100 px-4 py-5">
                <p>Selección de productos a incluir en el préstamo.</p>
                <div class="flex w-full my-3">
                    <input type="text" name="" id="" class="mr-3 w-3/5 rounded-lg px-2 py-2" placeholder="Escribe el titulo del producto..." />
                    <select name="" id="" class="w-2/5 rounded-lg px-2 py-2">
                        <option value="">Pelicula</option>
                    </select>
                </div>
                <div class="mt-3">
                    <button type="submit" class="rounded-lg bg-indigo-500 p-3 text-white">Buscar producto</button>
                </div>
            </div>
            <div class="col-span-5 row-start-4 row-end-6 mx-3 mt-5">
                <div class="flex items-center bg-violet-300 py-2">
                    <div class="w-3/12 px-2">Código del producto</div>
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
    </body>
</html>

<script>
    let usuarioSeleccionado = "Mi usuario";
    
    const btnBuscarUsuario = document.getElementById("buscarUsuario");
    const chipUsuario = document.getElementById("usuarioSeleccionado");
    const btnQuitarUsuario = document.getElementById("quitarUsuario");
    const spanUsuario = document.getElementById("textoChip");
    
    btnBuscarUsuario.onclick = () => {
        chipUsuario.style.display = "block";
        spanUsuario.innerHTML = usuarioSeleccionado;
    }
    
    btnQuitarUsuario.onclick = () => {
        chipUsuario.style.display = "none";
        spanUsuario.innerHTML = "";
    }
</script>
