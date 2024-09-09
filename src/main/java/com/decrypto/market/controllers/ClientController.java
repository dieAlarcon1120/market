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
    @Operation(summary = "Obtiene todos los recursos", description = "Este metodo obtiene una lista de todos los comitentes existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
    })
    @GetMapping("/all")
    public List<Client> getAllsClients(){
        return clientService.getAllsClients();
    }
    
    @CrossOrigin
    @Operation(summary = "Obtiene un recurso", description = "Este metodo obtiene un comitente por id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
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
    @Operation(summary = "Crea un recurso", description = "Este método crea un nuevo comitente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado"),
        @ApiResponse(responseCode = "409", description = "El recurso ya existe")
    })
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client){
        try{
        Client newClient = clientService.save(client);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newClient);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("El cliente ya existe");
        }
    }
    
    @CrossOrigin
    @Operation(summary = "Elimina un recurso", description = "Este método elimina un comitente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "El recurso ya fue eliminado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        if(!clientService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @CrossOrigin
    @Operation(summary = "Actualiza un recurso", description = "Este método actualiza un comitente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El recurso fue actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "El recurso ya existe")
    })
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
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("El cliente ya existe");
        }
    }
    
    @GetMapping("/stats")
    @Operation(summary = "Obtiene una lista de recursos", description = "Este método obtiene la cantidad de comitentes existentes por cada pais y cada mercado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El recurso fue obtenido exitosamente")
    })
    public List<ClientsPercertageResponseDto> getClientsCount() {
        return clientService.getClientsCountByMarketAndCountry();
    }
    
}
