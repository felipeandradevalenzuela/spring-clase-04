package com.link_tracker.Repositories;

import com.link_tracker.DTO.ResponseLinkDTO;

import java.io.IOException;

public interface IListadoRepository {

    ResponseLinkDTO saveUrl(String url,boolean validation,String password) throws Exception;
}
