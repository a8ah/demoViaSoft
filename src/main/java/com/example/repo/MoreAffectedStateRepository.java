package com.example.repo;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.example.entity.ServicioStatusHistory;
import com.example.entity.ServicioStatusHistory_;
import com.example.entity.State_;
import com.example.entity.Status;
import com.example.projection.MoreAffectedState;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Repository
public class MoreAffectedStateRepository {

  @Autowired
  EntityManager em;

  @Autowired
  IServicioStatusHistoryRepository mServicioStatusHistoryRepository;

  public MoreAffectedState moreAffectedState() {
    final var cb = this.em.getCriteriaBuilder();
    final var query = cb.createQuery(MoreAffectedState.class);
    final var root = query.from(ServicioStatusHistory.class);

    query.multiselect(
        root.get(ServicioStatusHistory_.state).get(State_.name),
        cb.count(root.get(ServicioStatusHistory_.state).get(State_.id)));

    query.where(
      cb.or(
        cb.equal(root.get(ServicioStatusHistory_.STATUS), Status.INACTIVE),
        cb.equal(root.get(ServicioStatusHistory_.STATUS), Status.PENDING)
        )
    );

    query.groupBy(
      root.get(ServicioStatusHistory_.state).get(State_.name),
        root.get(ServicioStatusHistory_.state).get(State_.id));

    query.orderBy(new OrderImpl(cb.count(root.get(ServicioStatusHistory_.state).get(State_.id)), false));

    final var q = this.em.createQuery(query);
    var items = q.setMaxResults(1).getSingleResult();
    return items;
  }

}
