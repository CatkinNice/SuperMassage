package org.catkin.supermassage.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * @author Catkin_nice
 *
 */
public class BaseRepository {
	@Autowired
	protected JdbcTemplate template;
}
