package org.springbicycle.queries.logicalexpressions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class AndExpression extends AbstractLogicalExpression {
    private final List<AbstractLogicalExpression> expressions;

    public AndExpression(final AbstractLogicalExpression... expressions) {
        this(Arrays.stream(expressions).collect(Collectors.toList()));
    }

    public AndExpression(final List<AbstractLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public final void and(final AbstractLogicalExpression expression) {
        expressions.add(expression);
    }

    @Override
    public final String build() {
        return build(BooleanOperator.AND, expressions);
    }
}
