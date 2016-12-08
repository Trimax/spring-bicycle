package org.springbicycle.querycontainers;

import org.springframework.beans.factory.annotation.Required;

public abstract class AbstractQueriesContainer {
    public String get;
    public String create;
    public String delete;
    public String update;

    @Required
    public final void setGet(final String get) {
        this.get = get;
    }

    @Required
    public final void setCreate(final String create) {
        this.create = create;
    }

    @Required
    public final void setDelete(final String delete) {
        this.delete = delete;
    }

    @Required
    public final void setUpdate(final String update) {
        this.update = update;
    }
}
