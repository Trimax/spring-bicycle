package org.springbicycle.caches;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.StringUtils;
import org.springbicycle.entities.hierarchies.AbstractHierarchicalEntity;

import java.util.*;
import java.util.stream.Collectors;

public final class HierarchyCache<E extends AbstractHierarchicalEntity> {
    private final HashMap<String, E> id2Entity;
    private final HashMap<String, List<E>> id2Children;
    private final HashMap<String, List<E>> name2Entities;
    private final Trie<String, String> trie;

    public HierarchyCache(final Collection<E> entities) {
        name2Entities = new HashMap<>();
        id2Children   = new HashMap<>();
        id2Entity     = new HashMap<>();
        trie          = new PatriciaTrie<>();

        id2Entity.put(null, null);
        entities.forEach(this::add);
    }

    public final synchronized void add(final E entity) {
        if (entity == null)
            return;

        id2Entity.put(entity.id, entity);

        add(entity.name.toLowerCase(), entity, name2Entities);
        add(entity.parentID, entity, id2Children);

        trie.put(entity.name.toLowerCase(), entity.name);
    }

    private void add(final String key, final E entity, final Map<String, List<E>> items) {
        if (!items.containsKey(key))
            items.put(key, new ArrayList<>());

        items.get(key).add(entity);
    }

    public final E getByID(final String id) {
        return id2Entity.get(id);
    }

    public final List<E> getByName(final String name) {
        return name2Entities.getOrDefault(name.toLowerCase(), new ArrayList<>());
    }

    public final List<E> getChildren(final String id) {
        return id2Children.getOrDefault(id, new ArrayList<>()).stream().sorted( (e1, e2) -> StringUtils.compare(e1.name, e2.name) ).collect(Collectors.toList());
    }

    public final List<String> suggest(final String prefix) {
        return trie.prefixMap(prefix.toLowerCase()).values().stream().collect(Collectors.toList());
    }

    public final Node<E> lookup(final String substring) {
        final String substringLowerCase = substring.toLowerCase();
        final List<E> matchingEntities = name2Entities.entrySet().stream().filter(e -> e.getKey() != null).filter( e -> e.getKey().contains(substringLowerCase) ).flatMap(entry -> entry.getValue().stream()).collect(Collectors.toList());

        return buildTree(matchingEntities);
    }

    public final Node<E> getTree(final List<String> names) {
        return buildTree(names.stream().map(this::getByName).flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private Node<E> buildTree(final List<E> items) {
        final HashMap<String, Node<E>> id2Node = new HashMap<>();
        final Queue<Node<E>> queue = new LinkedList<>();

        id2Node.put(null, new Node<>());

        items.forEach( entity -> {
            final Node<E> node = new Node<>(entity);
            id2Node.put(entity.id, node);
            queue.add(node);
        } );

        while (!queue.isEmpty()) {
            final Node<E> node = queue.poll();

            if (id2Node.containsKey(node.data.parentID)) {
                id2Node.get(node.data.parentID).addChild(node);
                continue;
            }

            final Node<E> parent = new Node<>(id2Entity.get(node.data.parentID));
            id2Node.put(parent.data.id, parent);
            queue.add(parent);

            parent.addChild(node);
        }

        return id2Node.get(null);
    }

    public final List<E> collect(final String id) {
        final List<E> results = new ArrayList<>();
        if (!id2Entity.containsKey(id))
            return results;

        results.add(id2Entity.get(id));
        if (id2Children.containsKey(id))
            id2Children.get(id).forEach( child -> results.addAll(collect(child.id)) );

        return results;
    }
}