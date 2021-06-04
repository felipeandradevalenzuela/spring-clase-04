package com.link_tracker.Services.Save;

import com.link_tracker.DTO.ResponseLinkDTO;

public interface ISaveService {

    ResponseLinkDTO saveUrl(String url);
}
