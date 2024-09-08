/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.decrypto.market.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dieal
 */
public class ClientsPercertageResponseDto {
    
    private String country;
    private  Map<String, Long> market = new HashMap<>();


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, Long> getMarket() {
        return market;
    }

    public void setMarket(Map<String, Long> market) {
        this.market = market;
    }


    
    
    
}
