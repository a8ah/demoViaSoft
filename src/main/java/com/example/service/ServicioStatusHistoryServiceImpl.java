package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.example.entity.Servicio;
import com.example.entity.ServicioStatusHistory;
import com.example.entity.State;
import com.example.entity.Status;
import com.example.projection.MoreAffectedState;
import com.example.projection.ServiceActualState;
import com.example.projection.StateActualServicioStatus;
import com.example.repo.IServicioStatusHistoryRepository;
import com.example.repo.IStateRepository;
import com.example.repo.MoreAffectedStateRepository;
import com.example.repo.StateActualServiceStatusRepository;
import com.example.specification.MoreAffectedStatePeriodSpecification;
import com.example.repo.IServicioRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class ServicioStatusHistoryServiceImpl implements IServicioStarusHistoryService {

  protected final Log logger = LogFactory.getLog(this.getClass());
  final int count=8;

  @Autowired
  IServicioStatusHistoryRepository mRepository;

  @Autowired
  IStateRepository mStateRepository;

  @Autowired
  IServicioRepository mServicioRepository;

  @Autowired
  MoreAffectedStateRepository mMoreAffectedStateRepository;

  @Autowired
  StateActualServiceStatusRepository mStateActualServiceStatusRepository;

  @Autowired
  ServicioServiceImpl mServicioServiceImpl;

  @Autowired
  StateServiceImpl mStateServiceImpl;

  @Override
  public List<ServicioStatusHistory> getALl() {
    return mRepository.findAll();
  }

  @Override
  public Optional<ServicioStatusHistory> findById(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public boolean crear(ServicioStatusHistory service) {

    if (service.getId() == null) {
      service.setEliminado(false);
      service.setCreado(LocalDateTime.now());
    } else {
      service.setModificado(LocalDateTime.now());
    }
    mRepository.save(service);
    return true;
  }

  @Override
  public String delete(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String update(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  public void consult(Document doc) {

    final String green = "verde";
    final String yellow = "amarela";
    final String red = "vermelho";
    final String notAvailable = "-";
    final String notPresent = "";

    final Map<String, State> stateMap = new HashMap<>();
    mStateRepository.findAll().forEach(f -> stateMap.put(f.getName(), f));

    final Map<String, Servicio> servicioMap = new HashMap<>();
    mServicioRepository.findAll().forEach(f -> servicioMap.put(f.getName(), f));

    // Obtening Data from http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx
    String title = doc.title();

    Element serviceTable = doc.getElementById("ctl00_ContentPlaceHolder1_gdvDisponibilidade2");
    Elements trElements = serviceTable.getElementsByTag("tr");

    // Recorriendo <tr>
    for (int i = 1; i < trElements.size(); i++) {
      Element row = trElements.get(i);
      Elements col = row.select("td");

      // Recorriendo <td>
      for (int j = 1; j < col.size(); j++) {
        // Creating ServicioStatusHistory entity
        ServicioStatusHistory entHistory = new ServicioStatusHistory();

        // Validating State
        System.out.println("Estado is: " + col.get(0).childNode(0));
        final State state = stateMap.get(col.get(0).childNode(0).toString());
        if (state == null) {
          // // Crear el estado
          // State newState= new State();
          // newState.setName(col.get(0).childNode(0).toString());
          // mStateServiceImpl.crear(newState);
          // stateMap.put(col.get(0).childNode(0).toString().toString(),newState);
          // entHistory.setState(newState);
        } else {
          entHistory.setState(state);
        }

        // Validating Service
        System.out.println("Servicio is: " + trElements.get(0).childNode(j + 1).childNodes().get(0));
        final var servicio = servicioMap.get(trElements.get(0).childNode(j + 1).childNodes().get(0).toString());
        if (servicio == null) {
          // Crear el nuevo servicio
          // Servicio newServicio = new Servicio();
          // newServicio.setName(trElements.get(0).childNode(j + 1).childNodes().get(0).toString());
          // mServicioServiceImpl.crear(newServicio);
          // servicioMap.put(trElements.get(0).childNode(j + 1).childNodes().get(0).toString(), newServicio);
          // entHistory.setService(newServicio);
        } else {
          entHistory.setService(servicio);
        }

        // Validating Service Status
        Element cel = col.get(j);
        final var actualStatus = cel.childNode(0).attributes().toString();
        System.out.println("Status is: " + cel.childNode(0).attributes().toString());

        if (actualStatus.contains(green)) {
          // Status Active
          entHistory.setStatus(Status.ACTIVE);
        } else if (actualStatus.contains(yellow)) {
          // Status Pending
          entHistory.setStatus(Status.PENDING);
        } else if (actualStatus.contains(red)) {
          // Status Down
          entHistory.setStatus(Status.INACTIVE);
        } else if (actualStatus.contains(notAvailable)) {
          // Status NOt Available
          entHistory.setStatus(Status.NOT_AVAILABLE);
        } else {
          // Starus Not Present
          entHistory.setStatus(Status.NOTPRESENT);
        }
        this.crear(entHistory);
      }
    }
    System.out.println("End");
  }

  public MoreAffectedState moreAffectedState(){
    return mMoreAffectedStateRepository.moreAffectedState();
  }

  public StateActualServicioStatus actualServiceStatusByState(UUID id){ 
    final State state = mStateRepository.findById(id).orElse(null);
    try {
      StateActualServicioStatus actualServiceStatus = new StateActualServicioStatus(state.getName(),mStateActualServiceStatusRepository.ActualStatus(id,8,null,null));
      return actualServiceStatus;
    } catch (Exception ex) {
      logger.error(ex);
      throw new IllegalArgumentException("No se encontro el Estado con ID "+ id);
    }
  }

  public List<StateActualServicioStatus> actualServiceStatus(){
    List<StateActualServicioStatus> stateList = new ArrayList<>();

    for (State state : mStateRepository.findAll()) {
      List<ServiceActualState> list = mStateActualServiceStatusRepository.ActualStatus(state.getId(),8,null,null);
      StateActualServicioStatus actualServiceStatus = new StateActualServicioStatus(state.getName(),list);
      stateList.add(actualServiceStatus);
    }
    
    return stateList;
  }

  public List<StateActualServicioStatus> serviceStatusByTimeRange(LocalDate inicialDate,LocalDate finallDate){
    List<StateActualServicioStatus> stateList = new ArrayList<>();

    final var fechaInicio = inicialDate != null ? inicialDate.atStartOfDay() : null;
    final var fechaFin = finallDate != null ? finallDate.atTime(LocalTime.MAX) : null;

    final var maxResult= -1;
    for (State state : mStateRepository.findAll()) {
      List<ServiceActualState> list = mStateActualServiceStatusRepository.ActualStatus(state.getId(),maxResult,fechaInicio,fechaFin);
      StateActualServicioStatus actualServiceStatus = new StateActualServicioStatus(state.getName(),list);
      stateList.add(actualServiceStatus);
    }
    
    return stateList;
  }

}