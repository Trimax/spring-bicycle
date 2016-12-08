package org.springbicycle.querycontainers.hierarchies;

import org.springbicycle.querycontainers.AbstractQueriesContainer;
import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractHierarchyQueriesContainer extends AbstractQueriesContainer {
    public String getAll;

    @Required
    public final void setGetAll(final String getAll) {
        this.getAll = getAll;
    }
}
