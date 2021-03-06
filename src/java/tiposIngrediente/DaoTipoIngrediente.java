package tiposIngrediente;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DaoTipoIngrediente {
     private Session session;
     
     public DaoTipoIngrediente setDaoTipoIngrediente(Session session) {
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
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_ingrediente").addEntity(TipoIngrediente.class);        
        List tiposIngrediente = query.list();
        
        return  tiposIngrediente;
    }
    
    public TipoIngrediente get(int id) {
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_ingrediente WHERE id = :id").addEntity(TipoIngrediente.class).setParameter("id", id);
        List tiposIngrediente = query.list();
        
        TipoIngrediente tipoIngrediente = (TipoIngrediente) tiposIngrediente.get(0);
        return tipoIngrediente;
    }   
    
    public boolean checkExistance(String nome) {
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_ingrediente WHERE nome = :nome").addEntity(TipoIngrediente.class).setParameter("nome", nome);
        List tiposIngrediente = query.list();
        
        return tiposIngrediente.size() > 0;
    }   
    
    public List search(String searchQuery) {
        Query query = this.session.createSQLQuery("SELECT * FROM tipos_ingrediente WHERE nome LIKE '%"+searchQuery+"%'").addEntity(TipoIngrediente.class);        
        List tiposIngrediente = query.list();
        
        return  tiposIngrediente;
    }
    
    public Object create(TipoIngrediente tipoIngrediente) {
        this.session.save(tipoIngrediente);        
        return tipoIngrediente;
    }

    public Object update(TipoIngrediente tipoIngrediente) {
        TipoIngrediente tipoIngredientePersistido = this.get(tipoIngrediente.getId());
        tipoIngredientePersistido = tipoIngrediente;
        this.session.merge(tipoIngredientePersistido);
        
        return tipoIngredientePersistido;
    }
    
    public void delete(TipoIngrediente tipoIngrediente) {
        this.session.delete(tipoIngrediente);
    }
}
