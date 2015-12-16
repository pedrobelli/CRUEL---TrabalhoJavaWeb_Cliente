package cardapios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cardapio implements Serializable {
    private int  id;
    private Date data;

    public Cardapio(int id, Date data) {
        this.id = id;
        this.data = data;
    }

    Cardapio() {
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public String getDataFormatada() {
        Date data = this.data;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        
        return dataString;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
    
            
}
