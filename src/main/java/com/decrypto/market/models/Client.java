package com.decrypto.market.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;


/**
 *
 * @author dieal
 */
@Entity
@Table(name = "clients")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(unique = true)
    private String description;
     
    @ManyToMany()
    @JoinTable(
        name = "clients_markets",
        joinColumns = @JoinColumn(name = "id_client"),
        inverseJoinColumns = @JoinColumn(name = "id_market")
    )
    private Set<Market> markets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(Set<Market> markets) {
        this.markets = markets;
    }


    
    
    
}
