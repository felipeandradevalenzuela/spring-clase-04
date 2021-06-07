package com.link_tracker.Repositories;

import com.link_tracker.DTO.ResponseLinkDTO;

public interface IListadoRepository {

    ResponseLinkDTO saveUrl(String url,boolean validation,String password);
}
