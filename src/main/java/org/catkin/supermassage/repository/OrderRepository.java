package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	private static final String T_ORDER_COLUMN = " id, store_id, user_id, package_id, package_name,"
			+ " package_timed, store_price, app_price, plan_staff_id, used_staff_id, plan_staff_name,"
			+ " used_staff_name, plan_time, used_time, pay_id, pay_type, pay_status, deleted ";
	private static final RowMapper<Order> orderMapper = new RowMapper<Order>() {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getString("id"));
			order.setStoreId(rs.getLong("store_id"));
			order.setUserId(rs.getLong("user_id"));
			
			order.setPlanTime(rs.getTimestamp("plan_time"));
			order.setUsedTime(rs.getTimestamp("used_time"));
			
			order.setPayId(rs.getString("pay_id"));
			order.setPayType(rs.getInt("pay_type"));
			order.setUseStatus(rs.getInt("use_status"));
			
			return order;
		}
	};

	public void addOrEditOrder(Order order) {
		String sql = "INSERT INTO t_order (" + T_ORDER_COLUMN + ")"
				+ " VALUES (:id, :storeId, :userId, :packages.id, :packages.name, :packages.timed,"
				+ " :packages.storePrice, :packages.appPrice, :planStaff.id, :usedStaff.id, :planStaff.name,"
				+ " :usedStaff.name, :planTime, :usedTime, :payId, :payType, :payStatus, :deleted)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " plan_staff_id = :planStaff.id,"
				+ " used_staff_id = :usedStaff.id,"
				+ " plan_staff_name = :planStaff.name,"
				+ " used_staff_name = :usedStaff.name,"
				+ " plan_time = :planTime,"
				+ " used_time = :usedTime,"
				+ " pay_id = :payId,"
				+ " pay_type = :payType,"
				+ " pay_status = :payStatus,"
				+ " deleted = :deleted";
		template.update(sql, new BeanPropertySqlParameterSource(order));
	}

	/**
	 * @return true 为预约时间不可用
	 */
	public boolean checkPlanTime(Order order) {
		String sql = "SELECT EXISTS(SELECT id FROM t_order"
				+ " WHERE (plan_staff_id = :planStaffId OR used_staff_id = :planStaffId)"
				+ " AND (CURRENT_DATE = DATE(plan_time) OR CURRENT_DATE = DATE(used_time))"
				+ " AND (ADDDATE(plan_time, INTERVAL package_timed MINUTE) > :planTime"
					+ " OR ADDDATE(used_time, INTERVAL (package_timed - 10) MINUTE) > :planTime"
					+ " OR ADDDATE(:planTime, INTERVAL (:packageTimed + 10) MINUTE) > plan_time))";
		return template.queryForObject(sql, new BeanPropertySqlParameterSource(order), Boolean.class);
	}
}