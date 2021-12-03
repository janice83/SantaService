package com.indexzero.santaService.repositories;

import java.util.List;

import com.indexzero.santaService.model.SantaProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SantaProfileRepository extends JpaRepository<SantaProfile, Long> {
    /* Find all available santas */
    @Query(value = "SELECT S.id, S.available, S.info, S.price, S.santa_profile_name FROM SANTA_PROFILE AS S LEFT JOIN CUSTOMER_PROFILE AS C ON S.id = C.id WHERE S.available = TRUE", nativeQuery = true)
    List<SantaProfile> customFindAllAvailableSantas();

    /* Find all available santas by postalcode */
    @Query(value = "SELECT S.id, S.available, S.info, S.price, S.santa_profile_name FROM SANTA_PROFILE AS S LEFT JOIN USER_ACCOUNT AS U ON S.id = U.santa_profile_id WHERE S.available = TRUE AND U.postal_code = :postal_code"
    , nativeQuery = true)
    List<SantaProfile> customFindAllAvailableSantasByPostalCode(@Param("postal_code") String postalCode);
}
