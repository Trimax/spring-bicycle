package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.queries.SelectQuery;

public final class InSubqueryExpression extends AbstractLogicalExpression {
    private final SelectQuery query;
    private final AbstractColumn column;

    public InSubqueryExpression(final AbstractColumn column, final SelectQuery query) {
        this.query     = query;
        this.column    = column;
    }

    @Override
    public final String build() {
        return "(" + column.getColumnNameWithTableName() + " IN (" + query.build() + "))";
    }
}
