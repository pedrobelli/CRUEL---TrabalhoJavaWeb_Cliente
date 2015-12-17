<%@page import="services.TipoCliente"%>
<%@page import="services.TiposClienteClient"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entradas.Entrada"%>
<!DOCTYPE html>

<%@ include file="/header.jsp"%>

<% 
    Entrada entrada = (Entrada) request.getAttribute("entrada"); 
    String dataAtual = (String) request.getAttribute("dataAtual");
%>

<main class="main">

    <div class="container row">

        <%@ include file="actionbutton.jsp"%>

        <h3 class="center">Entrada</h3>
        <section>
            <div class="container">
                <h4>Nova Entrada</h4>
                <%@ include file="/errors.jsp"%>
                <form class="cadast" action="<%=url%>entradas?action=create" method="post">
                                          
                    <div class="input-field">
                        <select name="tipoCliente" class="browser-default" value="">
                            <option value="" disabled selected>Tipo de Ingrediente</option>
                            <%
                                List<TipoCliente> tiposCliente = (List) request.getAttribute("tiposCliente");
                                out.println("<h1>"+tiposCliente+"</h1>");
                                if(tiposCliente.size()>0){
                                    int index;                                    
                                    for(TipoCliente t : tiposCliente){ 
                                                                               
                                        out.println("<option value='" + t.getId() + "' selected>" + t.getNome() + " | Valor: " + t.getValorRefeicaoWithTwoDecimals() + "</option>");
                                        
                                    }
                                }
                                
                                
                            %>
                        </select>

                    <div class="input-field">
                        <input type="text" name="data" value="<%=dataAtual%>" readonly>
                    </div>

                    
                    <button class="btn blue right" type="submit" name="action">Ok
                        <i class="material-icons right">send</i>
                    </button>
                    <a href="<%=url%>entradas" class="waves-effect waves-teal btn-flat right">Cancelar</a>
                </form>
            </div>
        </section>
    </div>
  </main>
<%@ include file="/footer.jsp"%>
