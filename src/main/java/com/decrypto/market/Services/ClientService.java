
package com.decrypto.market.Services;

import com.decrypto.market.dto.ClientsPercertageResponseDto;
import com.decrypto.market.models.Client;
import com.decrypto.market.repositories.ClientRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dieal
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
     public List<Client> getAllsClients(){
        return clientRepository.findAll();
    }
    
        public Optional<Client> getClientById(Long id){  
        return clientRepository.findById(id);          
    }
        
       public Client save(Client client){
        return clientRepository.save(client);
    }
       
        public void delete(Long id){
        clientRepository.deleteById(id);
    }
        
        public boolean exist(Long id){
        return clientRepository.existsById(id);
    }
        
     public List<ClientsPercertageResponseDto> getClientsCountByMarketAndCountry() {
        List<ClientsPercertageResponseDto> clientsPercertageResponseDtoList = new ArrayList<>();
        List<Object[]> results = clientRepository.countClientsByCountryAndMarket();
        Map<String, Map<String, Long>> countryMarketMap = new HashMap<>();

        for (Object[] result : results) {
            String countryName = (String) result[0];
            String marketCode = (String) result[1];
            Long clientCount = (Long) result[2];

            countryMarketMap.computeIfAbsent(countryName, k -> new HashMap<>())
                   .put(marketCode, clientCount);
        }
        
        
        for (Map.Entry<String, Map<String, Long>> countryEntry : countryMarketMap.entrySet()) {
            Map<String, Long> percentageMap = new HashMap<>();
            String country = countryEntry.getKey();
            Map<String, Long> markets = countryEntry.getValue();
            
            ClientsPercertageResponseDto responseDto = new ClientsPercertageResponseDto();
            responseDto.setCountry(country);
            
            for (Map.Entry<String, Long> marketEntry : markets.entrySet()) {
                String marketName = marketEntry.getKey();
                Long percentage = marketEntry.getValue();
                 percentageMap.put(marketName, percentage);
                 responseDto.setMarket(percentageMap);
            }
            clientsPercertageResponseDtoList.add(responseDto);
        }
        return clientsPercertageResponseDtoList;
    }

}
