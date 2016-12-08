package org.springbicycle.queries.functions;

import org.springbicycle.columns.AbstractColumn;

public final class AggregateFunction implements Selectable {
    public enum Function {
        AVG,
        COUNT,
        FIRST,
        LAST,
        MAX,
        MIN,
        SUM
    }

    private final AbstractColumn column;
    private final Function aggregateFunction;
    private boolean isDistinct;

    public AggregateFunction(final AbstractColumn selected, final Function aggregateFunction) {
        this.column = selected;
        this.aggregateFunction = aggregateFunction;
    }

    public final void setDistinct() {
        isDistinct = true;
    }

    @Override
    public final String build() {
        return aggregateFunction + "(" + getDistinctExpression() + column.build() + ")";
    }

    private String getDistinctExpression() {
        return isDistinct ? " DISTINCT " : "";
    }
}
