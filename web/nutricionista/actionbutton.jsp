<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="fixed-action-btn">
    <a class="btn-floating btn-large blue">
      <i class="large material-icons">menu</i>
    </a>
    <ul>
      <li><a href="<%=getServletContext().getContextPath()%>/cardapios" class="btn-floating blue lighten-2 ">CARD</a></li>
      <li><a href="<%=getServletContext().getContextPath()%>/tiposIngrediente" class="btn-floating blue lighten-2 ">TPIN</a></li>      
      <li><a href="<%=getServletContext().getContextPath()%>/ingredientes" class="btn-floating blue lighten-2 ">INGR</a></li>
    </ul>
</div>
