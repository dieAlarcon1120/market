package com.decrypto.market.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author dieal
 */
@Entity
@Table(name = "markets")
public class Market {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(unique = true)
    private String code;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;
    
    @ManyToMany(mappedBy = "markets", cascade = {CascadeType.MERGE})
    private Set<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
     
    
     
}
