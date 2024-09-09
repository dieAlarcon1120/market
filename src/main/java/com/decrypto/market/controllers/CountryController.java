package com.decrypto.market.controllers;

import com.decrypto.market.Services.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.decrypto.market.models.Country;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
/**
 *
 * @author dieal
 */
@RestController
@Validated
@RequestMapping("/api/countries")
public class CountryController {
    
    @Autowired
    private CountryService countryService;
    
    @CrossOrigin
    @Operation(summary = "Crea un recurso", description = "Este método crea un nuevo pais (Solo se permiten Argentina y Uruguay).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Recurso creado"),
        @ApiResponse(responseCode = "409", description = "Recurso ya existente"),
        @ApiResponse(responseCode = "400", description = "Error en el request")
    })
    @PostMapping
    public ResponseEntity<?> createCountry(@RequestBody Country country){
        try{
            Country newCountry = countryService.save(country);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(newCountry);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("El pais ya existe");
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Ocurrio un error");
        }
    }
    
    
    @CrossOrigin
    @Operation(summary = "Obtiene un recurso", description = "Este metodo obtiene un pais por id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id){  
        Optional<Country> country = countryService.getCountryById(id);
        if(country.isPresent()){
            return ResponseEntity.ok(country.get());
        }else{
            return ResponseEntity.notFound().build();
        }           
    }
    

    
    @CrossOrigin
    @Operation(summary = "Obtiene todos los recursos", description = "Este metodo obtiene una lista de todos los paises existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso encontrado"),
    })
    @GetMapping("/all")
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }
    
    
    @CrossOrigin
    @Operation(summary = "Elimina un recurso", description = "Este método elimina un pais.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "El recurso ya fue eliminado"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        if(!countryService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @CrossOrigin
    @Operation(summary = "Actualiza un recurso", description = "Este método actualiza un pais.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El recurso fue actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado"),
        @ApiResponse(responseCode = "409", description = "El recurso ya existe"),
        @ApiResponse(responseCode = "400", description = "Error en el request")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,@RequestBody Country country){
        if(!countryService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        
        try{
            country.setId(id);
            Country countryUpdate = countryService.save(country);
            return ResponseEntity.ok(countryUpdate);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("El pais ya existe");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Ocurrio un error");
        }
    }
    
}
