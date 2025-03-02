package com.correajose.cineplus.controllers;

import com.correajose.cineplus.dtos.purchase.PurchaseTicketsDTO;
import com.correajose.cineplus.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.correajose.cineplus.utils.ResponseUtils.buildOkResponse;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/find/all")
    public ResponseEntity<?> list() {
        return buildOkResponse(HttpStatus.OK, purchaseService.list(), "List of purchases");
    }

    @GetMapping("/find/reference/{reference}")
    public ResponseEntity<?> findByReference(@PathVariable String reference) {
        return buildOkResponse(HttpStatus.OK, purchaseService.findByReference(reference),"Purchase with reference: "+ reference + " found");
    }

    @GetMapping("/find/reference/{reference}/tickets")
    public ResponseEntity<?> getTicketsByPurchaseId(@PathVariable String reference) {
        return buildOkResponse(HttpStatus.OK, purchaseService.getTicketsByPurchaseId(reference),"Purchase with reference: "+ reference + " found");
    }

    @PostMapping
    public ResponseEntity<?> createPurchaseWithTickets(@Valid @RequestBody PurchaseTicketsDTO body) {
        return buildOkResponse(HttpStatus.OK, purchaseService.createPurchaseWithTickets(body),"Purchase and tickets created successfully");
    }
}
