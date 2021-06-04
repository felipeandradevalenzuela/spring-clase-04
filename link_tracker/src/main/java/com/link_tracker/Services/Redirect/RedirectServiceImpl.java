package com.link_tracker.Services.Redirect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service("RedirectServiceImpl")
public class RedirectServiceImpl implements IRedirectService{

    @Override
    public ResponseEntity<Object> testUrl(String link) throws IOException {
        URL url = new URL(link);
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return new ResponseEntity<>(url, HttpStatus.OK);

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("URL SIN PAGINA EXISTENTE", HttpStatus.NOT_FOUND);
    }
}
