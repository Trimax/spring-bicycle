package org.springbicycle.controllers.collections;

import org.springbicycle.controllers.AbstractController;
import org.springbicycle.definitions.Methods;
import org.springbicycle.definitions.Parameters;
import org.springbicycle.entities.collections.AbstractCollectionEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.services.collections.AbstractCollectionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class AbstractCollectionController<E extends AbstractCollectionEntity, F extends AbstractFilter> extends AbstractController<E, F> {
    @RequestMapping(value = Methods.SEARCH, method = RequestMethod.POST)
    public final List<E> search(@RequestBody final F filter) {
        return getService().search(filter);
    }

    @RequestMapping(value = Methods.COUNT, method = RequestMethod.POST)
    public final Long count(@RequestBody final F filter) {
        return getService().count(filter);
    }

    @RequestMapping(value = Methods.GET_COLUMN_VALUES, method = RequestMethod.POST)
    public final List<String> getColumnValues(@RequestParam(value = Parameters.COLUMN) final String column,
                                              @RequestBody final F filter) {
        return getService().getColumnValues(filter, column);
    }

    @Override
    protected abstract AbstractCollectionService<E, F> getService();
}
