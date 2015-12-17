package entradas;
    
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.TipoCliente;
import services.TiposClienteClient;
import utils.HibernateUtil;
    
@WebServlet(name = "EntradasController", urlPatterns = {"/entradas"})
public class EntradasController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Session session;
    private Transaction transaction;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
    * methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    * @throws java.sql.SQLException
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            this.setSession(HibernateUtil.getSessionFactory().openSession());
            this.setTransaction(this.getSession().beginTransaction());
            
            HttpSession httpSession = request.getSession(false); 
            //Usuario usuarioSession = (Usuario) httpSession.getAttribute("usuarioSession");

            //if (usuarioSession == null) {
            //    this.getResponse().sendRedirect(getServletContext().getContextPath() + "/login");
            //}

            String action = request.getParameter("action");
                
            if (action == null) {
                request.setAttribute("entradas", this.all()); 

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/atendente/index.jsp").forward(request, response);

            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");

                if (!searchQuery.equals("")) {
                    request.setAttribute("entradas", this.search(searchQuery));
                } else {
                    request.setAttribute("entradas", this.all());
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/atendente/index.jsp").forward(request, response);

            } else if (action.equals("new")) {
                
                List<TipoCliente> tiposCliente = new TiposClienteClient().listAll();                
                String dataAtual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                
                request.setAttribute("tiposCliente", tiposCliente);                  
                request.setAttribute("entrada", new Entrada());
                request.setAttribute("dataAtual", dataAtual); 
                
                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/atendente/new.jsp").forward(request, response);

            } else if (action.equals("create")) {                    
                this.validate();

                Entrada entrada = this.processRequestForm();
                this.create(entrada);

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/entradas");

            } else if (action.equals("edit")) {
                DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));
                
                 List<TipoCliente> tiposCliente = new TiposClienteClient().listAll();                
                
                request.setAttribute("tiposCliente", tiposCliente);                  
                
                request.setAttribute("entrada", daoEntrada.get(id));

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/atendente/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                this.validate();

                Entrada entrada = this.processRequestForm();
                this.update(entrada);

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/entradas");

            } else if (action.equals("delete")) {
                Entrada entrada = new Entrada();
                entrada.setId(Integer.parseInt(request.getParameter("id")));

                this.delete(entrada);

                this.getTransaction().commit();

               this.getResponse().sendRedirect(getServletContext().getContextPath() + "/entradas");

            }

        } catch(Exception E) {
            E.printStackTrace();
            request.setAttribute("entrada", this.processRequestForError());
            //request.setAttribute("usuario", Usuario.processRequestForError(request));

            String action = request.getParameter("action");

            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/atendente/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/atendente/edit.jsp").forward(request, response);
            }
        }finally {
            out.close();
        }
    }

    public List<Entrada> all() throws SQLException {
        List<Entrada> entradas = new ArrayList<Entrada>();

        DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
        entradas = (List) daoEntrada.all(this.getSession());

        return entradas;
    }

    public List<Entrada> search(String searchQuery) throws SQLException {
        List<Entrada> entradas = new ArrayList<Entrada>();

        DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
        entradas = (List) daoEntrada.search(searchQuery);

        return entradas;
    }

    public void create(Entrada entrada) throws SQLException {
        DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
        daoEntrada.create(entrada);
    }

    public void update(Entrada entrada) throws SQLException {
        DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
        daoEntrada.update(entrada);
    }

    public void delete(Entrada entrada) throws SQLException {
        DaoEntrada daoEntrada = new DaoEntrada().setDaoEntrada(this.getSession());
        daoEntrada.delete(entrada);
    }

    private void validate() throws Exception {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();

        		
        if (request.getParameter("tipoCliente").isEmpty()) {
            errors.add("O campo tipo de tipo Cliente deve ser preenchido;");
        }     

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
         }   
    }

    private Entrada processRequestForm() {
        Entrada entr = new Entrada();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            entr.setId(Integer.parseInt(request.getParameter("id")));
        }

        entr.setTipoCliente(parseInt(request.getParameter("tipoCliente")));

        return entr;
    }

    private Entrada processRequestForError() {
        Entrada entr = new Entrada();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            entr.setId(Integer.parseInt(request.getParameter("id")));
        }
        String TP = request.getParameter("tipoCliente");
        if (TP != null ) {
            
            entr.setTipoCliente(parseInt(request.getParameter("tipoCliente")));
        }
        
        

        return entr;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(EntradasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(EntradasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EntradasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EntradasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
