package org.springbicycle.preparators;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.columns.CustomColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.filters.restrictions.Range;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.BetweenExpression;
import org.springbicycle.queries.logicalexpressions.GreaterExpression;
import org.springbicycle.queries.logicalexpressions.LessExpression;
import org.springbicycle.queries.stringexpressions.ValueExpression;

public final class RangePreparator extends AbstractPreparator {
    private final AbstractColumn columnMin;
    private final AbstractColumn columnMax;
    private final AbstractColumn column;

    private final Range<?> value;

    public RangePreparator(final AbstractColumn column, final Range<?> value) {
        this.value  = value;

        this.column = column;
        this.columnMin = new CustomColumn(column.getTable(), column.toString() + "_MIN", column);
        this.columnMax = new CustomColumn(column.getTable(), column.toString() + "_MAX", column);
    }

    @Override
    public final AbstractLogicalExpression getExpression() {
        if (value == null)
            return AbstractLogicalExpression.TRUE;

        if ((value.min != null) && (value.max != null))
            return new BetweenExpression(column, new ValueExpression(columnMin.toString()), new ValueExpression(columnMax.toString()));

        if (value.min != null)
            return new GreaterExpression(column, new ValueExpression(columnMin.toString()));

        return new LessExpression(column, new ValueExpression(columnMax.toString()));
    }

    @Override
    public final Arguments getArguments() {
        final Arguments parameters = new Arguments();
        if (value == null)
            return parameters;

        parameters.add(columnMin, value.min);
        parameters.add(columnMax, value.max);

        return parameters;
    }
}
