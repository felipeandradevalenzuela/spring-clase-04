package com.link_tracker.Repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Entities.Link;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Repository("ListadoRepository")
public class ListadoRepositoryImpl implements IListadoRepository{

    private HashMap<Integer, Link> listado = new HashMap<>();
    private final Path newFilePath = Paths.get("src/main/resources/listadoUrl.json");


    @Override
    public ResponseLinkDTO saveUrl(String url){

        if(!Files.exists(newFilePath)){
            try {
                Files.createFile(newFilePath);
            } catch (IOException e) {
                System.out.println("EL ARHICOV YA EXISTE \n"+e);
            }
        }

        try {
            writeFile(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean writeFile(String url) throws IOException {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:listadoUrl.json");
        } catch (FileNotFoundException e) {
            System.out.println("EL ARCHIVO NO SE PUDO UBICAR \n"+e);
        }

        var objectMapper = new ObjectMapper();

        //CREAMOS EL OBJETO A PARTIR DE LA URL
        Link link = new Link(1,url,false,true);

        //INTENTAMOS ESCRIBIR EL ARCHIVO
        try {
            objectMapper.writeValue(file,link);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CAMBIAR
        return true;

    }


}
