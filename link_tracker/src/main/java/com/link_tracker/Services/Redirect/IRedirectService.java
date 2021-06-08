package com.link_tracker.Services.Redirect;

import com.link_tracker.Entities.Link;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IRedirectService {

    Link getLinkFromDataBase(int id);
    void sumarContador(int id);
    Link modifyValidation(int id) throws Exception;
    Link passwordCompare(int linkId, String password);
}
