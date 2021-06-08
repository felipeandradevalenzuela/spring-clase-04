package com.link_tracker.Repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Entities.Link;
import com.link_tracker.HasCode.HasCodeSecurity;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Repository("ListadoRepository")
public class ListadoRepositoryImpl implements IListadoRepository {

    private HashMap<Integer, Link> listado = new HashMap<>();
    private Path newFilePath = Paths.get("src/main/resources/listadoUrl.json");
    private ResponseLinkDTO responsedto = new ResponseLinkDTO();


    //Método que va a permitir guardar una URL con un password
    @Override
    public ResponseLinkDTO saveUrl(String url, boolean validation, String password) throws Exception {

        String pass = HasCodeSecurity.encrypt(password, "ferreira");

        //Verificamos que si el archivo existe o no. Si no existe, se crea.
        try {
            if (!Files.exists(newFilePath)){
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            System.out.println("El Archivo ya existe \n" + e);
        }
        //Invocamos al método writeFIle, que va a escribir en el archivo.
        writeFile(url, validation, pass);
        return responsedto;
    }

    //Método que va a escribir en un archivo .json las urls que se van creando.
    private boolean writeFile(String url, boolean validation, String password) throws Exception {
        //Obtenemos el archivo donde fue creado.
        File file = new File("src/main/resources/listadoUrl.json");
        responsedto = null;

        var objectMapper = new ObjectMapper();

        //Comprobamos que el archivo no este vacío, si no está vacílo, lo leemos.
        if (file.length() == 0) {
            System.out.println("No errors, and file empty");
        } else {
            listado = objectMapper.readValue(file, HashMap.class);
        }

        //Creamos el objeto a partir de la URL. El parámetro validation indica si la URL es correcta o no (devuelve true o false).
        int size = listado.size() == 0 ? 0 : listado.size();
        if (validation == false) {
            throw new NullPointerException("La URL es inválida");
        } else {
            Link link = new Link(size, url, password, validation, 0);
            listado.put(size, link);

            System.out.println(listado);

            //Escribimos en el Archivo.
            objectMapper.writeValue(file, listado);
            String estado = validation ? "Valido" : "Invalido";
            responsedto = new ResponseLinkDTO(size, true, "Añadido con exito  a la BD Con el id: " + size + " Y con un estado " + estado);
            return true;
        }
    }

}