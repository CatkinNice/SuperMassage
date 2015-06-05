package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.Store;
import org.catkin.supermassage.entity.model.QueryParam;
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
	
	private final static String T_STORE_COLUMN = " id, account, name, pwd, long_lat_itude, address, phone, remark ";
	
	public Store getStoreById(Long id) {
		return getStoreById(id, false);
	}
	
	public Store getStoreById(Long id, boolean displayPwd) {
		String sql = "SELECT " + T_STORE_COLUMN + " FROM t_store WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), new StoreMapper(displayPwd));
	}
	
	public void addOrEditStore(Store store) {
		String sql = "INSERT INTO t_store (" + T_STORE_COLUMN + ") "
				+ "VALUES (:id, :account, :name, :pwd, :longLatItude, :address, :phone, :remark) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "name = :name, "
				+ "long_lat_itude = :longLatItude, "
				+ "address = :address, "
				+ "phone = :phone, "
				+ "remark = :remark";
		template.update(sql, new BeanPropertySqlParameterSource(store));
	}

	public List<Store> getStores(QueryParam param) {
		Integer from = param.getFrom();
		Integer size = param.getSize();
		
		String sql = "SELECT " + T_STORE_COLUMN + " FROM t_store" + getStoresWhere(param);
		if (size != null && size > 0){
			sql += " LIMIT " + (from == null ? 0 : from) + "," + size;
		}
		return template.query(sql, new StoreMapper());
	}

	public int getStoresCount(QueryParam param) {
		String sql = "SELECT COUNT(id) FROM t_store" + getStoresWhere(param);
		return template.queryForObject(sql, Integer.class);
	}
	
	private String getStoresWhere(QueryParam param) {
		String key = param.getKey();
		
		String where = "";
		if (key != null && key.trim().length() > 0) {
			where = " WHERE `name` LIKE '%" + key + "%' OR account LIKE '%" + key + "%' OR address LIKE '%" + key + "%'";
		}
		return where;
	}
	
	public boolean checkSameAccount(String account) {
		String sql = "SELECT EXISTS (SELECT id FROM t_store WHERE account = :account)";
		return template.queryForObject(sql, Collections.singletonMap("account", account), Boolean.class);
	}

	public boolean checkSameName(String name) {
		String sql = "SELECT EXISTS (SELECT id FROM t_store WHERE `name` = :name)";
		return template.queryForObject(sql, Collections.singletonMap("name", name), Boolean.class);
	}

	public Store getStoreByAccount(String account) {
		String sql = "SELECT " + T_STORE_COLUMN + " FROM t_store WHERE account = :account";
		return template.queryForObject(sql, Collections.singletonMap("account", account), new StoreMapper(true));
	}

	public void changePwd(Store store) {
		String sql = "UPDATE t_store SET pwd = :newPwd WHERE id = :id";
		template.update(sql, new BeanPropertySqlParameterSource(store));
	}
	
	private static final class StoreMapper implements RowMapper<Store> {
		private boolean displayPwd = false;
		
		public StoreMapper() {
			super();
		}

		public StoreMapper(boolean displayPwd) {
			this.displayPwd = displayPwd;
		}

		@Override
		public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
			Store store = new Store();
			store.setId(rs.getLong("id"));
			store.setAccount(rs.getString("account"));
			if (displayPwd) store.setPwd(rs.getString("pwd"));
			
			store.setName(rs.getString("name"));
			store.setPhone(rs.getString("phone"));
			store.setAddress(rs.getString("address"));
			store.setLongLatItude(rs.getString("long_lat_itude"));
			
			store.setRemark(rs.getString("remark"));
			return store;
		}
	}

}
