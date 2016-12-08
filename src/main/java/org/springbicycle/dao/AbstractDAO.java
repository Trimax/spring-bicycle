package org.springbicycle.dao;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.datasources.DBDataSource;
import org.springbicycle.entities.AbstractEntity;
import org.springbicycle.entitymappers.AbstractEntityMapper;
import org.springbicycle.filtermappers.AbstractFilterMapper;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.querybuilders.AbstractSearchQueryBuilder;
import org.springbicycle.querycontainers.AbstractQueriesContainer;
import org.springbicycle.tables.AbstractTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity, F extends AbstractFilter> {
    @Autowired
    protected DBDataSource dataSource;

    public final E get(final String id) {
        return dataSource.get(getQueriesContainer().get, new Arguments(getTable().getPrimaryColumn(), id), getEntityMapper());
    }

    public final Boolean create(final E entity) {
        return dataSource.insert(getQueriesContainer().create, getEntityMapper().mapArguments(entity));
    }

    public final Boolean update(final E entity) {
        return dataSource.update(getQueriesContainer().update, getEntityMapper().mapArguments(entity));
    }

    public final Boolean delete(final String id) {
        return dataSource.update(getQueriesContainer().delete, new Arguments(getTable().getPrimaryColumn(), id));
    }

    public final List<String> getColumnValues(final F filter, final AbstractColumn column) {
        return dataSource.getList(getColumnValuesQuery(filter, column), getFilterMapper().mapArguments(filter), String.class);
    }

    private String getColumnValuesQuery(final F filter, final AbstractColumn column) {
        return getSearchQueryBuilder().getColumnValues(column, getFilterMapper().mapArguments(filter), getFilterMapper().mapExpression(filter), filter.sorting, filter.limitation).build();
    }

    protected abstract AbstractQueriesContainer getQueriesContainer();
    protected abstract AbstractEntityMapper<E> getEntityMapper();
    protected abstract AbstractFilterMapper<F> getFilterMapper();
    protected abstract AbstractTable getTable();
    protected abstract AbstractSearchQueryBuilder getSearchQueryBuilder();
}