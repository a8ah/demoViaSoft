package com.example.controller;

import java.util.List;
import java.util.UUID;

import com.example.entity.Servicio;
import com.example.entity.ServicioStatusHistory;
import com.example.entity.State;
import com.example.projection.MoreAffectedState;
import com.example.projection.StateActualServicioStatus;
import com.example.request.ServicioRequest;
import com.example.response.SuccessResponse;
import com.example.service.ServicioServiceImpl;
import com.example.service.ServicioStatusHistoryServiceImpl;
import com.example.service.StateServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("api/base/history")
public class ServicioStatusHistoryController {

  protected final Log logger = LogFactory.getLog(this.getClass());
  
  @Autowired
  ServicioStatusHistoryServiceImpl mService;
  // private final ServicioService mServicioService;

  @GetMapping
  public List<ServicioStatusHistory> all() {
    return mService.getALl();
  }

  @GetMapping("more-affected-state")
  public MoreAffectedState moreAffectedState() {
    return mService.moreAffectedState();
  }

  @GetMapping("actual-status-by-state")
  public List<StateActualServicioStatus> actualServiceStatus() {
    return mService.actualServiceStatus();
  }

  @GetMapping("actual-status/{id}")
  public Object actualServiceStatusByState(@PathVariable UUID id) {
      return mService.actualServiceStatusByState(id);
  }
  
}
