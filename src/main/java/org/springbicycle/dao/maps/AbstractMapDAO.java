package org.springbicycle.dao.maps;

import org.springbicycle.caches.MapCache;
import org.springbicycle.dao.AbstractDAO;
import org.springbicycle.dao.Arguments;
import org.springbicycle.entities.maps.AbstractMapEntity;
import org.springbicycle.exceptions.CustomException;
import org.springbicycle.filtermappers.AbstractFilterMapper;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.querycontainers.maps.AbstractMapQueriesContainer;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractMapDAO<E extends AbstractMapEntity> extends AbstractDAO<E, AbstractFilter> {
    private MapCache<E> cache;

    @PostConstruct
    private void init() {
        cache = new MapCache<>(dataSource.search(getQueriesContainer().getAll, new Arguments(), getEntityMapper()));
    }

    public final List<String> getByKey(final String key) {
        return cache.getByKey(key);
    }

    @Override
    protected final AbstractFilterMapper<AbstractFilter> getFilterMapper() {
        throw new CustomException("Hierarchies do not use filters");
    }

    @Override
    protected abstract AbstractMapQueriesContainer getQueriesContainer();
}