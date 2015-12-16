package cardapios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DaoCardapio {
     private Session session;
     
     public DaoCardapio setDaoCardapio(Session session) {
        this.setSession(session);
        
        return this;
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    
    public List all(Session session) {
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios").addEntity(Cardapio.class);        
        List cardapios = query.list();
        
        return  cardapios;
    }
    
    public Cardapio get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios WHERE id = :id").addEntity(Cardapio.class).setParameter("id", id);
        List cardapios = query.list();
        
        Cardapio cardapio = (Cardapio) cardapios.get(0);
        return cardapio;
    }   
    
    public boolean checkExistance() {
        Date hoje = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hojeString = simpleDateFormat.format(hoje);
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios WHERE data = :hoje").addEntity(Cardapio.class);
        query.setParameter("hoje", hojeString);
        List cardapios = query.list();
        
        return cardapios.size() > 0;
    }   
    
    public List search(int mes, int ano) {
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios WHERE MONTH(data) = :mes AND YEAR(data) = :ano").addEntity(Cardapio.class);        
        query.setParameter("mes", mes);
        query.setParameter("ano", ano);
        List cardapios = query.list();
        
        return  cardapios;
    }
    
    public Object create(Cardapio cardapio) {
        this.session.save(cardapio);        
        return cardapio;
    }

    public Object update(Cardapio cardapio) {
        Cardapio cardapioPersistido = this.get(cardapio.getId());
        cardapioPersistido = cardapio;
        this.session.update(cardapioPersistido);
        
        return cardapioPersistido;
    }
    
    public void delete(Cardapio cardapio) {
        this.session.delete(cardapio);
    }
}
