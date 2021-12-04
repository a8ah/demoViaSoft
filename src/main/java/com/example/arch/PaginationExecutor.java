package com.example.arch;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.util.ClassTypeInformation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class PaginationExecutor<T, R> {
  private final EntityManager em;
  private final CriteriaBuilder builder;
  private final Class<T> domainClass;
  private final Class<R> retClass;
  private boolean creadoOrder;

  public PaginationExecutor(EntityManager em) {
    this.em = em;
    this.builder = this.em.getCriteriaBuilder();

    final ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    final Type[] params = parameterizedType.getActualTypeArguments();

    this.domainClass = (Class<T>) params[0];
    this.retClass = (Class<R>) params[1];
  }

  protected List<Selection<?>> select(Root<T> root) {
    final ClassTypeInformation<R> typeInformation = ClassTypeInformation.from(retClass);
    PreferredConstructor<?, ?> constructor = PreferredConstructorDiscoverer.discover(typeInformation.getType());
    if (constructor == null) {
      throw new IllegalArgumentException("Constructor not found in class !" + retClass.toString());
    }

    creadoOrder = false;
    final List<Selection<?>> selections = new ArrayList<>();
    for (PreferredConstructor.Parameter parameter : constructor.getParameters()) {
      final String property = parameter.getName();

      PropertyPath path = PropertyPath.from(property, domainClass);
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

    return selections;
  }

  private Path findJoin(String segment, From<?,?> root) {
    return root.getJoins()
               .stream()
               .filter(j -> j.getAttribute().getName().equals(segment))
               .findFirst().orElse(null);
  }

  protected List<Order> orders(Root<T> root) {
    return null;
  }

  protected List<Expression<?>> groupBy(Root<T> root) {
    return null;
  }

  public Page<R> paginate(Specification<T> spec, Pageable page) {
    final CriteriaQuery<R> query = builder.createQuery(retClass);
    final Root<T> root = query.from(domainClass);

    query.where(spec.toPredicate(root, query, builder));
    query.multiselect(select(root));

    final List<Order> orders = orders(root);
    if (orders != null) {
      query.orderBy(orders);
    } else if (creadoOrder) {
      query.orderBy(new OrderImpl(root.get("creado"), false));
    }

    final List<Expression<?>> group = groupBy(root);
    if (group != null) {
      query.groupBy(group);
    }

    final TypedQuery<R> q = this.em.createQuery(query);
    q.setFirstResult((int) page.getOffset());
    q.setMaxResults(page.getPageSize());

    final CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
    final Root<T> cRoot = countQuery.from(domainClass);

    if (group != null) {
      countQuery.groupBy(group);
    }

    return PageableExecutionUtils.getPage(q.getResultList(), page, () -> {
      countQuery.where(spec.toPredicate(cRoot, countQuery, builder));
      countQuery.orderBy(List.of());
      countQuery.select(builder.count(cRoot));
      final TypedQuery<Long> count = this.em.createQuery(countQuery);
      final List<?> totals = count.getResultList();
      return totals.size() == 1 ? (Long) totals.get(0) : (long) totals.size();
    });
  }

  public Page<T> paginateList(List<T> list, Pageable page, int total ){
    return PageableExecutionUtils.getPage(list, page,()->{ return (long) total;});
  }
}
