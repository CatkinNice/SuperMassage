package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.StoreBuy;
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
public class StoreBuyRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private final static RowMapper<StoreBuy> storeBuyMapper = new RowMapper<StoreBuy>() {
		@Override
		public StoreBuy mapRow(ResultSet rs, int rowNum) throws SQLException {
			StoreBuy storeBuy = new StoreBuy();
			storeBuy.setId(rs.getLong("id"));
			storeBuy.setStoreId(rs.getLong("store_id"));
			
			storeBuy.setStaffNum(rs.getInt("staff_num"));
			storeBuy.setPrice(rs.getFloat("price"));
			storeBuy.setBuyTime(rs.getTime("buy_time"));
			
			storeBuy.setStartTime(rs.getTime("start_time"));
			storeBuy.setEndTime(rs.getTime("end_time"));
			return storeBuy;
		}
	};
	
	private final static String T_STORE_BUY_COLUMN = " id, store_id, staff_num, price, buy_time, start_time, end_time ";
		
	public void addStoreBuy(StoreBuy storeBuy) {
		String sql = "INSERT INTO t_store_buy (" + T_STORE_BUY_COLUMN + ") "
				+ "VALUES (:id, :storeId, :staffNum, :price, NOW(), :startTime, :endTime)";
		template.update(sql, new BeanPropertySqlParameterSource(storeBuy));
	}
	
	public List<StoreBuy> getStoreBuyByStoreId(Long id) {
		String sql = "SELECT " + T_STORE_BUY_COLUMN + " FROM t_store_buy "
				+ "WHERE store_id = :storeId  ORDER BY end_time DESC";
		return template.query(sql, Collections.singletonMap("storeId", id), storeBuyMapper);
	}

	public Integer getStaffNum(Long id) {
		String sql = "SELECT SUM(staff_num) FROM t_store_buy"
				+ "WHERE store_id = :storeId "
				+ "AND CURRENT_DATE BETWEEN start_time AND end_time";
		return template.queryForObject(sql, Collections.singletonMap("storeId", id), Integer.class);
	}
	
	/**
	 * 取10天内要过期的购买信息
	 */
	public List<StoreBuy> getExpStoreBuy() {
		String sql = "SELECT " + T_STORE_BUY_COLUMN + " FROM t_store_buy "
				+ "WHERE end_time BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY)";
		return template.query(sql, storeBuyMapper);
	}

}
