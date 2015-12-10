/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradas;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Layla
 */
public class DaoEntrada {
     private Session session;
     
     public DaoEntrada setDaoEntrada(Session session) {
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
        Query query = this.session.createSQLQuery("SELECT * FROM entradas").addEntity(Entrada.class);        
        List entradas = query.list();
        
        return  entradas;
    }
    
    public Entrada get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM entradas WHERE id = :id").addEntity(Entrada.class).setParameter("id", id);
        List entradas = query.list();
        
        Entrada entrada = (Entrada) entradas.get(0);
        return entrada;
    }   
    
    public boolean checkExistance(String cpf) {
        Query query = this.session.createSQLQuery("SELECT * FROM entradas WHERE nome = :nome").addEntity(Entrada.class).setParameter("cpf", cpf);
        List entradas = query.list();
        
        return entradas.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM entradas WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Entrada.class);        
        List entradas = query.list();
        
        return  entradas;
    }
    
    public Object create(Entrada entrada) {
        this.session.save(entrada);        
        return entrada;
    }

    public Object update(Entrada entrada) {
        Entrada entradaPersistido = this.get(entrada.getId());
        entradaPersistido = entrada;
        this.session.update(entradaPersistido);
        
        return entradaPersistido;
    }
    
    public void delete(Entrada entrada) {
        this.session.delete(entrada);
    }
}
