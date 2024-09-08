package com.decrypto.market.Services;
import com.decrypto.market.models.Country;
import com.decrypto.market.models.Market;
import com.decrypto.market.repositories.MarketRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dieal
 */
@Service
public class MarketService {
    
    @Autowired
    private MarketRepository marketRepository;
    
     public Market save(Market market){
         return marketRepository.save(market);
     }
     
     public List<Market> getAllMarkets(){
        return marketRepository.findAll();
    }
     
    public Optional<Market> getMarketById(Long id){
        return marketRepository.findById(id);
     } 
    
      public void deleteById(Long id){
         marketRepository.deleteById(id);
     }
    
    public boolean exist(Long id){
        return marketRepository.existsById(id);
    }
    
}
