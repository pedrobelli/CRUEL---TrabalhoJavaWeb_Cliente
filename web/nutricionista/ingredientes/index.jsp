<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>
<main>
    <div class="container row">

       <%@ include file="actionbutton.jsp"%>

     <h3 class="center">NUTRIÇÃO</h3>
    <section>
        <div class="container">
            <h4>Ingredientes</h4>
            <form>
                <div class="input-field main-search" >
                    <input id="search-ingrediente" type="search" required>
                    <label for="search-ingrediente"><i class="material-icons">search</i><span>Buscar Ingrediente</span></label>
                    <button class="btn waves-effect waves-light blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                      </button>
                </div>
            </form>
            <ul class="main-list">
                <li class="main-item">
                   <span>Ingrediente</span>
                   <span>Tipo</span>

                   <a href="#"> <i class="material-icons right">delete</i></a>
                   <a href="#"> <i class="material-icons right">edit</i></a>
                </li>
            </ul>
            <a href="new.html" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>

</main>
    
<%@ include file="/footer.jsp"%>
