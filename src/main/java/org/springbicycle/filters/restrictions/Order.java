package org.springbicycle.filters.restrictions;

public enum Order {
    Ascending("ASC"),
    Descending("DESC");

    private final String clause;

    Order(final String clause) {
        this.clause = clause;
    }

    public final String getClause() {
        return clause;
    }
}
