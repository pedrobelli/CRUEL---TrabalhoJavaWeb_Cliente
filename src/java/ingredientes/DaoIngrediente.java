/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingredientes;

import java.util.List;
import org.hibernate.Query;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Layla
 */
public class DaoIngrediente {
     private Session session;
     
     public DaoIngrediente setDaoIngrediente(Session session) {
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
        Query query = this.session.createSQLQuery("SELECT * FROM ingredientes").addEntity(Ingrediente.class);        
        List ingredientes = query.list();
        
        return  ingredientes;
    }
    
    public Ingrediente get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM ingredientes WHERE id = :id").addEntity(Ingrediente.class).setParameter("id", id);
        List ingredientes = query.list();
        
        Ingrediente ingrediente = (Ingrediente) ingredientes.get(0);
        return ingrediente;
    }   
    
    public boolean checkExistance(String nome) {
        Query query = this.session.createSQLQuery("SELECT * FROM ingredientes WHERE nome = :nome").addEntity(Ingrediente.class).setParameter("nome", nome);
        List ingredientes = query.list();
        
        return ingredientes.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM ingredientes WHERE nome LIKE '%"+searchQuery+"%'").addEntity(Ingrediente.class);        
        List ingredientes = query.list();
        
        return  ingredientes;
    }
    
    public Object create(Ingrediente ingrediente) {
        this.session.save(ingrediente);        
        return ingrediente;
    }

    public Object update(Ingrediente ingrediente) {
        Ingrediente ingredientePersistido = this.get(ingrediente.getId());
        ingredientePersistido = ingrediente;
        this.session.update(ingredientePersistido);
        
        return ingredientePersistido;
    }
    
    public void delete(Ingrediente ingrediente) {
        this.session.delete(ingrediente);
    }
}
