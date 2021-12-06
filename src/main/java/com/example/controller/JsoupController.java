package com.example.controller;

import java.io.IOException;

import com.example.service.ServicioStatusHistoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@RestController
@RequestMapping("api/base/consult")
@CrossOrigin({"*"})
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
