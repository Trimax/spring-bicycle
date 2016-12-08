package org.springbicycle.entitymappers;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.dao.Arguments;
import org.springbicycle.entities.AbstractEntity;
import org.springbicycle.enums.AbstractEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractEntityMapper<E extends AbstractEntity> implements RowMapper<E> {
    public final static class Row {
        private final ResultSet result;

        Row(final ResultSet result) {
            this.result = result;
        }

        public final Double getDouble(final AbstractColumn column) {
            return Optional.ofNullable(getString(column)).map(Double::parseDouble).orElse(null);
        }

        public final Integer getInteger(final AbstractColumn column) {
            return Optional.ofNullable(getString(column)).map(Integer::parseInt).orElse(null);
        }

        public final Boolean getBoolean(final AbstractColumn column) {
            return Optional.ofNullable(getString(column)).map(Boolean::parseBoolean).orElse(null);
        }

        public final Date getDate(final AbstractColumn column) {
            try {
                return result.getDate(column.getColumnName());
            } catch (final SQLException ignored) {}

            return null;
        }

        public final String getString(final AbstractColumn column) {
            try {
                return result.getString(column.getColumnName());
            } catch (final SQLException ignored) {}

            return null;
        }

        public final <T extends Enum<T> & AbstractEnum> T getEnum(final AbstractColumn column, final Class<T> clazz) {
            final String value = getString(column.getColumnName());
            if (value == null)
                return null;

            return EnumSet.allOf(clazz).parallelStream().filter( v -> Objects.equals(v.getValue(), value) ).findAny().orElse(null);
        }

        private String getString(final String column) {
            try {
                return result.getString(column);
            } catch (final SQLException ignored) {}

            return null;
        }
    }

    @Override
    public final E mapRow(final ResultSet resultSet, final int i) {
        return mapEntity(new Row(resultSet));
    }

    public abstract E mapEntity(final Row row);
    public abstract Arguments mapArguments(final E entity);
}
