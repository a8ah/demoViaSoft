package com.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.entity.Servicio;
import com.example.entity.ServicioStatusHistory;
import com.example.entity.State;
import com.example.entity.Status;
import com.example.repo.IServicioRepository;
import com.example.repo.IStateRepository;
import com.example.request.ServicioRequest;
import com.example.response.SuccessResponse;
import com.example.service.ServicioServiceImpl;
import com.example.service.ServicioStatusHistoryServiceImpl;
import com.example.service.StateServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("api/base/consult")
public class JsoupController {

  protected final Log logger = LogFactory.getLog(this.getClass());

  @Autowired
  ServicioStatusHistoryServiceImpl mServicioStatusHistoryServiceImpl;

  @GetMapping
  public void consult() {
    Document doc;
    try {
      // Obtening Data from http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx
      doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
          mServicioStatusHistoryServiceImpl.consult(doc);      

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
