<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tiposIngrediente.TipoIngrediente"%>
<%@page import="ingredientes.Ingrediente"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    Ingrediente ingrediente = (Ingrediente) request.getAttribute("ingrediente"); 
    List<TipoIngrediente> tiposIngrediente = (List) request.getAttribute("tiposIngrediente"); 
%>

<main class="main">

    <div class="container row">

        <%@ include file="/nutricionista/actionbutton.jsp"%>

        <h3 class="center">Tipos de Ingrediente</h3>
        <section>
            <div class="container">
                <h4>Novo Tipo</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>ingredientes?action=update" method="post">
                    <input type="hidden" name="id" value="${ingrediente.id}">                  
                    <div class="input-field">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" value="${ingrediente.nome}">
                    </div>
                    <div class="input-field">
                        <select name="tipoIngrediente" class="browser-default" value="">
                            <option value="" disabled selected>Tipo de Ingrediente</option>
                            <%
                                if(tiposIngrediente.size()>0){
                                    int index;                                    
                                    for(index=0; index < tiposIngrediente.size(); index++){ 
                                        TipoIngrediente tipoIngrediente = tiposIngrediente.get(index);
                                        if (tipoIngrediente.getId() == ingrediente.getTipoIngrediente()) {
                                            out.println("<option value='" + tipoIngrediente.getId() + "' selected>" + tipoIngrediente.getNome() + "</option>");
                                        } else {
                                            out.println("<option value='" + tipoIngrediente.getId() + "'>" + tipoIngrediente.getNome() + "</option>");
                                        }
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="input-field">
                            <label for="descricao">Descricao</label>
                            <textarea name="descricao" class="materialize-textarea">${ingrediente.descricao}</textarea>
                    </div>

                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>ingredientes" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
  </main>
<%@ include file="/footer.jsp"%>
