package com.example.repo;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.example.entity.ServicioStatusHistory;
import com.example.entity.ServicioStatusHistory_;
import com.example.entity.Servicio_;
import com.example.entity.State_;
import com.example.entity.Status;
import com.example.projection.MoreAffectedState;
import com.example.projection.ServiceActualState;


@Repository
public class StateActualServiceStatusRepository {

  @Autowired
  EntityManager em;

  @Autowired
  IServicioStatusHistoryRepository mServicioStatusHistoryRepository;

  public List<ServiceActualState> ActualStatus(UUID stateID, int maxRow, LocalDateTime inicialDate, LocalDateTime finalDate) {
    final var cb = this.em.getCriteriaBuilder();
    final var query = cb.createQuery(ServiceActualState.class);
    final var root = query.from(ServicioStatusHistory.class);

    query.multiselect(
      root.get(ServicioStatusHistory_.service).get(Servicio_.NAME),
        root.get(ServicioStatusHistory_.STATUS));

        if (inicialDate != null && finalDate != null) {
          query.where(
            cb.and(
              cb.equal(root.get(ServicioStatusHistory_.state).get(State_.id), stateID),
              cb.between(root.get(ServicioStatusHistory_.creado), inicialDate, finalDate)
              )              
          );
            // cb.equal(root.get(ServicioStatusHistory_.state).get(State_.id), stateID));
            // cb.between(root.get(ServicioStatusHistory_.creado), inicialDate, finalDate);
       } else{
    query.where(cb.equal(root.get(ServicioStatusHistory_.state).get(State_.id), stateID));
  }

    query.orderBy(new OrderImpl(root.get(ServicioStatusHistory_.creado), false));

    final var q = this.em.createQuery(query);
    var items= q.getResultList();
    if(maxRow>0){
      items = q.setMaxResults(8).getResultList();
    }
    
    return items;
  }

}
