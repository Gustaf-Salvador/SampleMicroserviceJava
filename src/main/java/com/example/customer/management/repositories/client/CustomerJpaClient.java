package com.example.customer.management.repositories.client;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.customer.management.repositories.model.CustomerDSO;

@Repository
public interface CustomerJpaClient extends JpaRepository<CustomerDSO, UUID> {

    @Query("SELECT c FROM CustomerDSO c WHERE c.isActive = :isActive")
    List<CustomerDSO> listAllByIsActive(@Param("isActive") boolean isActive);


    @Query("SELECT c FROM CustomerDSO c WHERE c.id = :id AND c.isActive = :isActive")
    CustomerDSO getByIdAndIsActive(@Param("id") UUID id, @Param("isActive") boolean isActive);

}
