package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Packages;
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
public class OrderRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	private static final String T_ORDER_COLUMN = " id, store_id, user_id, package_id, package_name,"
			+ " package_timed, store_price, app_price, pay_id, pay_type, use_status, deleted ";
	private static final RowMapper<Order> orderMapper = new RowMapper<Order>() {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getString("id"));
			order.setStoreId(rs.getLong("store_id"));
			order.setUserId(rs.getLong("user_id"));
			
			order.setPayId(rs.getString("pay_id"));
			order.setPayType(rs.getInt("pay_type"));
			order.setUseStatus(rs.getInt("use_status"));
			Packages packages = new Packages();
			
			packages.setId(rs.getLong("package_id"));
			packages.setName(rs.getString("package_name"));
			packages.setTimed(rs.getInt("package_timed"));
			order.setPackages(packages);
			return order;
		}
	};

	public void addOrder(Order order) {
		String sql = "INSERT INTO t_order (" + T_ORDER_COLUMN + ")"
				+ " VALUES (:id, :storeId, :userId, :packages.id, :packages.name, :packages.timed,"
				+ " :packages.storePrice, :packages.appPrice, :payId, :payType, :useStatus, :deleted)";
		template.update(sql, new BeanPropertySqlParameterSource(order));
	}
	
	public void editOrder(Order order) {
		String sql = "UPDATE t_order SET"
				+ " pay_id = IFNULL(:payId, pay_id),"
				+ " pay_type = IFNULL(:payType, pay_type),"
				+ " use_status = IFNULL(:useStatus, use_status),"
				+ " deleted = IFNULL(:deleted, deleted)"
				+ " WHERE id = :id";
		template.update(sql, new BeanPropertySqlParameterSource(order));
	}

	public Order getOrderById(String id) {
		String sql = "SELECT " + T_ORDER_COLUMN + " FROM t_consume WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), orderMapper);
	}

}