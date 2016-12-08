package org.springbicycle.controllers;

import org.springbicycle.definitions.Methods;
import org.springbicycle.definitions.Parameters;
import org.springbicycle.entities.AbstractEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.services.AbstractService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbstractController<E extends AbstractEntity, F extends AbstractFilter> {
    final static String DATE_TIME_FORMAT = "yyyy-MM-dd";

    @RequestMapping(value = Methods.GET, method = RequestMethod.GET)
    public final E get(@RequestParam(value = Parameters.ID) final String id) {
        return getService().get(id);
    }

    @RequestMapping(value = Methods.CREATE, method = RequestMethod.POST)
    public final Boolean create(@RequestBody final E entity) {
        return getService().create(entity);
    }

    @RequestMapping(value = Methods.UPDATE, method = RequestMethod.POST)
    public final Boolean update(@RequestBody final E entity) {
        return getService().update(entity);
    }

    @RequestMapping(value = Methods.DELETE, method = RequestMethod.GET)
    public final Boolean delete(@RequestParam(value = Parameters.ID) final String id) {
        return getService().delete(id);
    }

    @RequestMapping(value = Methods.UPLOAD, method = RequestMethod.POST)
    public final Boolean upload(@RequestBody final MultipartFile file) {
        return getService().upload(file);
    }

    protected abstract AbstractService<E, F> getService();
}
