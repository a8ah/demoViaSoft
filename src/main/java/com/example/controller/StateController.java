package com.example.controller;

import java.util.List;
import com.example.entity.State;
import com.example.response.SuccessResponse;
import com.example.service.StateServiceImpl;

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
@RequestMapping("api/base/state")
@CrossOrigin({"*"})
public class StateController {

  protected final Log logger = LogFactory.getLog(this.getClass());

  @Autowired
  StateServiceImpl mService;
  // private final ServicioService mServicioService;

  @GetMapping
  public List<State> all() {
    return mService.getALl();
  }

  @PostMapping()
  public SuccessResponse crear(@RequestBody State state) {
    try { 
      final boolean result = mService.crear(state);
      return new SuccessResponse(result, "", state.getId());
    } catch (Exception ex) {
      if (logger.isErrorEnabled()) {
        logger.error(ex);
        ;
      }

      return new SuccessResponse(false, ex.getMessage());
    }
  }

}
