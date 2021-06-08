package com.link_tracker.Controllers;
import com.link_tracker.DTO.ResponseLinkDTO;
import com.link_tracker.DTO.ResponseMetricsDTO;
import com.link_tracker.Entities.Link;
import com.link_tracker.HasCode.HasCodeSecurity;
import com.link_tracker.Services.Redirect.IRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

    @Autowired
    @Qualifier("RedirectServiceImpl")
    private IRedirectService redirectService;

    //Método POST para enviar la contraseña mediante el Body.
    @PostMapping("/link/{linkId}")
    public ModelAndView redirectUrlPost(@PathVariable int linkId , @RequestBody String password) throws RuntimeException {
        //Buscamos en nuestro archivo el linkID especificado.
        Link resp = redirectService.passwordCompare(linkId,password);
        RedirectView r = new RedirectView();
        //Controlamos el link, en caso de ser un link inválido, enviamos httpstatus not found, sino lo retornamos al
        // link que se esta buscando.
        if(!resp.isValidation()){
            r.setStatusCode(HttpStatus.NOT_FOUND);
            r.setUrl("");
        }
        else{
            r.setUrl(resp.getUrl());
            //Método que va a sumar las redirecciones de la URL.
            redirectService.sumarContador(linkId);
        }
        return new ModelAndView(r);
    }

    //Método POST para enviar la contraseña mediante el QueryParam.
    @GetMapping("/link/{linkId}")
    public ModelAndView redirectUrl(@PathVariable int linkId , @RequestParam(defaultValue = "") String password) throws RuntimeException {
        //Buscamos en nuestro archivo el linkID especificado.
        Link resp = redirectService.passwordCompare(linkId,password);
        RedirectView r = new RedirectView();
        //Controlamos el link, en caso de ser un link inválido, enviamos httpstatus not found, sino lo retornamos al
        // link que se esta buscando.
        if(!resp.isValidation()){
            r.setStatusCode(HttpStatus.NOT_FOUND);
            r.setUrl("");
        }
        else{
            r.setUrl(resp.getUrl());
            //Método que va a sumar las redirecciones de la URL.
            redirectService.sumarContador(linkId);
        }
        return new ModelAndView(r);
    }

    //Método que va a devovler la cantidad de redirecciones para 1 url en especifico.
    @GetMapping("/metrics/{linkID}")
    public ResponseEntity<Object> getMetrics(@PathVariable int linkID,String password){
        Link resp = redirectService.getLinkFromDataBase(linkID);
        ResponseMetricsDTO response = new ResponseMetricsDTO(resp.getUrl(),resp.getMetrics());
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }


    //Método que va a invalidar una URL en específico
    @PostMapping("/invalidate/{linkID}")
    public ResponseEntity<Link> invalidateLink(@PathVariable int linkID) throws Exception {
        Link resp = redirectService.modifyValidation(linkID);
        return new ResponseEntity<Link>(resp,HttpStatus.OK);
    }


}