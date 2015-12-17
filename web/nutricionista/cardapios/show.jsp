<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cardapios.Cardapio"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    Cardapio cardapio = (Cardapio) request.getAttribute("cardapio"); 
%>
<main>
    <div class="container row">

    <%@ include file="/nutricionista/actionbutton.jsp"%>

    <h3 class="center">Cardapio</h3>
    <section>
        
        <div class="container">
            <div class="input-field" >
                <%      
                    String htmlBody ="<h5>Cardapio do dia: " + cardapio.getDataFormatada() + "</h5>";
                    out.println(htmlBody);
                %>
            </div>
            <form class="cadast" action="index.html" method="post">
                <input type="hidden" name="id" value="${cardapio.id}">
                <input type="hidden" name="tipoRefeicao" value="1">
                
                <div class="group">
                  <p>Almoço</p>
                  <ul class="ingredientes">
                      <li>${ingrediente}</li>
                  </ul>
                  <button class="btn blue right" name="action">Editar
                          <i class="material-icons right">edit</i>
                  </button>
                </div>
              
            </form>
            
            <form class="cadast" action="index.html" method="post">
                <input type="hidden" name="id" value="${cardapio.id}">
                <input type="hidden" name="tipoRefeicao" value="2">
                
                <div class="group">
                    <p>Janta</p>
                    <ul class="ingredientes">
                        <li>${ingrediente}</li>
                    </ul>
                    <button class="btn blue right" name="action">Editar
                        <i class="material-icons right">edit</i>
                    </button>
                </div>     

            </form>
                  
        <a href="<%=url%>cardapios" class="waves-effect waves-teal btn-flat right">Cancelar</a>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>

