package cardapios;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import utils.MesEnum.Mes;

@WebServlet(name = "CardapiosController", urlPatterns = {"/cardapios"})
public class CardapiosController extends HttpServlet {

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            this.setRequest(request);
            this.setResponse(response);
            this.setSession(HibernateUtil.getSessionFactory().openSession());
            this.setTransaction(this.getSession().beginTransaction());
            
            String action = request.getParameter("action");

            if (action == null) {
                Calendar calendario = Calendar.getInstance();
                int mes = calendario.get(calendario.MONTH)+1;
                int ano = calendario.get(calendario.YEAR);
                request.setAttribute("existeCardapioHoje", this.checkExistance()); 
                request.setAttribute("cardapios", this.search(mes, ano));
                request.setAttribute("mes", mes); 
                request.setAttribute("ano", ano); 

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/cardapios/index.jsp").forward(request, response);

            } else if (action.equals("new")) {
                request.setAttribute("cardapio", new CardapioDecorator());

                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/cardapios/new.jsp").forward(request, response);

            } else if (action.equals("search")) {
                request.setAttribute("existeCardapioHoje", this.checkExistance());
                String mes = request.getParameter("mes");
                String ano = request.getParameter("ano");

                if ((!mes.equals("") && mes.length() < 4) && !ano.equals("")) {
                    int mesInt = Integer.parseInt(mes);
                    int anoInt = Integer.parseInt(ano);
                    request.setAttribute("mes", mesInt); 
                    request.setAttribute("ano", anoInt); 
                    request.setAttribute("cardapios", this.search(mesInt, anoInt));
                } else {
                    Calendar calendario = Calendar.getInstance();
                    int mesInt = calendario.get(calendario.MONTH)+1;
                    int anoInt = calendario.get(calendario.YEAR);
                    request.setAttribute("mes", mesInt); 
                    request.setAttribute("ano", anoInt); 
                    request.setAttribute("cardapios", this.search(mesInt, anoInt));
                }

                this.getTransaction().commit();

                getServletContext().getRequestDispatcher("/nutricionista/cardapios/index.jsp").forward(request, response);

            } else if (action.equals("create")) {
                this.validate();

                Cardapio cardapio = this.processRequestForm();
                this.create(cardapio);

                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/cardapios");

            } else if (action.equals("edit")) {
                DaoCardapio daocardapio = new DaoCardapio().setDaoCardapio(this.getSession());
                int id = Integer.parseInt(request.getParameter("id"));
                
                Cardapio cardapio = daocardapio.get(id);
                CardapioDecorator cardapioDecorator = new CardapioDecorator();
                
                cardapioDecorator.setId(cardapio.getId());
                cardapioDecorator.setData(cardapio.getDataFormatadaISO());
                
                request.setAttribute("cardapio", cardapioDecorator);
                this.getTransaction().commit();
                
                getServletContext().getRequestDispatcher("/nutricionista/cardapios/edit.jsp").forward(request, response);

            } else if (action.equals("update")) {
                this.validate();
                
                Cardapio cardapio = this.processRequestForm();
                this.update(cardapio);                
                
                this.getTransaction().commit();

                this.getResponse().sendRedirect(getServletContext().getContextPath() + "/cardapios");

            }
            
        } catch(Exception E) {
            E.printStackTrace();
            request.setAttribute("cardapio", this.processRequestForError());
            
            String action = request.getParameter("action");

            if (action != null && action.equals("create")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/cardapios/new.jsp").forward(request, response);
            } else if (action != null && action.equals("update")) {
                this.getTransaction().rollback();
                getServletContext().getRequestDispatcher("/nutricionista/cardapios/edit.jsp").forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    public List<Cardapio> search(int mes, int ano) throws SQLException {
        List<Cardapio> cardapios = new ArrayList<Cardapio>();

        DaoCardapio daoCardapio = new DaoCardapio().setDaoCardapio(this.getSession());
        cardapios = (List) daoCardapio.search(mes, ano);

        return cardapios;
    }

    public boolean checkExistance() throws SQLException {
        DaoCardapio daoCardapio = new DaoCardapio().setDaoCardapio(this.getSession());
        boolean existance = (boolean) daoCardapio.checkExistance();

        return existance;
    }

    public void create(Cardapio cardapio) throws SQLException {
        DaoCardapio daoCardapio = new DaoCardapio().setDaoCardapio(this.getSession());
        daoCardapio.create(cardapio);
    }
    
    public void update(Cardapio cardapio) throws SQLException {
        DaoCardapio daoCardapio = new DaoCardapio().setDaoCardapio(this.getSession());
        daoCardapio.update(cardapio);
    }
    
    private List<String> validate() throws Exception {
        List<String> errors = new ArrayList<>();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("data").isEmpty()) {
            errors.add("O campo data deve ser preenchido;");
        } else {
            String data = request.getParameter("data");
            
            if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
                errors.add("Data inválida. Ex:(31/03/2007);");
            }
        }
        
        if (!errors.isEmpty()) {
            errors.add("A operação não pôde ser concluída por causa dos seguintes erros:");
            request.setAttribute("errors", errors);
            throw new Exception();
        }

        return errors; 
    }
    
    private Cardapio processRequestForm() throws ParseException {
        Cardapio card = new Cardapio();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            card.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = simpleDateFormat.parse(request.getParameter("data"));
        card.setData(data);
        
        return card;
    }
    
    private CardapioDecorator processRequestForError() {
        CardapioDecorator card = new CardapioDecorator();
        HttpServletRequest request = this.getRequest();

        if (request.getParameter("id") != null) {
            card.setId(Integer.parseInt(request.getParameter("id")));
        }

        card.setData(request.getParameter("data"));

        return card;
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
        } catch (Exception ex) {
            Logger.getLogger(CardapiosController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CardapiosController.class.getName()).log(Level.SEVERE, null, ex);
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
