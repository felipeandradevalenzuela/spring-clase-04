package com.link_tracker.Repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Entities.Link;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Repository("ListadoRepository")
public class ListadoRepositoryImpl implements IListadoRepository{

    private HashMap<Integer, Link> listado = new HashMap<>();
    private final Path newFilePath = Paths.get("src/main/resources/listadoUrl.json");
    private ResponseLinkDTO responsedto  = new ResponseLinkDTO();


    @Override
    public ResponseLinkDTO saveUrl(String url,boolean validation){

        if(!Files.exists(newFilePath)){
            try {
                Files.createFile(newFilePath);
            } catch (IOException e) {
                System.out.println("EL ARCHIVO YA EXISTE \n"+e);
            }
        }

        try {
            writeFile(url,validation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  responsedto;
    }

    private boolean writeFile(String url,boolean validation) throws IOException {
        File file = new File("src/main/resources/listadoUrl.json");
        responsedto = null;
        var objectMapper = new ObjectMapper();

        //Comprueba que el archivo no este vacio

        if (file.length() == 0 ) {
            System.out.println("No errors, and file empty");

        }
        else{
            listado = objectMapper.readValue(file,HashMap.class);
        }



        //CREAMOS EL OBJETO A PARTIR DE LA URL
        int size = listado.size() == 0 ? 0 : listado.size();
        Link link = new Link(size,url,false,validation);
        listado.put(size, link);
        System.out.println(listado);
        //ESCRIBIMOS EL ARCHIVO
        //     try {
        objectMapper.writeValue(file,listado);
        String estado = validation ? "Valido" : "Invalido";
        responsedto = new ResponseLinkDTO(size,true,"Añadido con exito  a la BD Con el id: "+size +" Y con un estado "+estado);
        return true;
     /*   } catch (IOException e) {
            e.printStackTrace();
        }

        responsedto = new ResponseLinkDTO(0,false,"No se logro añadir");
        return false;
        */

    }







}