package org.springbicycle.preparators;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.EqualsExpression;
import org.springbicycle.queries.stringexpressions.ValueExpression;

public final class NumberPreparator extends AbstractPreparator {
    private final AbstractColumn column;
    private final Number value;

    public NumberPreparator(final AbstractColumn column, final Number value) {
        this.column = column;
        this.value  = value;
    }

    @Override
    public final AbstractLogicalExpression getExpression() {
        return value == null ? AbstractLogicalExpression.TRUE : new EqualsExpression(column, new ValueExpression(column.toString()));
    }

    @Override
    public final Arguments getArguments() {
        final Arguments parameters = new Arguments();
        parameters.add(column, value);

        return parameters;
    }
}
