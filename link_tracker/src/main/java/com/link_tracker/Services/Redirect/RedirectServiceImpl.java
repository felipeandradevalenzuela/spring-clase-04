package com.link_tracker.Services.Redirect;

import com.fasterxml.jackson.core.type.TypeReference;
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
        if(dataBase.containsKey(id)){
            return dataBase.get(id);
        }
        return link;
    }

    @Override
    public void sumarContador(int id){
        HashMap<Integer ,Link> dataBase = getDataBase();
        int temp1 = dataBase.get(id).getMetrics() +1;
        dataBase.get(id).setMetrics(temp1);
        File file = new File("src/main/resources/listadoUrl.json");
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file,dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyValidation(int id){
        HashMap<Integer ,Link> dataBase = getDataBase();
        dataBase.get(id).setValidation(false);
        File file = new File("src/main/resources/listadoUrl.json");
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file,dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public HashMap<Integer, Link> getDataBase(){
        HashMap<Integer, Link> listado = new HashMap<>();
        File file = new File("src/main/resources/listadoUrl.json");
        var objectMapper = new ObjectMapper();
        TypeReference<HashMap<Integer,Link>> typeRef = new TypeReference<HashMap<Integer,Link>>() {};
        try {
            listado = objectMapper.readValue(file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listado;
    }

}
