package org.springbicycle.queries.stringexpressions;

public final class ValueExpression extends AbstractStringExpression {
    private final String parameter;

    public ValueExpression(final String parameter) {
        this.parameter = parameter;
    }

    @Override
    public final String build() {
        return ":" + parameter.toUpperCase();
    }
}
