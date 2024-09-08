package com.decrypto.market.models;

import java.io.Serializable;

/**
 *
 * @author dieal
 */
public class CompositeID implements Serializable{
    
    private Long idClient;
    private Long idMarket;

    public CompositeID(Long idClient, Long idMarket) {
        this.idClient = idClient;
        this.idMarket = idMarket;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(Long idMarket) {
        this.idMarket = idMarket;
    }
    
    
    
    
    
}
