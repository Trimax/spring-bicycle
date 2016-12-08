package org.springbicycle.datasources;

import org.springbicycle.dao.Arguments;
import org.springbicycle.entities.AbstractEntity;
import org.springbicycle.entitymappers.AbstractEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public final class DBDataSource {
    @Autowired
    private DataSource dataSource;

    protected final NamedParameterJdbcTemplate getTemplate() {
        final NamedParameterJdbcDaoSupport namedParameterJdbcDaoSupport = new NamedParameterJdbcDaoSupport();
        namedParameterJdbcDaoSupport.setDataSource(dataSource);

        return namedParameterJdbcDaoSupport.getNamedParameterJdbcTemplate();
    }

    public final Boolean insert(final String query, final Arguments arguments) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        if (arguments == null)
            return getTemplate().update(query, new MapSqlParameterSource(), keyHolder) > 0;

        return getTemplate().update(query, new MapSqlParameterSource(getParametersForJDBC(arguments)), keyHolder) > 0;
    }

    public final void insert(final String query, final Collection<Arguments> arguments) {
        if (arguments == null)
            return;

        getTemplate().batchUpdate(query, arguments.stream().map(this::getParametersForJDBC).map(MapSqlParameterSource::new).collect(Collectors.toList()).toArray(new MapSqlParameterSource[arguments.size()]));
    }

    public final <E extends AbstractEntity> E get(final String query, final Arguments arguments, final AbstractEntityMapper<E> mapper) {
        if (arguments == null)
            return getTemplate().queryForObject(query, new HashMap<>(), mapper);

        return getTemplate().queryForObject(query, getParametersForJDBC(arguments), mapper);
    }

    public final Boolean update(final String query, final Arguments arguments) {
        return getTemplate().update(query, getParametersForJDBC(arguments)) > 0;
    }

    public final <E extends AbstractEntity> List<E> search(final String query, final Arguments arguments, final AbstractEntityMapper<E> mapper) {
        if (arguments == null)
            return getTemplate().query(query, new HashMap<>(), mapper);

        return getTemplate().query(query, getParametersForJDBC(arguments), mapper);
    }

    public final <E extends AbstractEntity> Boolean isEmpty(final String query, final Arguments arguments, final AbstractEntityMapper<E> mapper) {
        return search(query, arguments, mapper).isEmpty();
    }

    public final <T> T getObject(final String query, final Arguments arguments, final Class<T> clazz) {
        return getTemplate().queryForObject(query, getParametersForJDBC(arguments), clazz);
    }

    public final <T> List<T> getList(final String query, final Arguments arguments, final Class<T> clazz) {
        return getTemplate().queryForList(query, getParametersForJDBC(arguments), clazz);
    }

    private Map<String, Object> getParametersForJDBC(final Arguments arguments) {
        final Map<String, Object> results = new HashMap<>();
        arguments.getParameters().forEach((k, v) -> results.put(k.toString(), v));

        return results;
    }
}
