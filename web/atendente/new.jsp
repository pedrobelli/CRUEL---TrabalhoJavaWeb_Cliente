<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entradas.Entrada"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    Entrada entrada = (Entrada) request.getAttribute("entrada"); 
%>

<main class="main">

    <div class="container row">

        <%@ include file="actionbutton.jsp"%>

        <h3 class="center">Entrada</h3>
        <section>
            <div class="container">
                <h4>Nova Entrada</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>entradas?action=create" method="post">
                                          
                    <div class="input-field">
                        <select class="browser-default" name="tipoCliente" value="">
                                <option value="" disabled selected>Tipo de Cliente</option>
                                <option value="1">Funcionario</option>
                                <option value="2">Aluno</option>
                                <option value="3">Externo</option>
                        </select>
                        </div>
                     <div class="input-field">
                            <label for="valorRefeicao">Valor:</label>
                            <input type="text" name="valorRefeicao" value="${entrada.valorRefeicao}">
                    </div>

                    <div class="input-field">
                        <input type="date" name="data" value="${entrada.data}">
                    </div>

                    
                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>entradas" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
  </main>
<%@ include file="/footer.jsp"%>
