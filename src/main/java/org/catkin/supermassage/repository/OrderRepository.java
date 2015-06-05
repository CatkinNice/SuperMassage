package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.catkin.supermassage.entity.Order;
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
	private static final String T_ORDER_COLUMN = " id, store_id, user_id, package_id, store_price, app_price, plan_staff_id, used_staff_id, plan_time, used_time, pay_id, pay_type, pay_status, deleted ";
	private static final RowMapper<Order> orderMapper = new RowMapper<Order>() {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getLong("id"));
			order.setStoreId(rs.getLong("store_id"));
			order.setUserId(rs.getLong("user_id"));
			
			order.setPackageId(rs.getLong("package_id"));
			order.setStorePrice(rs.getFloat("store_price"));
			order.setAppPrice(rs.getFloat("app_price"));
			order.setPlanStaffId(rs.getLong("plan_staff_id"));
			
			order.setUsedStaffId(rs.getLong("used_staff_id"));
			order.setPlanTime(rs.getTimestamp("plan_time"));
			order.setUsedTime(rs.getTimestamp("used_time"));
			order.setPayId(rs.getString("pay_id"));
			
			order.setPayType(rs.getInt("pay_type"));
			order.setPayStatus(rs.getBoolean("pay_status"));
			return order;
		}
	};

	public void addOrEditOrder(Order order) {
		String sql = "INSERT INTO t_order (" + T_ORDER_COLUMN + ")"
				+ " VALUES (:id, :storeId, :userId, :packageId, :storePrice, :appPrice, :planStaffId, :usedStaffId, :planTime, :usedTime, :payId, :payType, :payStatus, :deleted)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " plan_staff_id = :planStaffId,"
				+ " used_staff_id = :usedStaffId,"
				+ " plan_time = :planTime,"
				+ " used_time = :usedTime,"
				+ " pay_id = :payId,"
				+ " pay_type = :payType,"
				+ " pay_status = :payStatus"
				+ " deleted = :deleted";
		template.update(sql, new BeanPropertySqlParameterSource(order));
	}

	public boolean checkPlanTime(Long planStaffId, Date planTime) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}