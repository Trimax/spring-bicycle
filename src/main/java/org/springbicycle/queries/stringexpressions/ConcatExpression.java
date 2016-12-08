package org.springbicycle.queries.stringexpressions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ConcatExpression extends AbstractStringExpression {
    protected List<AbstractStringExpression> expressions;

    public ConcatExpression(final AbstractStringExpression... expressions) {
        this.expressions = Arrays.asList(expressions);
    }

    @Override
    public final String build() {
        if (expressions == null || expressions.isEmpty())
            return "";

        final List<AbstractStringExpression> nonEmptyExpressions = expressions.stream().filter(expression -> !(expression == null)).collect(Collectors.toList());
        if (nonEmptyExpressions.isEmpty())
            return "";

        final StringBuilder query = new StringBuilder();
        query.append("CONCAT").append("(");

        nonEmptyExpressions.stream().limit(nonEmptyExpressions.size() - 1).forEach( expression -> query.append(expression.build()).append(", ") );
        query.append(nonEmptyExpressions.get(nonEmptyExpressions.size() - 1).build());

        query.append(")");

        return query.toString();
    }
}
