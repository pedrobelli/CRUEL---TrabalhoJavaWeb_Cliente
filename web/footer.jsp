<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String urlFooter = getServletContext().getContextPath() + "/"; %>

<footer class="page-footer">
    <div class="footer-copyright">
      <div class="container">
      © 2015 Copyright TADS|WEB2|DAC
      </div>
    </div>
  </footer>

  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

  <script src="<%=urlFooter%>assets/javascripts/bin/materialize.js"></script>
  <script src="<%=urlFooter%>assets/javascripts/init.js"></script>
  <script src="<%=urlFooter%>assets/javascripts/jquery-1.11.3.min.js" type="text/javascript"></script>
  <script src="<%=urlFooter%>assets/javascripts/inputmask/inputmask.js" type="text/javascript"></script>
  <script src="<%=urlFooter%>assets/javascripts/app.js" type="text/javascript"></script>

  </body>
</html>
