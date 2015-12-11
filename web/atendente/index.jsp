<%@page import="services.TiposClienteClient"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entradas.Entrada"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

        <%@ include file="/atendente/actionbutton.jsp"%>

    <h3 class="center">Entrada</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>entradas?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-entrada" type="search" name="searchQuery">
                    <label for="search-entrada"><span>Buscar Entrada</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
            <ul class="main-list">
                <%        
                    List<Entrada> entradas = (List) request.getAttribute("entradas");
                    
                    if(entradas.size()>0){
                        int index;
                        for(index=0; index < entradas.size(); index++){                   
                            Entrada entrada = entradas.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + entrada.getTipoCliente() + entrada.getData() + "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "entradas?action=delete' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + entrada.getId() + "'>";
                            htmlBody+="<button class='btn btn-delete  blue' type='submit'> <i class='material-icons'>delete</i></button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "entradas?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + entrada.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    }
                    else{
                        out.println("Nao existem Tipos de Entrada cadastrados.");
                    }
                %>
            </ul>
            <a href="<%=url%>entradas?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
