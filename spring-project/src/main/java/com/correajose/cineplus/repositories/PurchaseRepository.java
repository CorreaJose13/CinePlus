package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
