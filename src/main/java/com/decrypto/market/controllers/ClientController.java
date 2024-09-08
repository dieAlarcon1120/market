package com.decrypto.market.controllers;

import com.decrypto.market.Services.ClientService;
import com.decrypto.market.dto.ClientsPercertageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.decrypto.market.models.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 *
 * @author dieal
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @CrossOrigin
    @GetMapping("/all")
    public List<Client> getAllsClients(){
        return clientService.getAllsClients();
    }
    
    @CrossOrigin
    @GetMapping("/detail/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){  
        Optional<Client> client = clientService.getClientById(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }else{
            return ResponseEntity.notFound().build();
        }           
    }
    
    @CrossOrigin
    @PostMapping
        @Operation(summary = "Obtener un recurso", description = "Este método crea un nuevo comitente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public ResponseEntity<?> createClient(@RequestBody Client client){
        try{
        Client newClient = clientService.save(client);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newClient);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("The client already exist");
        }
    }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        if(!clientService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,@RequestBody Client client){
        if(!clientService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        
        try{
            client.setId(id);
            Client clientUpdate = clientService.save(client);
            return ResponseEntity.ok(clientUpdate);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("The client already exist");
        }
    }
    
      @GetMapping("/stats")
    public List<ClientsPercertageResponseDto> getClientsCount() {
        return clientService.getClientsCountByMarketAndCountry();
    }
    
}
