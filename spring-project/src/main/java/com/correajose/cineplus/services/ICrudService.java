package com.correajose.cineplus.services;

public interface ICrudService<Req,ReqU,Res> extends IService<Req,Res>{
    Res findById(int id);
    Res update(int id, ReqU body);
    Res toggleStatus(int id);
    Res delete(int id);
}
