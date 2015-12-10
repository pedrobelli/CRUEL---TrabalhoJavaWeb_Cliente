/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardapios;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Layla
 */
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
    
    public boolean checkExistance(String cpf) {
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios WHERE nome = :nome").addEntity(Cardapio.class).setParameter("cpf", cpf);
        List cardapios = query.list();
        
        return cardapios.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM cardapios WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Cardapio.class);        
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
