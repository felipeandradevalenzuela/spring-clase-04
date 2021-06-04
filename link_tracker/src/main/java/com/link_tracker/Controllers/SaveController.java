package com.link_tracker.Controllers;

import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.Services.Save.ISaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveController {
    
    @Autowired
    @Qualifier("SaveServiceImpl")
    private ISaveService saveService;

    @PostMapping("/saveUrl")
    public ResponseEntity<Object> saveUrl(@RequestBody String url){
        ResponseLinkDTO resp = saveService.saveUrl(url);
        if(resp.isStatus()){
            return new ResponseEntity<>("Su url se guardo con el id: "+resp.getId(), HttpStatus.OK);
        };
        return new ResponseEntity<>(resp.getDescription(), HttpStatus.OK);
    }

}
