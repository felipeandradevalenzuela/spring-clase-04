package com.link_tracker.Controllers;

import com.link_tracker.Services.Redirect.IRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RedirectController {

    @Autowired
    @Qualifier("RedirectServiceImpl")
    private IRedirectService redirectService;

    @GetMapping("/link/{linkId}")
    public ResponseEntity<Object> redirectUrl(@PathVariable int linkId) throws IOException {

        return redirectService.testUrl("https://www.google.com");
    }
}
