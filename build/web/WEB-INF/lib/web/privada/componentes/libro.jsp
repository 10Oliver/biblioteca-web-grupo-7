<%-- 
    Document   : libro
    Created on : 12-08-2023, 08:45:20 PM
    Author     : Oliver
--%>

<%@page import="java.util.List"%>
<%@page import="Recursos.Models.Utils.*"%>
<%
    List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");
    List<Editorial> listaEditoriales = (List<Editorial>) request.getAttribute("listaEditoriales");
    List<Genero> listaGeneros = (List<Genero>) request.getAttribute("listaGeneros");
    List<Idioma> listaIdiomas = (List<Idioma>) request.getAttribute("listaIdiomas");
    List<Estante> listaEstantes = (List<Estante>) request.getAttribute("listaEstantes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="" id='libroForm' method="post">
            <div class="mt-2 grid grid-cols-2 grid-rows-6 px-5">
                <div class="col-start-1 row-start-1 m-2 flex flex-col">
                    <label for="titulo">Titulo</label>
                    <input type="text" name="titulo" id="titulo" class="bg-slate-200 py-2 px-3 rounded-lg"  placeholder="Escribe el titulo..." />
                </div>
                <div class="col-start-2 row-start-1 m-2 flex flex-col">
                    <label for="autor">Autor</label>
                    <select name="autor" id="autor" class="bg-slate-200 py-2 px-3 rounded-lg">
                        <option value="" disabled selected>Selecionar</option>
                        <%for (Autor item: listaAutores) {%>
                        <option value="<%=item.getId()%>"><%=item.getAutor()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-start-1 row-start-2 m-2 flex flex-col">
                    <label for="editorial">Editorial</label>
                    <select name="editorial" id="editorial" class="bg-slate-200 py-2 px-3 rounded-lg">
                        <option value="" disabled selected>Selecionar</option>
                        <% for (Editorial item: listaEditoriales) {%>
                        <option value="<%=item.getId()%>"><%=item.getEditorial()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-start-2 row-start-2 m-2 flex flex-col">
                    <label for="numeroPaginas">Número de páginas</label>
                    <input type="number" name="numeroPaginas" id="numeroPaginas" class="bg-slate-200 py-2 px-3 rounded-lg"  value="0" />
                </div>
                <div class="col-start-1 row-start-3 m-2 flex flex-col">
                    <label for="isb">ISBN</label>
                    <input type="text" name="isbn" id="isbn" class="bg-slate-200 py-2 px-3 rounded-lg" placeholder="Escribe el ISBN..." />
                </div>
                <div class="col-start-2 row-start-3 m-2 flex flex-col">
                    <label for="edicion">Edición</label>
                    <input type="text" name="edicion" id="edicion" class="bg-slate-200 py-2 px-3 rounded-lg" placeholder="Escribe la edición..." />
                </div>
                <div class="col-start-1 row-start-4 m-2 flex flex-col">
                    <label for="lugarPublicacion">Lugar de publicación</label>
                    <input type="text" name="lugarPublicacion" id="lugarPublicacion" class="bg-slate-200 py-2 px-3 rounded-lg" placeholder="Escribe el lugar de la publicación..." />
                </div>
                <div class="col-start-2 row-start-4 m-2 flex flex-col">
                    <label for="fechaPublicacion">Fecha de publicación</label>
                    <input type="date" name="fechaPublicacion" id="fechaPublicacion" class="bg-slate-200 py-2 px-3 rounded-lg"/>
                </div>
                <div class="col-start-1 row-start-5 m-2 flex flex-col">
                    <label for="genero">Genero</label>
                    <select name="genero" id="genero" class="bg-slate-200 py-2 px-3 rounded-lg">
                        <option value="" disabled selected>Selecionar</option>
                        <% for (Genero item: listaGeneros) {%>
                        <option value="<%=item.getId()%>"><%=item.getGenero()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-start-2 row-start-5 m-2 flex flex-col">
                    <label for="idioma">Idioma</label>
                    <select name="idioma" id="idioma" class="bg-slate-200 py-2 px-3 rounded-lg">
                        <option value="" disabled selected>Selecionar</option>
                        <% for (Idioma item: listaIdiomas) {%>
                        <option value="<%=item.getId()%>"><%=item.getIdioma()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-start-1 row-start-6 m-2 flex flex-col">
                    <label for="notas">Notas</label>
                    <input type="text" name="notas" id="notas" class="bg-slate-200 py-2 px-3 rounded-lg" placeholder="Escribe las notas..."/>
                </div>
                <div class="col-start-2 row-start-6 m-2 flex flex-col">
                    <label for="stock">Stock</label>
                    <input type="number" name="stock" id="stock" class="bg-slate-200 py-2 px-3 rounded-lg" value="0"/>
                </div>
                <div class="col-start-1 row-start-7 m-2 flex flex-col">
                    <label for="nombreEstante">Estante</label>
                    <select name="nombreEstante" id="nombreEstante" class="bg-slate-200 py-2 px-3 rounded-lg">
                        <option value="" disabled selected>Selecionar</option>
                        <% for (Estante item: listaEstantes) {%>
                        <option value="<%=item.getId()%>"><%=item.getNombreEstante()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-span-2 row-start-8 m-2 py-3 flex flex-col justify-center items-center">
                    <a onClick='guardarLibro()' class="py-2 px-2 bg-indigo-300 w-52 rounded-lg">Crear libro</a>
                </div>
            </div>
        </form>
    </body>
</html>

<script>
    
    const guardarLibro = async (e) => {
        const datos = new FormData(document.getElementById("libroForm"));
        const urlParams = new URLSearchParams(datos);
        const response = await fetch('${contextPath}/LibroController?' + urlParams, {
            method: 'POST'
        })
        alert("El libro se ha registrado con éxito")
    }
</script>