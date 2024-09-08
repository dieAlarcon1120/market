package com.decrypto.market.controllers;

import com.decrypto.market.Services.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.decrypto.market.models.Market;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dieal
 */
@RestController
@RequestMapping("/api/markets")
public class MarketController {
    
    @Autowired
    private MarketService marketService;
    
    @CrossOrigin
    @PostMapping
        @Operation(summary = "Create a new resource", description = "This method create a new Market.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created resource"),
    })
    public ResponseEntity<?> createMarket(@RequestBody Market market){
        
        Market newMarket = marketService.save(market);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newMarket);
       
    }
    
    @CrossOrigin
    @GetMapping("/all")
    public List<Market> getAllMarkets(){
        return marketService.getAllMarkets();
    }
    
    @CrossOrigin
    @GetMapping("/detail/{id}")
    public ResponseEntity<Market> getClientById(@PathVariable Long id){  
        Optional<Market> market = marketService.getMarketById(id);
        if(market.isPresent()){
            return ResponseEntity.ok(market.get());
        }else{
            return ResponseEntity.notFound().build();
        }           
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id){
        if(!marketService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        marketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMarket(@PathVariable Long id,@RequestBody Market market){
        if(!marketService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        
        try{
            market.setId(id);
            Market marketUpdate = marketService.save(market);
            return ResponseEntity.ok(marketUpdate);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("The market already exist");
        }
    }
    
}
