package org.springbicycle.preparators;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.InExpression;
import org.springbicycle.queries.stringexpressions.ValueExpression;

import java.util.List;

public final class ListPreparator extends AbstractPreparator {
    private final AbstractColumn column;
    private final List<?> value;

    public ListPreparator(final AbstractColumn column, final List<?> value) {
        this.column = column;
        this.value  = value;
    }

    @Override
    public final AbstractLogicalExpression getExpression() {
        return value == null ? AbstractLogicalExpression.TRUE : new InExpression(column, new ValueExpression(column.toString()));
    }

    @Override
    public final Arguments getArguments() {
        final Arguments parameters = new Arguments();
        parameters.add(column, value);

        return parameters;
    }
}
