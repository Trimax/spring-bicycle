package org.springbicycle.controllers.hierarchies;

import org.springbicycle.caches.Node;
import org.springbicycle.controllers.AbstractController;
import org.springbicycle.definitions.Methods;
import org.springbicycle.definitions.Parameters;
import org.springbicycle.entities.hierarchies.AbstractHierarchicalEntity;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.services.hierarchies.AbstractHierarchyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class AbstractHierarchyController<E extends AbstractHierarchicalEntity, F extends AbstractFilter> extends AbstractController<E, F> {
    @RequestMapping(value = Methods.SUGGEST, method = RequestMethod.GET)
    public final List<String> suggest(@RequestParam(value = Parameters.PREFIX)                  final String string,
                                      @RequestParam(value = Parameters.LIMIT, required = false) final Integer limit) {
        return getService().suggest(string, limit);
    }

    @RequestMapping(value = Methods.GET_CHILDREN, method = RequestMethod.GET)
    public final List<E> getChildren(@RequestParam(value = Parameters.ID, required = false) final String id) {
        return getService().getChildren(id);
    }

    @RequestMapping(value = Methods.GET_LEAVES, method = RequestMethod.GET)
    public final List<E> getLeaves(@RequestParam(value = Parameters.ID, required = false) final String id) {
        return getService().getLeaves(id);
    }

    @RequestMapping(value = Methods.LOOKUP, method = RequestMethod.GET)
    public final Node<E> lookup(@RequestParam(value = Parameters.SUBSTRING) final String string) {
        return getService().lookup(string);
    }

    @RequestMapping(value = Methods.GET_TREE, method = RequestMethod.POST)
    public final Node<E> getTree(@RequestBody final F filter) {
        return getService().getTree(filter);
    }

    @Override
    protected abstract AbstractHierarchyService<E, F> getService();
}
