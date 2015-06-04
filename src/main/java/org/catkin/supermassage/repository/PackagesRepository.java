package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.utils.MyJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Catkin_nice
 *
 */
@Repository
public class PackagesRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private static final String T_PACKAGES_COLUMN = " id, store_id, `name`, store_price, app_price, timed, remark ";
	private static final RowMapper<Packages> packagesMapper = new RowMapper<Packages>() {
		@Override
		public Packages mapRow(ResultSet rs, int rowNum) throws SQLException {
			Packages packages = new Packages();
			packages.setId(rs.getLong("id"));
			packages.setStoreId(rs.getLong("store_id"));
			
			packages.setName(rs.getString("name"));
			packages.setStorePrice(rs.getFloat("store_price"));
			packages.setAppPrice(rs.getFloat("app_price"));
			
			packages.setTimed(rs.getInt("timed"));
			packages.setRemark(rs.getString("remark"));
			return packages;
		}
	};
	
	public void addOrEditPackage(Packages packages) {
		String sql = "INSERT INTO packages (" + T_PACKAGES_COLUMN + ")"
				+ " VALUES (:id, :storeId, :name, :storePrice, :appPrice, :timed, :remark)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " `name` = :name, "
				+ " store_price = :storePrice,"
				+ " app_price = :appPrice,"
				+ " timed = :timed,"
				+ " remark = :remark";
		template.update(sql, new BeanPropertySqlParameterSource(packages));
	}

	public List<Packages> getPackages(Packages packares) {
		Integer from = packares.getFrom();
		Integer size = packares.getSize();
		
		String sql = "SELECT " + T_PACKAGES_COLUMN + " FROM t_packages" + getPackagesWhere(packares);

		if (size != null && size > 0){
			sql += " LIMIT " + (from == null ? 0 : from) + "," + size;
		}
		return template.query(sql, packagesMapper);
	}

	public int getPackagesCount(Packages packares) {
		String sql = "SELECT COUNT(id) FROM t_packages" + getPackagesWhere(packares);
		return template.queryForObject(sql, Integer.class);
	}
	
	private String getPackagesWhere(Packages packares) {
		String key = packares.getKey();
		String where = " WHERE store_id = " + packares.getStoreId();
		
		if (key != null && key.trim().length() > 0) {
			where += " AND `name` LIKE '%" + key + "%'";
		}
		
		return where;
	}

	public Packages getPackage(String id) {
		String sql = "SELECT " + T_PACKAGES_COLUMN + " FROM t_packages WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), packagesMapper);
	}
}
