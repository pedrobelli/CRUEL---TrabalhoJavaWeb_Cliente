<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ingredientes.Ingrediente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/nutricionista/actionbutton.jsp"%>

    <h3 class="center">Ingrediente</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>ingredientes?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-ingrediente" type="search" name="searchQuery">
                    <label for="search-ingrediente"><span>Buscar Ingrediente</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Ingrediente> ingredientes = (List) request.getAttribute("ingredientes");
                    if(ingredientes.size()>0){
                        int index;
                        for(index=0; index < ingredientes.size(); index++){                   
                            Ingrediente ingrediente = ingredientes.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + ingrediente.getNome() + "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "ingredientes?action=delete' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + ingrediente.getId() + "'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "ingredientes?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + ingrediente.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nao existem Tipos de Ingrediente cadastrados.");
                    }
                %>
            </ul>
            <a href="<%=url%>ingredientes?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
