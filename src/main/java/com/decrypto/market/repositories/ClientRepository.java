package com.decrypto.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.decrypto.market.models.Client;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author dieal
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    
        @Query(value = "SELECT c.name AS countryName, m.code AS marketCode, COUNT(cm.id_client) AS clientCount " +
                   "FROM clients_markets cm " +
                   "JOIN markets m ON cm.id_market = m.id " +
                   "JOIN countries c ON m.id_country = c.id " +
                   "GROUP BY c.name, m.id", nativeQuery = true)
    List<Object[]> countClientsByCountryAndMarket();
    
}
