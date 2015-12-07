<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
      <section>
        <div class="container">
          <h4>Lista de Cardápios</h4>
          
          <form>
            <div class="input-field main-search" >
              <input id="search-card" type="search" required>
              <label for="search-card"><i class="material-icons">search</i><span>Buscar Cardapio</span></label>
            </div>
          </form>
          
          <ul class="main-list">
            <li class="main-item">
              <span>Data</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
              <a href="#"> <i class="material-icons right">edit</i></a>
            </li>
            
          </ul>
        </div>
      </section>
    </div>
  </main>

<%@ include file="/footer.jsp"%>