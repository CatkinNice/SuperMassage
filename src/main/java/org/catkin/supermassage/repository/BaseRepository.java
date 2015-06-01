package org.catkin.supermassage.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 
 * @author Catkin_nice
 *
 */
public class BaseRepository {
	@Autowired
	protected JdbcTemplate template;
	
	@Autowired
	protected NamedParameterJdbcTemplate nTemplate;
}
