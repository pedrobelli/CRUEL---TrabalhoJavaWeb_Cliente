<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiposIngrediente.TipoIngrediente"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    TipoIngrediente tipoIngrediente = (TipoIngrediente) request.getAttribute("tipoIngrediente"); 
%>

<main class="main">

    <div class="container row">

        <%@ include file="/nutricionista/actionbutton.jsp"%>

        <h3 class="center">Tipos de Ingrediente</h3>
        <section>
            <div class="container">
                <h4>Editar Tipo</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>tiposIngrediente?action=update" method="post">
                    <input type="hidden" name="id" value="${tipoIngrediente.id}">
                      
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" value="${tipoIngrediente.nome}">
                    </div>
                                        
                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>tiposIngrediente" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
  </main>
<%@ include file="/footer.jsp"%>
