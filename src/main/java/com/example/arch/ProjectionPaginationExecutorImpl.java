package com.example.arch;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.util.ClassTypeInformation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectionPaginationExecutorImpl<T, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> implements ProjectionPaginationExecutor<T> {
  private final EntityManager em;

  public ProjectionPaginationExecutorImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.em = entityManager;
  }

  @Override
  public <R> Page<R> paginate(Specification<T> specificarion, Class<R> projectionClass, Pageable page) {
    final var domainClass = this.getDomainClass();
    final CriteriaBuilder builder = this.em.getCriteriaBuilder();

    final CriteriaQuery<R> query = builder.createQuery(projectionClass);
    final Root<T> root = query.from(domainClass);

    query.where(specificarion.toPredicate(root, query, builder));
    query.multiselect(select(root, projectionClass, query));

    final TypedQuery<R> q = this.em.createQuery(query);
    q.setFirstResult((int) page.getOffset());
    q.setMaxResults(page.getPageSize());

    final CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
    final Root<T> cRoot = countQuery.from(domainClass);

    if (query.getGroupList() != null && !query.getGroupList().isEmpty()) {
      countQuery.groupBy(query.getGroupList());
    }

    return PageableExecutionUtils.getPage(q.getResultList(), page, () -> {
      countQuery.where(specificarion.toPredicate(cRoot, countQuery, builder));
      countQuery.orderBy(List.of());
      countQuery.select(builder.count(cRoot));
      final TypedQuery<Long> count = this.em.createQuery(countQuery);
      final List<?> totals = count.getResultList();
      return totals.size() == 1 ? (Long) totals.get(0) : (long) totals.size();
    });
  }

  protected <R> List<Selection<?>> select(Root<T> root, Class<R> projectionClass, CriteriaQuery<R> query) {
    final ClassTypeInformation<R> typeInformation = ClassTypeInformation.from(projectionClass);
    PreferredConstructor<?, ?> constructor = PreferredConstructorDiscoverer.discover(typeInformation.getType());
    if (constructor == null) {
      throw new IllegalArgumentException("Constructor not found in class !" + projectionClass.toString());
    }

    var creadoOrder = false;
    final List<Selection<?>> selections = new ArrayList<>();
    for (PreferredConstructor.Parameter parameter : constructor.getParameters()) {
      final String property = parameter.getName();

      PropertyPath path = PropertyPath.from(property, getDomainClass());
      Path expression = findJoin(path.getSegment(), root);
      if (expression == null) {
        expression = root.get(path.getSegment());
      }

      while (path.hasNext()) {
        path = path.next();
        Path joinExpression = null;
        if (expression instanceof From) {
          joinExpression = this.findJoin(path.getSegment(), (From)expression);
        }

        if (joinExpression == null) {
          expression = expression.get(path.getSegment());
        } else {
          expression = joinExpression;
        }
      }
      selections.add(expression);

      if (property.equals("creado")) {
        creadoOrder = true;
      }
    }

    if (creadoOrder && (query.getOrderList() == null || query.getOrderList().isEmpty())) {
      query.orderBy(new OrderImpl(root.get("creado"), false));
    }

    return selections;
  }

  private Path findJoin(String segment, From<?,?> root) {
    return root.getJoins()
               .stream()
               .filter(j -> j.getAttribute().getName().equals(segment))
               .findFirst().orElse(null);
  }

  @Override
  public <R> List<R> list(Specification<T> specificarion, Class<R> projectionClass) {
    final var domainClass = this.getDomainClass();
    final CriteriaBuilder builder = this.em.getCriteriaBuilder();

    final CriteriaQuery<R> query = builder.createQuery(projectionClass);
    final Root<T> root = query.from(domainClass);

    query.where(specificarion.toPredicate(root, query, builder));
    query.multiselect(select(root, projectionClass, query));

    final TypedQuery<R> q = this.em.createQuery(query);
    return q.getResultList();
  }
}
