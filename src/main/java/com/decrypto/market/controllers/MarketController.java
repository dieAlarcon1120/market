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
    @Operation(summary = "Crea un recurso", description = "Este método crea un nuevo mercado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado"),
        @ApiResponse(responseCode = "409", description = "El recurso ya existe")
    })
    @PostMapping
    public ResponseEntity<?> createMarket(@RequestBody Market market){
        
        Market newMarket = marketService.save(market);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newMarket);
       
    }
    
    @CrossOrigin
    @Operation(summary = "Obtiene todos los recursos", description = "Este metodo obtiene una lista de todos los mercados existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
    })
    @GetMapping("/all")
    public List<Market> getAllMarkets(){
        return marketService.getAllMarkets();
    }
    
    @CrossOrigin
    @Operation(summary = "Obtiene un recurso", description = "Este metodo obtiene un mercado por id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
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
    @Operation(summary = "Elimina un recurso", description = "Este método elimina un mercado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "El recurso ya fue eliminado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id){
        if(!marketService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        marketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @CrossOrigin
    @Operation(summary = "Actualiza un recurso", description = "Este método actualiza un mercado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El recurso fue actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "El recurso ya existe")
    })
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
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("El mercado ya existe");
        }
    }
    
}
