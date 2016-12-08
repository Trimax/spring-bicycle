package org.springbicycle.queries.logicalexpressions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class OrExpression extends AbstractLogicalExpression {
    private final List<AbstractLogicalExpression> expressions;

    public OrExpression(final AbstractLogicalExpression... expressions) {
        this(Arrays.stream(expressions).collect(Collectors.toList()));
    }

    public OrExpression(final List<AbstractLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public final void or(final AbstractLogicalExpression expression) {
        expressions.add(expression);
    }

    @Override
    public final String build() {
        return build(BooleanOperator.OR, expressions);
    }
}
