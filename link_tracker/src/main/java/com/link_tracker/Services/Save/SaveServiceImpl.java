package com.link_tracker.Services.Save;

import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Repositories.IListadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("SaveServiceImpl")
public class SaveServiceImpl implements ISaveService {


    @Autowired
    @Qualifier("ListadoRepository")
    private IListadoRepository listado;


    //Invocamos al repository para almacenar la url.
    @Override
    public ResponseLinkDTO saveUrl(String url,String password) throws Exception {
        return listado.saveUrl(url, isValidUrl(url),password);
    }

    //Expression regular que controla que la URL tenga formato corredcto.
    public boolean isValidUrl(String url){
        Pattern pattern = Pattern.compile( "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();


    }

}
