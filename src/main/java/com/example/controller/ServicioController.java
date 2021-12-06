package com.example.controller;

import java.util.List;

import com.example.entity.Servicio;
import com.example.response.SuccessResponse;
import com.example.service.ServicioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@RestController
@RequestMapping("api/base/servicio")
@CrossOrigin({"*"})
public class ServicioController {

  protected final Log logger = LogFactory.getLog(this.getClass());
  
  @Autowired
  ServicioServiceImpl mServicioService;

  @GetMapping
  public List<Servicio> all() {
    return mServicioService.getALl();
  }

  @PostMapping()
  public SuccessResponse crear(@RequestBody Servicio servicio) {
    try { 
      final boolean result = mServicioService.crear(servicio);
      return new SuccessResponse(result, "", servicio.getId());
    } catch (Exception ex) {
      if (logger.isErrorEnabled()) {
        logger.error(ex);
        ;
      }

      return new SuccessResponse(false, ex.getMessage());
    }
  }

}
