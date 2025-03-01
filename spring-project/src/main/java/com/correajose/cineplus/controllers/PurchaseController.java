package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.purchase.PurchaseCreateDTO;
import com.correajose.cineplus.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/purchase")
public class PurchaseController implements IController<PurchaseCreateDTO>{

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, purchaseService.list(), "List of purchases");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(PurchaseCreateDTO body) {
        return buildOkResponse(HttpStatus.CREATED, purchaseService.create(body), "Purchase created successfully");
    }
}
