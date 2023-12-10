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

            <div class="row-span-2 row-start-3 row-end-7 mx-3 flex flex-col items-center rounded-lg bg-indigo-100 p-5">
                <p>Para registrar un nuevo producto, has click aquí.</p>
                <div class="mt-7">
                    <a id="registrarBoton" class="rounded-lg bg-violet-400 px-5 py-3">Registrar producto</a>
                </div>
            </div>
            <div class="row-span-2 row-start-3 row-end-7 mx-3 flex flex-col items-center rounded-lg bg-blue-100 p-5">
                <p>Para buscar un producto ya existente, haz click aquí.</p>
                <div class="mt-7">
                    <a href="${contextPath}/buscar.do" class="rounded-lg bg-violet-400 px-5 py-3">Buscar producto</a>
                </div>
            </div>
        </div>

        <div id="seleccionarTipoProducto" class="fixed inset-0 z-10 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
            <div class="flex min-h-screen items-center justify-center">
                <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                <!-- Ajustamos el tamaño de este div -->
                <div class="h-auto w-4/6 transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:align-middle">
                    <div class="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                        <h3 class="text-lg font-medium leading-6 text-gray-900">Selección del tipo de producto</h3>
                        <div class="mt-5">
                            <span>Elige el tipo de producto que deseas registrar.</span>
                            <div class="my-3">
                                <div class="flex">
                                    <input type="radio" name="producto" id="libro" class="mr-2" value="libro"/>
                                    <label for="libro">Libro</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="e-book" class="mr-2" value="e-book" />
                                    <label for="e-book">E-book</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="periodico" class="mr-2" value="periodico" />
                                    <label for="periodico">Periodico</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="tesis" class="mr-2" value="tesis"/>
                                    <label for="tesis">Tesis</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="pelicula" class="mr-2" value="pelicula"/>
                                    <label for="pelicula">Pelicula</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="cd" class="mr-2" value="cd"/>
                                    <label for="cd">CD</label>
                                </div>
                                <div class="flex">
                                    <input type="radio" name="producto" id="revista" class="mr-2" value="revista"/>
                                    <label for="revista">Revista</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                        <button type="button" id="cerrarModal" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Cerrar</button>
                        <a type="submit" onClick="enviarVista()" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Empezar registro del producto</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    const modal = document.getElementById("seleccionarTipoProducto");
    const abrirModal = document.getElementById("registrarBoton");
    const agregarCerrarBoton = document.getElementById("cerrarModal");

    abrirModal.onclick = function () {
        modal.style.display = "block";
    }

    agregarCerrarBoton.onclick = function () {
        modal.style.display = "none";
    }

    const enviarVista = () => {
        let vistaSeleccionada;
        const radioButtons = document.getElementsByName("producto");

        Object.values(radioButtons).forEach((radio) => {
            if (radio.checked) {
                vistaSeleccionada = radio.value;
                location.href = "${contextPath}/producto.do?tipo=" + vistaSeleccionada;
            }
        })
    }
</script>