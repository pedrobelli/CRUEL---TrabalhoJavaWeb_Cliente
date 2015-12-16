<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cardapios.Cardapio"%>
<%@page import="utils.MesEnum.Mes"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%@ include file="/header.jsp"%>

<% 
    int mes = (Integer) request.getAttribute("mes"); 
    int ano = (Integer) request.getAttribute("ano");
%>

<main class="main">
    <div class="container row">

    <%@ include file="/nutricionista/actionbutton.jsp"%>

    <h3 class="center">Cardapio</h3>
    <section>
        <div class="container">
            <form class="cadast" action="<%=url%>cardapios?action=search" method="post">
                <label>Buscar Cardapio</label>
                <div class="input-field main-search" >
                    
                    <div class="input-field">
                        <select name="mes" class="browser-default" value="">
                            <%
                                for(Mes mesEnum : Mes.values()){ 
                                    if (mesEnum.getCod() == mes) {
                                        out.println("<option value='" + mesEnum.getCod() + "' selected>" + mesEnum.getNome() + "</option>");
                                    } else {
                                        out.println("<option value='" + mesEnum.getCod() + "'>" + mesEnum.getNome() + "</option>");
                                    }

                                }
                            %>
                        </select>
                    </div>
                        
                    <div class="input-field">
                        <label for="ano">Ano:</label>
                        <input type="text" name="ano" value="${ano}">   
                    </div>
                    
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
