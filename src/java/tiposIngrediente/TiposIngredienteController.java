package tiposIngrediente;
    
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
    
@WebServlet(name = "TiposIngredienteController", urlPatterns = {"/tiposIngrediente"})
public class TiposIngredienteController extends HttpServlet {
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
            
            //HttpSession httpSession = request.getSession(false); 
            //Usuario usuarioSession = (Usuario) httpSession.getAttribute("usuarioSession");

            //if (usuarioSession == null) {
            //    this.getResponse().sendRedirect(getServletContext().getContextPath() + "/login");
            //}

            String action = request.getParameter("action");
                
            if (action == null) {
                request.setAttribute("tiposIngrediente", this.all()); 

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/index.jsp").forward(request, response);

            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");

                if (!searchQuery.equals("")) {
                    request.setAttribute("tiposIngrediente", this.search(searchQuery));
                } else {
                    request.setAttribute("tiposIngrediente", this.all());
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/index.jsp").forward(request, response);

            } else if (action.equals("new")) {

                request.setAttribute("tipoIngrediente", new TipoIngrediente());

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/new.jsp").forward(request, response);

            } else if (action.equals("create")) {                    
                this.validate();

                TipoIngrediente tipoIngrediente = this.processRequestForm();
                this.create(tipoIngrediente);

                request.setAttribute("tiposIngrediente", this.all());

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposIngrediente");
                
            } else if (action.equals("edit")) {
                DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("tipoIngrediente", daoTipoIngrediente.get(id));

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                this.validate();

                TipoIngrediente tipoIngrediente = this.processRequestForm();
                this.update(tipoIngrediente);

                this.getTransaction().commit();
                
                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposIngrediente");

            } else if (action.equals("delete")) {
                TipoIngrediente tipoIngrediente = new TipoIngrediente();
                tipoIngrediente.setId(Integer.parseInt(request.getParameter("id")));

                this.delete(tipoIngrediente);

                this.getTransaction().commit();

               this.getResponse().sendRedirect(getServletContext().getContextPath() + "/tiposIngrediente");

            }

        } catch(Exception E) {
            E.printStackTrace();
            request.setAttribute("tipoIngrediente", this.processRequestForError());

            String action = request.getParameter("action");

            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/tiposIngrediente/edit.jsp").forward(request, response);
            }
        }finally {
            out.close();
        }
    }

    public List<TipoIngrediente> all() throws SQLException {
        List<TipoIngrediente> tiposIngrediente = new ArrayList<TipoIngrediente>();

        DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
        tiposIngrediente = (List) daoTipoIngrediente.all(this.getSession());

        return tiposIngrediente;
    }

    public List<TipoIngrediente> search(String searchQuery) throws SQLException {
        List<TipoIngrediente> tiposIngrediente = new ArrayList<TipoIngrediente>();

        DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
        tiposIngrediente = (List) daoTipoIngrediente.search(searchQuery);

        return tiposIngrediente;
    }

    public void create(TipoIngrediente tipoIngrediente) throws SQLException {
        DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
        daoTipoIngrediente.create(tipoIngrediente);
    }

    public void update(TipoIngrediente tipoIngrediente) throws SQLException {
        DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
        daoTipoIngrediente.update(tipoIngrediente);
    }

    public void delete(TipoIngrediente tipoIngrediente) throws SQLException {
        DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
        daoTipoIngrediente.delete(tipoIngrediente);
    }

    private void validate() throws Exception {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("nome").isEmpty()) {
            errors.add("O campo nome deve ser preenchido;");
        }

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
         }   
    }

    private TipoIngrediente processRequestForm() {
        TipoIngrediente tipo = new TipoIngrediente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));

        return tipo;
    }

    private TipoIngrediente processRequestForError() {
        TipoIngrediente tipo = new TipoIngrediente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            tipo.setId(Integer.parseInt(request.getParameter("id")));
        }

        tipo.setNome(request.getParameter("nome"));

        return tipo;
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
            Logger.getLogger(TiposIngredienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TiposIngredienteController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TiposIngredienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TiposIngredienteController.class.getName()).log(Level.SEVERE, null, ex);
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
