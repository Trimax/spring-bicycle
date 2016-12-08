package org.springbicycle.services.hierarchies;

import org.springbicycle.caches.Node;
import org.springbicycle.dao.hierarchies.AbstractHierarchyDAO;
import org.springbicycle.entities.hierarchies.AbstractHierarchicalEntity;
import org.springbicycle.exceptions.CustomException;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.services.AbstractService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractHierarchyService<E extends AbstractHierarchicalEntity, F extends AbstractFilter> extends AbstractService<E, F> {
    public final List<String> suggest(final String name, final Integer limit) {
        if (limit == null)
            return getDAO().suggest(name);

        if (limit <= 0)
            throw new CustomException("Limit must be a positive number");

        return getDAO().suggest(name).stream().limit(limit).collect(Collectors.toList());
    }

    public final List<E> getChildren(final String id) {
        return getDAO().getChildren(id);
    }

    public final List<E> getLeaves(final String id) {
        return getDAO().getLeaves(id);
    }

    public final Node<E> lookup(final String substring) {
        return getDAO().lookup(substring);
    }

    public final Node<E> getTree(final F filter) {
        if (getTable().getColumn("name").getCorrespondingForeignColumn() == null)
            throw new CustomException("Unable to getTree of this hierarchy: hierarchy is not linked by name to any table");
        return getDAO().getTree(getDAO().getColumnValues(filter, getTable().getColumn("name").getCorrespondingForeignColumn()));
    }

    @Override
    public final Boolean upload(final MultipartFile file) {
        throw new CustomException("Hierarchies import isn't supported now");
    }

    @Override
    protected abstract AbstractHierarchyDAO<E, F> getDAO();
}
