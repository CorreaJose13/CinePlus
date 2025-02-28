package com.correajose.cineplus.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IController<Req> {
    ResponseEntity<?> list();
    ResponseEntity<?> create(@RequestBody Req body);
}
