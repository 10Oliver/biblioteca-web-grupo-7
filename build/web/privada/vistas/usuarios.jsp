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
                <div class="my-10 flex justify-end">
                    <a id="btnAgregar" class="mr-20 rounded-md bg-blue-400 px-3 py-1">Agregar</a>
                </div>
                <div class="mx-10">
                    <!-- begin: Empieza cabezado de la tabla-->
                    <div class="flex items-center bg-violet-300 py-2">
                        <div class="w-2/12 px-2">Nombre de usuario</div>
                        <div class="w-3/12 px-2">Correo electrónico</div>
                        <div class="w-2/12 px-2">Fecha de nacimiento</div>
                        <div class="w-2/12 px-2">Teléfono</div>
                        <div class="w-2/12 px-2">Rol</div>
                        <div class="w-2/12 px-2">Acciones</div>
                    </div>
                    <!-- end: Termina cabezado de la tabla-->
                    <!-- begin: Empieza contenido de la tabla-->
                    <div class="flex items-center bg-blue-200 py-2">
                        <div class="w-2/12 px-2">oliver</div>
                        <div class="w-3/12 px-2">oliver_erazo@oliver.com</div>
                        <div class="w-2/12 px-2">2023-10-10</div>
                        <div class="w-2/12 px-2">74854152</div>
                        <div class="w-2/12 px-2">Administrador</div>
                        <div class="w-2/12 px-2">
                            <a href="" class="inline-block rounded-md bg-purple-400 px-5"
                               ><span>Restablecer</span><br />
                                <span>contraseña</span></a
                            >
                        </div>
                    </div>
                    <div class="flex items-center bg-blue-50 py-2">
                        <div class="w-2/12 px-2">oliver</div>
                        <div class="w-3/12 px-2">oliver_erazo@oliver.com</div>
                        <div class="w-2/12 px-2">2023-10-10</div>
                        <div class="w-2/12 px-2">74854152</div>
                        <div class="w-2/12 px-2">Administrador</div>
                        <div class="w-2/12 px-2">
                            <a href="" class="inline-block rounded-md bg-purple-400 px-5"
                               ><span>Restablecer</span><br />
                                <span>contraseña</span></a
                            >
                        </div>
                    </div>
                    <div class="flex items-center bg-blue-200 py-2">
                        <div class="w-2/12 px-2">oliver</div>
                        <div class="w-3/12 px-2">oliver_erazo@oliver.com</div>
                        <div class="w-2/12 px-2">2023-10-10</div>
                        <div class="w-2/12 px-2">74854152</div>
                        <div class="w-2/12 px-2">Administrador</div>
                        <div class="w-2/12 px-2">
                            <a href="" class="inline-block rounded-md bg-purple-400 px-5"
                               ><span>Restablecer</span><br />
                                <span>contraseña</span></a
                            >
                        </div>
                    </div>
                    <div class="flex items-center bg-blue-50 py-2">
                        <div class="w-2/12 px-2">oliver</div>
                        <div class="w-3/12 px-2">oliver_erazo@oliver.com</div>
                        <div class="w-2/12 px-2">2023-10-10</div>
                        <div class="w-2/12 px-2">74854152</div>
                        <div class="w-2/12 px-2">Administrador</div>
                        <div class="w-2/12 px-2">
                            <a href="" class="inline-block rounded-md bg-purple-400 px-5"
                               ><span>Restablecer</span><br />
                                <span>contraseña</span></a
                            >
                        </div>
                    </div>
                    <!-- end_ Terminar contenido de la tabla-->
                </div>
            </div>
        </div>

        <div id="agregarUsuarios" class="fixed inset-0 z-10 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
            <div class="flex min-h-screen items-center justify-center">
                <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                <!-- Ajustamos el tamaño de este div -->
                <div class="h-auto w-4/6 transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:align-middle">
                    <div class="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                        <h3 class="text-lg font-medium leading-6 text-gray-900">Agregar un nuevo usuario</h3>
                        <div class="mt-8 flex flex-wrap">
                            <div class="my-1 w-1/2 px-3 py-1">
                                <input type="text" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe el nombre de usuario..." />
                            </div>
                            <div class="my-1 w-1/2 px-3 py-1">
                                <input type="email" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe el correo del usuario..." />
                            </div>
                            <div class="my-1 w-1/2 px-3 py-1">
                                <input type="date" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe la fecha de nacimiento..." />
                            </div>
                            <div class="my-1 w-1/2 px-3 py-1">
                                <input type="tel" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe el teléfono..." />
                            </div>
                            <div class="my-1 w-1/2 px-3 py-1">
                                <select name="" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" id="">
                                    <option value="" selected disabled>Seleccione el rol</option>
                                    <option value="">Administrador</option>
                                    <option value="">Maestro</option>
                                    <option value="">Estudiante</option>
                                </select>
                            </div>
                            <div class="my-1 w-1/2 px-3 py-1">
                                <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe la contraseña..." />
                            </div>
                            <div class="my-1 w-1/2  px-3 py-1p-1">
                                <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Confirma la contraseña..." />
                            </div>
                        </div>
                    </div>
                    <div class="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                        <button type="button" id="cerrarModal" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

<script>
    const modal = document.getElementById("agregarUsuarios");
    const agregarBoton = document.getElementById("btnAgregar");
    const cerrarBoton = document.getElementById("cerrarModal");

    agregarBoton.onclick = function () {
        modal.style.display = "block";
    }

    cerrarBoton.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>