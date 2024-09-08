package com.decrypto.market.repositories;

import com.decrypto.market.models.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dieal
 */
@Repository
public interface MarketRepository  extends JpaRepository<Market, Long>{
    
}
