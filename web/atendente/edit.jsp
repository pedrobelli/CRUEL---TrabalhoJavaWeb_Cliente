<main>
    <div class="container row">

       <%@ include file="/gerente/actionbutton.jsp"%>

     <h3 class="center">CAIXA</h3>
      <section>
        <div class="container">
          <h4>Entrada</h4>
          <form class="cad-entry" action="index.html" method="post">
            <div class="input-field">
              <input type="date" name="entry-date" class="datepicker">
            </div>
            <div class="input-field">
              <label for="entry-hour">Hora:</label>
              <input type="text" name="entry-hour">
            </div>
            
             <div class="input-field">
              <label for="entry-value">Valor:</label>
              <input type="text" name="entry-value">
            </div>
            
            <div class="input-field">
              <select class="browser-default">
                <option value="" disabled selected>Categoria</option>
                <option value="1">Funcionario</option>
                <option value="2">Aluno</option>
                <option value="3">Externo</option>
              </select>
            </div>
                  

              <button class="btn blue right" type="submit" name="action">Ok
               <i class="material-icons right">send</i>
             </button>
             
          </form>
        </div>
      </section>
    </div>
  </main>
