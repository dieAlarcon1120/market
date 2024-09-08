package com.decrypto.market.Services;

import com.decrypto.market.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.decrypto.market.models.Country;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dieal
 */
@Service
public class CountryService {
    
     @Autowired
    private CountryRepository countryRepository;
     
     public Country save(Country country){
         return countryRepository.save(country);
     }
     
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }
    
    public Optional<Country> getCountryById(Long id){
         return countryRepository.findById(id);
     }
    
    public void deleteById(Long id){
         countryRepository.deleteById(id);
     }
    
    public boolean exist(Long id){
        return countryRepository.existsById(id);
    }
    
}
