package org.springbicycle.caches;

import org.springbicycle.entities.maps.AbstractMapEntity;

import java.util.*;

public final class MapCache<E extends AbstractMapEntity> {
    private final HashMap<String, List<String>> key2Value;

    public MapCache(final Collection<E> entities) {
        key2Value = new HashMap<>();

        entities.forEach(this::add);
    }

    public final synchronized void add(final E entity) {
        if (entity == null)
            return;

        add(entity.key, entity.value, key2Value);
    }

    private void add(final String key, final String value, final Map<String, List<String>> items) {
        if (!items.containsKey(key))
            items.put(key, new ArrayList<>());

        items.get(key).add(value);
    }

    public final List<String> getByKey(final String key) {
        return key2Value.getOrDefault(key, new ArrayList<>());
    }
}