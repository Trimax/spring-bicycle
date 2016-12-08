package org.springbicycle.queries;

import org.springbicycle.filters.restrictions.Limitation;
import org.springbicycle.filters.restrictions.Sorting;
import org.springbicycle.queries.functions.Selectable;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.tables.AbstractTable;

import java.util.*;

public final class SelectQuery extends AbstractQuery {
    private boolean isDistinct;
    private final List<Selectable> selectedFields;
    private final AbstractTable table;
    private final Map<AbstractTable, List<AbstractLogicalExpression>> joinedTables;
    private Sorting sorting;
    private Limitation limitation;
    private AbstractLogicalExpression where;

    public SelectQuery(final AbstractTable table) {
        this(table, new ArrayList<>());
    }

    public SelectQuery(final AbstractTable table, final List<Selectable> selectedFields) {
        this.selectedFields = selectedFields;
        this.joinedTables    = new LinkedHashMap<>();
        this.table           = table;
    }

    public final void setDistinct() {
        isDistinct = true;
    }

    public final void join(final AbstractTable table, final AbstractLogicalExpression expression) {
        if (!joinedTables.containsKey(table))
            joinedTables.put(table, new ArrayList<>());

        joinedTables.get(table).add(Optional.ofNullable(expression).orElse(AbstractLogicalExpression.TRUE));
    }

    public final void where(final AbstractLogicalExpression expression) {
        this.where = expression;
    }

    public final void orderBy(final Sorting sorting) {
        this.sorting = sorting;
    }

    public final void limit(final Limitation limitation) {
        this.limitation = limitation;
    }

    @Override
    public final String build() {
        return "SELECT " + getDistinctExpression() + getSelectedColumnsExpression() + getFromTableExpression() + getJoinedTablesExpression() + getWhereExpression() + getSortingClause() + getLimitationClause();
    }

    private String getSelectedColumnsExpression() {
        if (selectedFields == null || selectedFields.isEmpty())
            return "*\n";

        final StringBuilder query = new StringBuilder();

        selectedFields.stream().limit(selectedFields.size() - 1).forEach(column -> query.append(column.build()).append(", "));
        query.append(selectedFields.get(selectedFields.size() - 1).build()).append("\n");

        return query.toString();
    }

    private String getFromTableExpression() {
        return "FROM " + table.getName() + "\n";
    }

    private String getJoinedTablesExpression() {
        final StringBuilder query = new StringBuilder();
        for (final AbstractTable joinedColumn : joinedTables.keySet())
            query.append(getJoinedTableExpression(joinedColumn));

        return query.toString();
    }

    private String getJoinedTableExpression(final AbstractTable joinedTable) {
        final StringBuilder query = new StringBuilder();
        for (final AbstractLogicalExpression joinedTableInstance : joinedTables.get(joinedTable))
            query.append(getJoinedTableInstanceExpression(joinedTable, joinedTableInstance));

        return query.toString();
    }

    private String getJoinedTableInstanceExpression(final AbstractTable joinedTable, final AbstractLogicalExpression expression) {
        if ((expression == null) || (expression == AbstractLogicalExpression.TRUE))
            return "JOIN " + joinedTable.getName() + "\n";

        return "JOIN " + joinedTable.getName() + " ON (" + expression.build() + ")\n";
    }

    private String getWhereExpression() {
        return Optional.ofNullable(where).map( where -> "WHERE " + where.build() ).orElse("");
    }

    private String getDistinctExpression() {
        return isDistinct ? " DISTINCT " : "";
    }

    private String getSortingClause() {
        if ((sorting == null)||(sorting.column == null)||(sorting.order == null))
            return "";

        return " ORDER BY " + sorting.column + " " + sorting.order.getClause();
    }

    private String getLimitationClause() {
        return limitation == null ? "" : " LIMIT " + Optional.ofNullable(limitation.startRow).orElse(0) + ", " + Optional.ofNullable(limitation.count).orElse(Integer.MAX_VALUE);
    }
}
