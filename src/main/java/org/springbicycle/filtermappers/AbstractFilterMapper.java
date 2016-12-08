package org.springbicycle.filtermappers;

import org.springbicycle.dao.Arguments;
import org.springbicycle.filters.AbstractFilter;
import org.springbicycle.preparators.AbstractPreparator;
import org.springbicycle.queries.logicalexpressions.AbstractLogicalExpression;
import org.springbicycle.queries.logicalexpressions.AndExpression;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractFilterMapper<F extends AbstractFilter> {
    public final Arguments mapArguments(final F filter) {
        return Optional.ofNullable(filter).map(this::getArgumentsFromNotNullFilter).orElse(new Arguments());
    }

    private Arguments getArgumentsFromNotNullFilter(final F filter) {
        return new Arguments(getPreparators(filter).stream().map(AbstractPreparator::getArguments).collect(Collectors.toList()));
    }

    public final AbstractLogicalExpression mapExpression(final F filter) {
        return Optional.ofNullable(filter).map(this::getExpressionFromNotNullFilter).orElse(AbstractLogicalExpression.TRUE);
    }

    private AbstractLogicalExpression getExpressionFromNotNullFilter(final F filter) {
        return new AndExpression(getPreparators(filter).stream().map(AbstractPreparator::getExpression).collect(Collectors.toList()));
    }

    protected abstract List<AbstractPreparator> getPreparators(final F filter);
}
