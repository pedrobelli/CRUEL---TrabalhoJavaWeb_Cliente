<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>
<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
    <section>
        <div class="container">
            <h4>Editar Cardápio</h4>
             <form class="cadast" action="index.html" method="post">
              <div class="input-field" >
                <input type="date" name="cardapio-data" class="datepicker">
              </div>
              <div class="group">
                <p>Almoço</p>
                <div class="input-field">
                  <select name="carne" class="browser-default">
                      <option value="" disabled selected>Carne</option>
                      <option value="1">Option 1</option>
                      <option value="2">Option 2</option>
                      <option value="3">Option 3</option>
                    </select>
                </div>
                <div class="input-field">
                    <select name="salada" class="browser-default">
                        <option value="" disabled selected>Salada</option>
                        <option value="1">Option 1</option>
                        <option value="2">Option 2</option>
                        <option value="3">Option 3</option>
                      </select>
                </div>
                <div class="input-field">
                    <select name="acompanhamento" class="browser-default">
                        <option value="" disabled selected>Acompanhamento</option>
                        <option value="1">Option 1</option>
                        <option value="2">Option 2</option>
                        <option value="3">Option 3</option>
                      </select>
                </div>
              </div>
              
              <div class="group">
                <p>Janta</p>
                <div class="input-field">
                  <select name="carne" class="browser-default">
                      <option value="" disabled selected>Carne</option>
                      <option value="1">Option 1</option>
                      <option value="2">Option 2</option>
                      <option value="3">Option 3</option>
                    </select>
                </div>
                <div class="input-field">
                    <select name="salada" class="browser-default">
                        <option value="" disabled selected>Salada</option>
                        <option value="1">Option 1</option>
                        <option value="2">Option 2</option>
                        <option value="3">Option 3</option>
                      </select>
                </div>
                <div class="input-field">
                    <select name="acompanhamento" class="browser-default">
                        <option value="" disabled selected>Acompanhamento</option>
                        <option value="1">Option 1</option>
                        <option value="2">Option 2</option>
                        <option value="3">Option 3</option>
                      </select>
                </div>
              </div>
              

              <button class="btn blue right" type="submit" name="action">Ok
               <i class="material-icons right">send</i>
             </button>
             <a class="waves-effect waves-teal btn-flat right">Cancelar</a>
          </form>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>
