<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>
<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
    <section>
        <div class="container">
             <form class="cadast" action="index.html" method="post">
              <div class="input-field" >
                  <label for="cardapio-data">Cardapio Data</label>
                  <span id="cardapio-data">${cardapio-data}</span>
              </div>
              <div class="group">
                <p>Almoço</p>
                <ul class="ingredientes">
                    <li>${ingrediente}</li>
                </ul>
                <button class="btn blue right" name="action">Editar
                        <i class="material-icons right">edit</i>
                </button>
              </div>
              
              <div class="group">
                <p>Janta</p>
                <ul class="ingredientes">
                    <li>${ingrediente}</li>
                </ul>
                <button class="btn blue right" name="action">Editar
                        <i class="material-icons right">edit</i>
                </button>
              </div>     

              
             <a class="waves-effect waves-teal btn-flat right">Voltar</a>
          </form>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>

