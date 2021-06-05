package com.link_tracker.Services.Redirect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link_tracker.Entities.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service("RedirectServiceImpl")
public class RedirectServiceImpl implements IRedirectService {

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

    @Override
    public Link getLinkFromDataBase(int id){
        HashMap<Integer ,Link> dataBase = getDataBase();
        Link link = new Link();

        if(dataBase.containsKey(String.valueOf(id))){
            var temp = dataBase.get(id);
            link = temp;
        }

     return link;
    }


    public HashMap<Integer, Link> getDataBase(){
        HashMap<Integer, Link> listado = new HashMap<>();
        File file = new File("src/main/resources/listadoUrl.json");
        var objectMapper = new ObjectMapper();
        try {
            listado = objectMapper.readValue(file,HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
         return listado;
    }





}
