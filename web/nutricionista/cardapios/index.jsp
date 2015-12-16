<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cardapios.Cardapio"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<main class="main">
    <div class="container row">

    <%@ include file="/nutricionista/actionbutton.jsp"%>

    <h3 class="center">Cardapio</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>cardapios?action=search" method="post">
                <div class="input-field main-search" >
                    <input id="search-cardapio" type="search" name="searchQuery">
                    <label for="search-cardapio"><span>Buscar Cardapio</span></label>
                    <button class="btn btn-main-search blue right" type="submit" name="action">Buscar
                        <i class="material-icons right">search</i>
                    </button>
                </div>
            </form>
                <%      
                    boolean existeCardapioHoje = (Boolean) request.getAttribute("existeCardapioHoje");
                    if(!existeCardapioHoje){             
                        String htmlBody = "<span style='color:red;'>Não existe cardapío cadastrado para o dia de hoje!</span>";

                        out.println(htmlBody);
                    }
                %>
            <ul class="main-list">
                <%      
                    List<Cardapio> cardapios = (List) request.getAttribute("cardapios");
                    if(cardapios.size()>0){
                        int index;
                        for(index=0; index < cardapios.size(); index++){                   
                            Cardapio cardapio = cardapios.get(index);
                            String htmlBody ="<li class='main-item'>";
                            htmlBody+="<span>" + cardapio.getDataFormatada() + "</span>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "cardapios?action=show' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + cardapio.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> VER </button></form>";
                            htmlBody+="</form>";
                            
                            htmlBody+="<form class='list-form right' action='" + url + "cardapios?action=edit' method='post'>";
                            htmlBody+="<input type='hidden' name='id' value='" + cardapio.getId() + "'>";
                            htmlBody+="<button class='btn btn-edit  blue' type='submit'> <i class='material-icons'>edit</i></button></form>";
                            htmlBody+="</form>";

                            out.println(htmlBody);
                        }
                    } else{
                        out.println("Nenhum cardápio foi encontrado.<br>");
                    }
                %>
            </ul>
            <a href="<%=url%>cardapios?action=new" class="btn btn-small waves-effect waves-light blue"><i class="material-icons">add</i></a>
        </div>
      </section>
    </div>
</main>
    
<%@ include file="/footer.jsp"%>
