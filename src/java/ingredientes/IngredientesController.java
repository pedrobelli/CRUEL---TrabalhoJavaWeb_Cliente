package ingredientes;
    
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
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tiposIngrediente.DaoTipoIngrediente;
import utils.HibernateUtil;
    
@WebServlet(name = "IngredientesController", urlPatterns = {"/ingredientes"})
public class IngredientesController extends HttpServlet {

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
                request.setAttribute("ingredientes", this.all()); 

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/index.jsp").forward(request, response);

            } else if (action.equals("search")) {
                String searchQuery = request.getParameter("searchQuery");

                if (!searchQuery.equals("")) {
                    request.setAttribute("ingredientes", this.search(searchQuery));
                } else {
                    request.setAttribute("ingredientes", this.all());
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/index.jsp").forward(request, response);

            } else if (action.equals("new")) {
                DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());

                request.setAttribute("ingrediente", new Ingrediente());
                request.setAttribute("tiposIngrediente", daoTipoIngrediente.all(this.getSession()));
                
                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/new.jsp").forward(request, response);

            } else if (action.equals("create")) {                    
                this.validate();
                
                Ingrediente ingrediente = this.processRequestForm();
                this.create(ingrediente);
                
                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/ingredientes");

            } else if (action.equals("edit")) {
                DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));
                
                DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());

                request.setAttribute("ingrediente", daoIngrediente.get(id));
                request.setAttribute("tiposIngrediente", daoTipoIngrediente.all(this.getSession()));
                
                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                this.validate();

                Ingrediente ingrediente = this.processRequestForm();
                this.update(ingrediente);

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/ingredientes");

            } else if (action.equals("delete")) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(Integer.parseInt(request.getParameter("id")));

                this.delete(ingrediente);

                this.getTransaction().commit();

               this.getResponse().sendRedirect(getServletContext().getContextPath() + "/ingredientes");

            }

        } catch(Exception E) {
            E.printStackTrace();
            DaoTipoIngrediente daoTipoIngrediente = new DaoTipoIngrediente().setDaoTipoIngrediente(this.getSession());
            
            request.setAttribute("ingrediente", this.processRequestForError());
            request.setAttribute("tiposIngrediente", daoTipoIngrediente.all(this.getSession()));
            
            String action = request.getParameter("action");

            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/ingredientes/edit.jsp").forward(request, response);
            }
        }finally {
            out.close();
        }
    }

    public List<Ingrediente> all() throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
        ingredientes = (List) daoIngrediente.all(this.getSession());

        return ingredientes;
    }

    public List<Ingrediente> search(String searchQuery) throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

        DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
        ingredientes = (List) daoIngrediente.search(searchQuery);

        return ingredientes;
    }

    public void create(Ingrediente ingrediente) throws SQLException {
        DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
        daoIngrediente.create(ingrediente);
    }

    public void update(Ingrediente ingrediente) throws SQLException {
        DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
        daoIngrediente.update(ingrediente);
    }

    public void delete(Ingrediente ingrediente) throws SQLException {
        DaoIngrediente daoIngrediente = new DaoIngrediente().setDaoIngrediente(this.getSession());
        daoIngrediente.delete(ingrediente);
    }

    private void validate() throws Exception {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("nome").isEmpty()) {
            errors.add("O campo nome deve ser preenchido;");
        }
		
	if (request.getParameter("tipoIngrediente") == null || request.getParameter("tipoIngrediente").isEmpty()) {
            errors.add("O campo tipo de ingrediente deve ser preenchido;");
        }      

        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
         }   
    }

    private Ingrediente processRequestForm() {
        Ingrediente ingre = new Ingrediente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            ingre.setId(Integer.parseInt(request.getParameter("id")));
        }

        ingre.setNome(request.getParameter("nome"));
        ingre.setTipoIngrediente(Integer.parseInt(request.getParameter("tipoIngrediente")));
        ingre.setDescricao(request.getParameter("descricao"));
        
        

        return ingre;
    }

    private Ingrediente processRequestForError() {
        Ingrediente ingre = new Ingrediente();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            ingre.setId(Integer.parseInt(request.getParameter("id")));
        }
        ingre.setNome(request.getParameter("nome"));
        
        if (request.getParameter("tipoIngrediente") != null && !request.getParameter("tipoIngrediente").isEmpty()) {
            ingre.setTipoIngrediente(Integer.parseInt(request.getParameter("tipoIngrediente")));
        }
        
        ingre.setDescricao(request.getParameter("descricao"));

        return ingre;
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
            Logger.getLogger(IngredientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngredientesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IngredientesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngredientesController.class.getName()).log(Level.SEVERE, null, ex);
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
