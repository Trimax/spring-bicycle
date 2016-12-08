package org.springbicycle.caches;

import java.util.ArrayList;

public final class Node<T> {
    public T data;
    public final ArrayList<Node<T>> children;

    public Node() {
        this(null);
    }

    public Node(final T node) {
        children = new ArrayList<>();
        data     = node;
    }

    public final void addChild(final Node<T> child) {
        children.add(child);
    }
}
