package org.springbicycle.preparators;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.LikeExpression;
import org.springbicycle.queries.stringexpressions.ConcatExpression;
import org.springbicycle.queries.stringexpressions.PercentExpression;
import org.springbicycle.queries.stringexpressions.ValueExpression;

import java.util.Optional;

public final class StringPreparator extends AbstractPreparator {
    private final AbstractColumn column;
    private final String value;

    public StringPreparator(final AbstractColumn column, final String value) {
        this.column = column;
        this.value  = value;
    }

    @Override
    public final AbstractLogicalExpression getExpression() {
        return value == null ? AbstractLogicalExpression.TRUE : new LikeExpression(column, new ConcatExpression(new PercentExpression(), new ValueExpression(column.toString()), new PercentExpression()));
    }

    @Override
    public final Arguments getArguments() {
        return Optional.ofNullable(value).map( v -> new Arguments(column, v) ).orElse(new Arguments());
    }
}
