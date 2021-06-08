package com.link_tracker.Controllers;

import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Services.Save.ISaveService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SaveController {
    
    @Autowired
    @Qualifier("SaveServiceImpl")
    private ISaveService saveService;

    //Método que va a permitir guardar una URL con contraseña.
    @PostMapping("/saveUrl")
    public ResponseEntity<ResponseLinkDTO> saveUrl(@RequestBody String url, @RequestParam(defaultValue = "") String password )
    throws Exception {
        ResponseLinkDTO resp = saveService.saveUrl(url,password);
        if(resp != null){
            return new ResponseEntity<ResponseLinkDTO>(resp,HttpStatus.OK);
        }
        return  new ResponseEntity<ResponseLinkDTO>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
