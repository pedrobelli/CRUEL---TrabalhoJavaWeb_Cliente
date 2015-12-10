/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardapios;


import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Layla
 */
public class Cardapio implements Serializable {
    private int id;
    private Date data;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
    
            
}
