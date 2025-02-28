package com.correajose.cineplus.services;

import java.util.List;

public interface IService<Req,Res> {
    List<Res> list();
    Res create( Req body) ;
}
