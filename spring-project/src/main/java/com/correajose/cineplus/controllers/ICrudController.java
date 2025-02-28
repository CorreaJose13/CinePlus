package com.correajose.cineplus.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICrudController<Req,ReqU> extends IController<Req> {
    ResponseEntity<?> find(@PathVariable int id);
    ResponseEntity<?> update(@PathVariable int id, @RequestBody ReqU body);
    ResponseEntity<?> toggleStatus(@PathVariable int id);
    ResponseEntity<?> delete(@PathVariable int id);
}
