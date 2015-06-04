package org.catkin.supermassage.utils;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Catkin_nice
 *
 */
@Component
public class MyJdbcTemplate extends NamedParameterJdbcTemplate {

	@Autowired
	public MyJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	/**
	 * 
	 * @param sql SQL query to execute
	 * @param requiredType container of arguments to bind to the query
	 * @return the result object of the required type, or null in case of SQL NULL
	 */
	public <T> T queryForObject(String sql, Class<T> requiredType) {
		return super.queryForObject(sql, EmptySqlParameterSource.INSTANCE, requiredType);
	}

	@Override
	public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException {
		T obj = null;
		try {
			obj = super.queryForObject(sql, paramSource, rowMapper);
		} catch (Exception e) {
			Log.error(e);
		}
		return obj;
	}

	@Override
	public <T> T queryForObject(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) {
		T obj = null;
		try {
			obj = super.queryForObject(sql, paramMap, rowMapper);
		} catch (Exception e) {
			Log.error(e);
		}
		return obj;
	}

	@Override
	public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) {
		List<T> list = null;
		try {
			list = super.query(sql, paramSource, rowMapper);
		} catch (Exception e) {
			Log.error(e);
		}
		return list;
	}

	@Override
	public <T> List<T> query(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) {
		List<T> list = null;
		try {
			list = super.query(sql, paramMap, rowMapper);
		} catch (Exception e) {
			Log.error(e);
		}
		return list;
	}
	
	
}
