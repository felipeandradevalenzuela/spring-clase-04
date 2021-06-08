package com.link_tracker.Services.Redirect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.link_tracker.Entities.Link;
import com.link_tracker.HasCode.HasCodeSecurity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


@Service("RedirectServiceImpl")
public class RedirectServiceImpl implements IRedirectService {

    private File file = new File("src/main/resources/listadoUrl.json");
    private final String SECRET = "ferreira";

    @Override
    public Link passwordCompare(int id, String password){
        Link resp= getLinkFromDataBase(id);

        //Desencriptamos la password que nos viene desde el archivo.
        String pass = HasCodeSecurity.decrypt(resp.getPassword(),SECRET);
        resp.setPassword(pass);
        if(!resp.isValidation() || resp.getUrl() ==  null ||
                !(pass.equals(password))){
            resp.setValidation(false);
        }
        if(!pass.equals(password)){
            throw new NullPointerException("La password ingresada no es válida");
        }

        resp.setPassword(HasCodeSecurity.encrypt(password,SECRET));
        return resp;
    }

    //Método que retorna un LINK específico de los contenidos en el archivo.
    @Override
    public Link getLinkFromDataBase(int id) throws RuntimeException{
        HashMap<Integer ,Link> dataBase = getDataBase();
        Link link;

        //Consultamos si tenemos el Link en el HashMap, en caso de tenerlo lo retornamos, si no existe lanzamos una exepción.
        if(dataBase.containsKey(id)){
            link = dataBase.get(id);
            return link;
        }
        else{
            throw new RuntimeException("La URL con el ID " + id + " no existe.");
        }
    }


    //Método que va a sumar la cantidad de redirecciones que se realizan para 1 linkId.
    @Override
    public void sumarContador(int id){
        HashMap<Integer ,Link> dataBase = getDataBase();
        int temp1 = dataBase.get(id).getMetrics() +1;
        dataBase.get(id).setMetrics(temp1);

        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(this.file,dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que permite modificar el estado de un link.
    @Override
    public Link modifyValidation(int id) throws Exception {
        HashMap<Integer ,Link> dataBase = getDataBase();
        Link link = null;
        link = dataBase.get(id);
        if(link == null){
            throw new Exception("No existe el Link con el ID " + id);
        }else {
            link.setValidation(false);
            var objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(this.file, dataBase);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return link;
        }
    }


    //Método que va a obtener la información de los links que tenemos en el HashMap
    public HashMap<Integer, Link> getDataBase(){
        HashMap<Integer, Link> listado = new HashMap<>();
        var objectMapper = new ObjectMapper();
        TypeReference<HashMap<Integer,Link>> typeRef = new TypeReference<HashMap<Integer,Link>>() {};
        try {
            listado = objectMapper.readValue(this.file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listado;
    }

}
