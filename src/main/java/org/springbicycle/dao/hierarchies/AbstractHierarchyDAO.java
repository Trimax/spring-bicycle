package org.springbicycle.dao.hierarchies;

import org.springbicycle.caches.HierarchyCache;
import org.springbicycle.caches.Node;
import org.springbicycle.dao.AbstractDAO;
import org.springbicycle.dao.Arguments;
import org.springbicycle.entities.hierarchies.AbstractHierarchicalEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.querycontainers.hierarchies.AbstractHierarchyQueriesContainer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractHierarchyDAO<E extends AbstractHierarchicalEntity, F extends AbstractFilter> extends AbstractDAO<E, F> {
    private HierarchyCache<E> cache;

    @PostConstruct
    private void init() {
        cache = new HierarchyCache<>(dataSource.search(getQueriesContainer().getAll, new Arguments(), getEntityMapper()));
    }

    public final List<String> suggest(final String name) {
        return cache.suggest(name);
    }

    public final List<E> getChildren(final String id) {
        return cache.getChildren(id);
    }

    public final List<E> getLeaves(final String id) {
        return cache.collect(id).stream().filter( node -> node != null && node.isLeaf ).collect(Collectors.toList());
    }

    public final Node<E> lookup(final String substring) {
        return cache.lookup(substring);
    }

    public final Node<E> getTree(final List<String> names) {
        return cache.getTree(names);
    }

    public final List<E> collect(final String id) {
        return cache.collect(id);
    }

    public final List<E> getByName(final String name) {
        return cache.getByName(name);
    }

    @Override
    protected abstract AbstractHierarchyQueriesContainer getQueriesContainer();
}