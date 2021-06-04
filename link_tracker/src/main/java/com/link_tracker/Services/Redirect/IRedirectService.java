package com.link_tracker.Services.Redirect;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IRedirectService {

    ResponseEntity<Object> testUrl(String url) throws IOException;
}
