package org.springbicycle.queries.stringexpressions;

public final class PercentExpression extends AbstractStringExpression {
    @Override
    public final String build() {
        return "'%'";
    }
}
