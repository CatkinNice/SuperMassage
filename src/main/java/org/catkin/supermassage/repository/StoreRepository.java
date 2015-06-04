package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.QueryParam;
import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.utils.MyJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

/**
 * 
 * @author Catkin_nice
 *
 */
@Repository
public class StoreRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private final static RowMapper<Store> storeMapper = new RowMapper<Store>() {
		@Override
		public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
			Store store = new Store();
			store.setId(rs.getLong("id"));
			store.setName(rs.getString("name"));
			
			store.setPhone(rs.getString("phone"));
			store.setAddress(rs.getString("address"));
			store.setLongLatItude(rs.getString("long_lat_itude"));
			
			store.setRemark(rs.getString("remark"));
			return store;
		}
	};
	
	private final static String T_STORE_COLUMN = " id, name, pwd, long_lat_itude, address, phone, remark ";
	
	public Store getStoreById(Long id) {
		String sql = "SELECT " + T_STORE_COLUMN + " FROM t_store WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), storeMapper);
	}
	
	public void addOrEditStore(Store store) {
		String sql = "INSERT INTO t_store (" + T_STORE_COLUMN + ") "
				+ "VALUES (:id, :name, :pwd, :longLatItude, :address, :phone, :remark) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "name = :name, "
				+ "pwd = :pwd, "
				+ "long_lat_itude = :longLatItude, "
				+ "address = :address, "
				+ "phone = :phone, "
				+ "remark = :remark";
		template.update(sql, new BeanPropertySqlParameterSource(store));
	}

	public List<Store> getStores(QueryParam param) {
		String key = param.getKey();
		Integer from = param.getFrom();
		Integer size = param.getSize();
		
		String sql = "SELECT " + T_STORE_COLUMN + " FROM t_store ";
		if (key != null && key.trim().length() > 0) {
			sql += ("WHERE `name` LIKE '%" + key + "%' OR address LIKE '%" + key + "%' ");
		}
		if (size != null && size > 0){
			sql += ("LIMIT " + (from == null ? 0 : from) + "," + size);
		}
		return template.query(sql, storeMapper);
	}

}
