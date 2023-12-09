<%-- 
    Document   : usuarios
    Created on : 11-30-2023, 09:24:56 PM
    Author     : Oliver
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%
    List<Map<String, String>> listaUsuarios = (List<Map<String, String>>) request.getAttribute("usuarios");
    Integer color = 0;
%>

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
                    <% for (Map<String, String> infoUsuario : listaUsuarios) {%>
                    <% if (color % 2 == 0) {%>
                    <div class="flex items-center bg-blue-200 py-2">
                        <%} else {%>
                        <div class="flex items-center bg-blue-50 py-2">
                            <%}%>

                            <div class="w-2/12 px-2"><%=infoUsuario.get("usuario")%></div>
                            <div class="w-3/12 px-2"><%=infoUsuario.get("correo")%></div>
                            <div class="w-2/12 px-2"><%=infoUsuario.get("fecha")%></div>
                            <div class="w-2/12 px-2"><%=infoUsuario.get("telefono")%></div>
                            <div class="w-2/12 px-2"><%=infoUsuario.get("rol")%></div>
                            <div class="w-2/12 px-2">
                                <a onClick="abrirRestableceModal('<%=infoUsuario.get("id")%>')" class="inline-block rounded-md bg-purple-400 px-5"
                                   ><span>Restablecer</span><br />
                                    <span>contraseña</span></a
                                >
                            </div>
                        </div>

                        <%
                                color++;
                            }
                        %>
                        <!-- end_ Terminar contenido de la tabla-->
                    </div>
                </div>
            </div>

            <!-- Modal para agregar un nuevo usuario -->
            <div id="agregarUsuarios" class="fixed inset-0 z-10 hidden overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div class="flex min-h-screen items-center justify-center">
                    <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                    <!-- Ajustamos el tamaño de este div -->
                    <div class="h-auto w-4/6 transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:align-middle">
                        <form action="${contextPath}/UserController?action=register" method="post">
                            <div class="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                                <h3 class="text-lg font-medium leading-6 text-gray-900">Agregar un nuevo usuario</h3>
                                <div class="mt-8 flex flex-wrap">
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="text" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="usuario" id="usuario" placeholder="Escribe el nombre de usuario..." />
                                    </div>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="email" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="correo" id="correo" placeholder="Escribe el correo del usuario..." />
                                    </div>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="date" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe la fecha de nacimiento..." />
                                    </div>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="tel" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="telefono" id="telefono" placeholder="Escribe el teléfono..." />
                                    </div>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <select name="rol" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" id="rol">
                                            <option value="" selected disabled>Seleccione el rol</option>
                                            <option value="">Administrador</option>
                                            <option value="">Maestro</option>
                                            <option value="">Estudiante</option>
                                        </select>
                                    </div>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Escribe la contraseña..." />
                                    </div>
                                    <div class="py-1p-1 my-1 w-1/2 px-3">
                                        <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="" id="" placeholder="Confirma la contraseña..." />
                                    </div>
                                </div>
                            </div>
                            <div class="px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                                <button type="button" id="cerrarModal" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Cerrar</button>
                                <button type="submit" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Agregar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal para restabler la contraseña-->
            <div id="restablecerModal" class="fixed inset-0 z-10 hidden overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div class="flex min-h-screen items-center justify-center">
                    <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                    <div class="h-auto w-4/6 transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:align-middle">
                        <div class="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                            <h3 class="text-lg font-medium leading-6 text-gray-900">Restablecer contraseña</h3>
                            <form action="${contextPath}/UserController?action=changePassword" method="post">
                                <div class="mb-8 mt-8 flex flex-wrap">
                                    <!-- Input para colocar el ID y enviarlo en el post-->
                                    <input type="text" id="userId" name="userId" class="hidden"/>
                                    <div class="my-1 w-1/2 px-3 py-1">
                                        <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" name="newPassword" id="newPassword" placeholder="Escribe la nueva contraseña..." />
                                    </div>
                                    <div class="py-1p-1 my-1 w-1/2 px-3">
                                        <input type="password" class="m-0 w-full rounded-lg bg-slate-200 px-2 py-2" id="confirmPassword" placeholder="Confirma la nueva contraseña..." />
                                    </div>
                                </div>
                                <div class="px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                                    <button type="button" id="cerrarRestablecer" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Cerrar</button>
                                    <button type="submit" class="mt-3 inline-flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-base font-medium text-white shadow-sm hover:bg-blue-700 focus:outline-none sm:ml-3 sm:mt-0 sm:w-auto sm:text-sm">Restablecer</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>

<script>
    const agregarModal = document.getElementById("agregarUsuarios");
    const agregarAbrirBoton = document.getElementById("btnAgregar");
    const agregarCerrarBoton = document.getElementById("cerrarModal");
    const idUsuarioSelecionado = document.getElementById("userId");

    const restablecerModal = document.getElementById("restablecerModal");
    const restablecerCerrarBoton = document.getElementById("cerrarRestablecer");

    agregarAbrirBoton.onclick = function () {
        agregarModal.style.display = "block";
    }

    agregarCerrarBoton.onclick = function () {
        agregarModal.style.display = "none";
    }

    const abrirRestableceModal = (idUsuario) => {
        restablecerModal.style.display = "block";
        idUsuarioSeleccionado.value = idUsuario;
    }

    restablecerCerrarBoton.onclick = () => {
        restablecerModal.style.display = "none";
        idUsuarioSeleccionado.value = "";
    }
</script>