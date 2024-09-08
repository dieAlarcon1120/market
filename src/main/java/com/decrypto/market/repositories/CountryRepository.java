package com.decrypto.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.decrypto.market.models.Country;

/**
 *
 * @author dieal
 */
public interface CountryRepository extends JpaRepository<Country, Long>{
    
}
