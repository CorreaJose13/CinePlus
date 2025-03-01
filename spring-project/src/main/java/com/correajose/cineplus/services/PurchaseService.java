package com.correajose.cineplus.services;

import com.correajose.cineplus.dtos.purchase.PurchaseCreateDTO;
import com.correajose.cineplus.dtos.purchase.PurchaseResponseDTO;
import com.correajose.cineplus.models.purchase.Purchase;
import com.correajose.cineplus.repositories.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseService implements IService<PurchaseCreateDTO, PurchaseResponseDTO>{

    @Autowired
    private PurchaseRepository purchaseRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PurchaseResponseDTO> list() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(u -> modelMapper.map(u, PurchaseResponseDTO.class)).toList();
    }

    public PurchaseResponseDTO create(PurchaseCreateDTO body) {
        Purchase purchase = modelMapper.map(body, Purchase.class);
        purchase.setReference(UUID.randomUUID().toString());
        return modelMapper.map(purchaseRepository.save(purchase), PurchaseResponseDTO.class);
    }
}
