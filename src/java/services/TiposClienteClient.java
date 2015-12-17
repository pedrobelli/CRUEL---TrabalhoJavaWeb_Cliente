/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:TiposClienteResource
 * [TiposCliente]<br>
 * USAGE:
 * <pre>
 *        TiposClienteClient client = new TiposClienteClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Layla
 */
public class TiposClienteClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:3030/CRUEL_-_TrabalhoJavaWeb_Gerente/services";

    public TiposClienteClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("/TiposCliente");
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getJson(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public List<TipoCliente> listAll() {
        WebTarget resource = webTarget;
        List<TipoCliente> lista = resource
                .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .get(new GenericType<List<TipoCliente>>(){});
        
        return lista;
    }

    public void close() {
        client.close();
    }
    
}
