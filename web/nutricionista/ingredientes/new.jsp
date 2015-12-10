<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
      <section>
          <div class="container">
          <h4>Novo Ingrediente</h4>
          
          <form class="cadast" action="index.html" method="post">
            <div class="input-field">
                <label for="name">Nome</label>
                <input type="text" name="name">
            </div>
            <div class="input-field">
                <select name="tipo" class="browser-default">
                   <option value="" disabled selected>Tipo</option>
                   <option value="1">Option 1</option>
                   <option value="2">Option 2</option>
                   <option value="3">Option 3</option>
                 </select>
            </div>
              
            <div class="input-field">
                <label for="descricao">Descricao</label>
                <textarea name="descricao" class="materialize-textarea"></textarea>
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