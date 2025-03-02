package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Optional<Purchase> findByReference(String reference);
}
