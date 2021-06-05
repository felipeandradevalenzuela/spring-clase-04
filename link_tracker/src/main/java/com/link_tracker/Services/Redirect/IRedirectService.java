package com.link_tracker.Services.Redirect;

import com.link_tracker.Entities.Link;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IRedirectService {

    ResponseEntity<Object> testUrl(String url) throws IOException;
    Link getLinkFromDataBase(int id );
}
