package org.springbicycle.dao;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.enums.AbstractEnum;
import org.springbicycle.filters.restrictions.Range;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Arguments {
    private final Map<AbstractColumn, Object> parameters;

    public Arguments() {
        parameters = new HashMap<>();
    }

    public Arguments(final AbstractColumn column, final Object value) {
        this();

        add(column, value);
    }

    public Arguments(final List<Arguments> arguments) {
        this();

        arguments.forEach(this::addAll);
    }

    public final void addAll(final Arguments arguments) {
        if (arguments == null)
            return;

        arguments.parameters.keySet().forEach(k -> parameters.put(k, arguments.parameters.get(k)));
    }

    public final void add(final AbstractColumn column, final Object value) {
        addToParameters(column, value);
    }

    private void addToParameters(final AbstractColumn column, final Object value) {
        if (value instanceof AbstractEnum) {
            parameters.put(column, ((AbstractEnum) value).getValue());
            return;
        }

        parameters.put(column, value);
    }

    public final void add(final AbstractColumn columnMin, final AbstractColumn columnMax, final Range range) {
        add(columnMin, range == null ? null : range.min);
        add(columnMax, range == null ? null : range.max);
    }

    public final Map<AbstractColumn, Object> getParameters() {
        return parameters;
    }
}