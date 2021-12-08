package com.example.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.entity.ServicioStatusHistory;
import com.example.projection.MoreAffectedState;
import com.example.projection.StateActualServicioStatus;
import com.example.service.ServicioStatusHistoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@RestController
@RequestMapping("api/base/history")
@CrossOrigin({"*"})
public class ServicioStatusHistoryController {

  protected final Log logger = LogFactory.getLog(this.getClass());

  @Autowired
  ServicioStatusHistoryServiceImpl mService;

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
    var pageNumber=1;
    var pageSize=10;
    return mService.actualServiceStatus(pageNumber,pageSize);
  }

  @GetMapping("actual-status/{id}")
  public Object actualServiceStatusByState(@PathVariable UUID id) { 
       var pageNumber=1;
       var pageSize=10;
       
    return mService.actualServiceStatusByState(pageNumber,pageSize,id);
  }

  @GetMapping("status-by-time-range")
  public Object serviceStatusByTimeRange(@RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate,
      @RequestParam(required = true) int pageNumber,
      @RequestParam(required = true) int pageSize) {  

    // convert String to LocalDate
    LocalDate inicialDate = LocalDate.parse(startDate);
    LocalDate finalDate = LocalDate.parse(endDate);
    if(pageNumber<=0 || pageSize<=0 ){
      pageNumber=1;
      pageSize=10;
    }


    return mService.serviceStatusByTimeRange(pageNumber,pageSize,inicialDate, finalDate);
  }

}
