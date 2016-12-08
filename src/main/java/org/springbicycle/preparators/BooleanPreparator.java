package org.springbicycle.preparators;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.IsExpression;
import org.springbicycle.queries.stringexpressions.ValueExpression;

import java.util.Optional;

public final class BooleanPreparator extends AbstractPreparator {
    private final AbstractColumn column;
    private final Boolean value;

    public BooleanPreparator(final AbstractColumn column, final Boolean value) {
        this.column = column;
        this.value  = value;
    }

    @Override
    public final AbstractLogicalExpression getExpression() {
        return value == null ? AbstractLogicalExpression.TRUE : new IsExpression(column, new ValueExpression(column.toString()));
    }

    @Override
    public final Arguments getArguments() {
        return Optional.ofNullable(value).map( v -> new Arguments(column, v) ).orElse(new Arguments());
    }
}
