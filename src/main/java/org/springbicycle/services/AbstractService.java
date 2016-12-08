package org.springbicycle.services;

import org.springbicycle.dao.AbstractDAO;
import org.springbicycle.entities.AbstractEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.tables.AbstractTable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, F extends AbstractFilter> {
    public final E get(final String id) {
        return getDAO().get(id);
    }

    public final Boolean create(final E entity) {
        return getDAO().create(entity);
    }

    public final Boolean update(final E entity) {
        return getDAO().update(entity);
    }

    public final Boolean delete(final String id) {
        return getDAO().delete(id);
    }

    public final List<String> getColumnValues(final F filter, final String columnName) {
        return getDAO().getColumnValues(filter, getTable().getColumn(columnName));
    }

    public abstract Boolean upload(final MultipartFile file);

    protected abstract AbstractDAO<E, F> getDAO();
    protected abstract AbstractTable getTable();
}
