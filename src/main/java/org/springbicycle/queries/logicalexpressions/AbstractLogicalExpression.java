package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.queries.AbstractExpression;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractLogicalExpression extends AbstractExpression {
    public static final AbstractLogicalExpression TRUE = new AbstractLogicalExpression() {
        @Override
        public final String build() {
            return "TRUE";
        }
    };

    protected final String build(final BooleanOperator operator, final List<? extends AbstractLogicalExpression> expressions) {
        if (expressions == null || expressions.isEmpty())
            return TRUE.build();

        final List<AbstractLogicalExpression> nonTrivialExpressions = expressions.stream().filter( expression -> expression != TRUE ).collect(Collectors.toList());
        if (nonTrivialExpressions.isEmpty())
            return TRUE.build();

        final StringBuilder query = new StringBuilder();
        query.append("(");

        nonTrivialExpressions.stream().limit(nonTrivialExpressions.size() - 1).forEach( expression -> query.append(expression.build()).append(" ").append(operator).append(" ") );
        query.append(nonTrivialExpressions.get(nonTrivialExpressions.size() - 1).build());

        query.append(")");

        return query.toString();
    }
}
