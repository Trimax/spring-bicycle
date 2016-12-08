package org.springbicycle.services.collections;

import org.springbicycle.dao.collections.AbstractCollectionDAO;
import org.springbicycle.entities.collections.AbstractCollectionEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.services.AbstractService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public abstract class AbstractCollectionService<E extends AbstractCollectionEntity, F extends AbstractFilter> extends AbstractService<E, F> {
    public final List<E> search(final F filter) {
        return getDAO().search(filter);
    }

    public final Long count(final F filter) {
        return getDAO().count(filter);
    }

    @Override
    public final Boolean upload(final MultipartFile file) {
        return true;
    }

    @Override
    protected abstract AbstractCollectionDAO<E, F> getDAO();
}
