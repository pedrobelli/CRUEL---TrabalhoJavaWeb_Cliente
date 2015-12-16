<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>
<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
    <section>
        <form class="cadast" action="<%=url%>cardapios?action=search" method="post">
            <div class="input-field main-search" >
                <select name="mes" class="browser-default">
                    <option value="" disabled selected>Mes</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                  </select>
                <input id="search-cardapio" type="search" name="searchQuery">
                <label for="search-cardapio"><span>Buscar Cardapio</span></label>
                <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                    <i class="material-icons right">search</i>
                </button>
            </div>
            </form>
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

