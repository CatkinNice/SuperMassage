package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.utils.MyJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
	private static final String T_ORDER_STORE_COLUMN = " o.id, o.store_id, o.user_id, o.package_id,"
			+ " o.package_name, o.package_timed, o.store_price, o.app_price, o.pay_id, o.pay_type,"
			+ " o.use_status, o.deleted, s.name ";
	private static final class OrderMapper implements RowMapper<Order> {
		private boolean storeName = false;
		
		public OrderMapper(boolean storeName) {
			this.storeName = storeName;
		}

		public OrderMapper() {
			super();
		}

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
			
			if (storeName) order.setStoreName(rs.getString("name"));
			return order;
		}
	}
		
	public void addOrder(Order order) {
		String sql = "INSERT INTO t_order (" + T_ORDER_COLUMN + ")"
				+ " VALUES (:id, :storeId, :userId, :packages.id, :packages.name, :packages.timed,"
				+ " :packages.storePrice, :packages.appPrice, :payId, :payType, :useStatus, :deleted)";
		template.update(sql, new BeanPropertySqlParameterSource(order));
	}
	
	public void editOrder(Order...orders) {
		String sql = "UPDATE t_order SET"
				+ " pay_id = IFNULL(:payId, pay_id),"
				+ " pay_type = IFNULL(:payType, pay_type),"
				+ " use_status = IFNULL(:useStatus, use_status),"
				+ " deleted = IFNULL(:deleted, deleted)"
				+ " WHERE id = :id";
		SqlParameterSource[] batchValues = new SqlParameterSource[orders.length];
		for (int i = 0; i < batchValues.length; i++) {
			batchValues[i] = new BeanPropertySqlParameterSource(orders[i]);
		}
		template.batchUpdate(sql, batchValues);
	}

	public Order getOrderById(String id) {
		String sql = "SELECT " + T_ORDER_COLUMN + " FROM t_order WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), new OrderMapper());
	}
	
	/**
	 * 根据code查询未使用订单
	 */
	public Order getOrderByCode(Order order) {
		String sql = "SELECT " + T_ORDER_COLUMN + " FROM t_order"
				+ " WHERE store_id = :storeId AND use_status < 3 id LIKE ':id%'";
		return template.queryForObject(sql, new BeanPropertySqlParameterSource(order), new OrderMapper());
	}
	
	public List<Order> getOrder(Order order) {
		Integer page = order.getPage();
		Integer size = order.getSize();
		
		String sql = "SELECT " + T_ORDER_STORE_COLUMN 
				+ " FROM t_order o INNER JOIN t_store s"
				+ " ON o.store_id = s.id"
				+ getOrderWhere(order);
		
		if (size != null && size > 0){
			sql += " LIMIT " + (page == null ? 0 : page) + "," + size;
		}
		return template.query(sql, new OrderMapper(true));
	}
	public Integer getOrderCount(Order order) {
		String sql = "SELECT COUNT(o.id) FROM t_order o" + getOrderWhere(order);
		return template.queryForObject(sql, Integer.class);
	}
	
	private String getOrderWhere(Order order) {
		StringBuilder where = new StringBuilder(" WHERE 1 = 1");
		
		if (order.getStoreId() != null) 
			where.append(" AND o.store_id = ").append(order.getStoreId());
		
		if (order.getUserId() != null)
			where.append(" AND o.user_id = ").append(order.getUserId());
		
		if (order.getPayType() != null)
			where.append(" AND o.pay_type = ").append(order.getPayType());
		
		if (order.getUseStatus() != null) 
			where.append(" AND o.use_status = ").append(order.getUseStatus());
		
		return where.toString();
	}

}