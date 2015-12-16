<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cardapios.CardapioDecorator"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    CardapioDecorator cardapio = (CardapioDecorator) request.getAttribute("cardapio"); 
%>

<main>
    <div class="container row">

    <%@ include file="/nutricionista/actionbutton.jsp"%>

    <h3 class="center">Cardapio</h3>
    <section>
        <div class="container">
            <h4>Novo Cardapio</h4>
            <%@ include file="/errors.jsp"%>
            <form class="cadast" action="<%=url%>cardapios?action=create" method="post">
                
                <label for="nome">Data do Cardápio:</label>
                <div class="input-field" >
                    <input type="date" name="data" class="datepicker" value="${cardapio.data}">
                </div>

              <button class="btn blue right" type="submit" name="action">Ok
               <i class="material-icons right">send</i>
             </button>
                <a href="<%=url%>cardapios" class="waves-effect waves-teal btn-flat right">Cancelar</a>
          </form>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>
