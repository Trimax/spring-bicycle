package org.springbicycle.dao.collections;

import org.springbicycle.dao.AbstractDAO;
import org.springbicycle.entities.collections.AbstractCollectionEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.querycontainers.collections.AbstractCollectionQueriesContainer;

import java.util.List;

public abstract class AbstractCollectionDAO<E extends AbstractCollectionEntity, F extends AbstractFilter> extends AbstractDAO<E, F> {
    public final List<E> search(final F filter) {
        return dataSource.search(getSearchQuery(filter), getFilterMapper().mapArguments(filter), getEntityMapper());
    }

    private String getSearchQuery(final F filter) {
        return getSearchQueryBuilder().getSearchQuery(getTable(), getFilterMapper().mapArguments(filter), getFilterMapper().mapExpression(filter), filter.sorting, filter.limitation).build();
    }

    public final Long count(final F filter) {
        return dataSource.getObject(getCountQuery(filter), getFilterMapper().mapArguments(filter), Long.class);
    }

    private String getCountQuery(final F filter) {
        return getSearchQueryBuilder().getCountQuery(getTable(), getFilterMapper().mapArguments(filter), getFilterMapper().mapExpression(filter)).build();
    }

    @Override
    protected abstract AbstractCollectionQueriesContainer getQueriesContainer();
}