package com.link_tracker.Controllers;

import com.link_tracker.DTO.LinkDTO;
import com.link_tracker.DTO.ResponseMetricsDTO;
import com.link_tracker.Entities.Link;
import com.link_tracker.LinkTrackerApplication;
import com.link_tracker.Services.Redirect.IRedirectService;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class RedirectController {

    @Autowired
    @Qualifier("RedirectServiceImpl")
    private IRedirectService redirectService;

    @GetMapping("/link/{linkId}")
    public ModelAndView redirectUrl(@PathVariable int linkId , @RequestParam(defaultValue = "") String password) throws IOException {

        Link resp = redirectService.getLinkFromDataBase(linkId);
        RedirectView r = new RedirectView();
        if(!resp.isValidation() || resp.getUrl() ==  null || !(resp.getPassword().equals(password))){
            r.setStatusCode(HttpStatus.NOT_FOUND);
            r.setUrl("");
        }
        else{
            r.setUrl(resp.getUrl());
            redirectService.sumarContador(linkId);
        }
        return new ModelAndView(r);
    }

    @GetMapping("/metrics/{linkID}")
    public ResponseEntity<Object> getMetrics(@PathVariable int linkID) throws IOException{
        Link resp = redirectService.getLinkFromDataBase(linkID);
        ResponseMetricsDTO response = new ResponseMetricsDTO(resp.getUrl(),resp.getMetrics());
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
    @PostMapping("/invalidate/{linkID}")
    public ResponseEntity<Object> invalidateLink(@PathVariable int linkID){
        Link resp = redirectService.getLinkFromDataBase(linkID);
        redirectService.modifyValidation(linkID);
        return new ResponseEntity<>("El url "+resp.getUrl()+" Fue invalidado",HttpStatus.OK);
    }


}