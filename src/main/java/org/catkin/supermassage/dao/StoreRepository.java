package org.catkin.supermassage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.catkin.supermassage.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Catkin_nice
 *
 */
@Repository
public class StoreRepository {

	@Autowired
	private JdbcTemplate template;
	
	private final static RowMapper<Store> storeMapper = new RowMapper<Store>() {
		@Override
		public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
			Store store = new Store();
			store.setId(rs.getLong("id"));
			store.setName(rs.getString("name"));
			
			store.setPhone(rs.getString("phone"));
			store.setAddress(rs.getString("address"));
			store.setLongLatItude(rs.getString("longLatItude"));
			
			store.setRemark(rs.getString("remark"));
			return store;
		}
	};
	
	public Store getStoreById(Long id) {
		String sql = "SELECT id, name, pwd, long_lat_itude, address, phone, remark FROM t_store WHERE id = ?";
		Object[] args = {id};
		return template.queryForObject(sql, args, storeMapper);
	}

}
