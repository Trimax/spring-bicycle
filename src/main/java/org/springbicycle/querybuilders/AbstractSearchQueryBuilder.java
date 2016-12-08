package org.springbicycle.querybuilders;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.filters.restrictions.Limitation;
import org.springbicycle.filters.restrictions.Sorting;
import org.springbicycle.queries.AbstractQuery;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.tables.AbstractTable;

public abstract class AbstractSearchQueryBuilder {
    public abstract AbstractQuery getSearchQuery(final AbstractTable table, final Arguments arguments, final AbstractLogicalExpression expressions, final Sorting sorting, final Limitation limitation);

    public abstract AbstractQuery getCountQuery(final AbstractTable table, final Arguments arguments, final AbstractLogicalExpression expressions);

    public abstract AbstractQuery getColumnValues(final AbstractColumn column, final Arguments arguments, final AbstractLogicalExpression expressions, final Sorting sorting, final Limitation limitation);
}