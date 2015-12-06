<main>
    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>

     <h3 class="center">CAIXA</h3>
      <section>
        <div class="container">
          <h4>Lista Entradas</h4>
          
          <form>
            <div class="input-field search-entry" >
              <input id="search-entry" type="search" required>
              <label for="search-entry"><i class="material-icons">search</i><span>Buscar Entrada</span></label>
            </div>
          </form>
          
          <ul class="entry-list">
            <li class="entry-item">
              <span>Categoria</span>
              <span>Data</span>
              <span>Valor</span>

              <a href="#"> <i class="material-icons right">delete</i></a>
              <a href="#"> <i class="material-icons right">edit</i></a>
            </li>            
          </ul>
        </div>
      </section>
    </div>
  </main>
