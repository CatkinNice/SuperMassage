package org.catkin.supermassage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * @author Catkin_nice
 *
 */
public class BaseDao {
	@Autowired
	protected JdbcTemplate template;
}
