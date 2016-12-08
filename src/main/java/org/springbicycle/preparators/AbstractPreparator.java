package org.springbicycle.preparators;

import org.springbicycle.dao.Arguments;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;

public abstract class AbstractPreparator {
    public abstract AbstractLogicalExpression getExpression();
    public abstract Arguments getArguments();
}
