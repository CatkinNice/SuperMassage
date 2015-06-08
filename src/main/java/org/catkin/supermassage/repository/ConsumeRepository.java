package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.catkin.supermassage.entity.Consume;
import org.catkin.supermassage.entity.Staff;
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
public class ConsumeRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private static final String T_CONSUME_COLUMN = " order_id, package_timed, plan_staff_id, plan_staff_name, plan_time, used_staff_id, used_staff_name, used_time, room_id ";
	private static final RowMapper<Consume> consumeMapper = new RowMapper<Consume>() {
		@Override
		public Consume mapRow(ResultSet rs, int rowNum) throws SQLException {
			Consume consume = new Consume();
			consume.setOrderId(rs.getString("order_id"));
			consume.setPackageTime(rs.getInt("package_timed"));
			consume.setPlanStaff(new Staff(rs.getLong("plan_staff_id"), rs.getString("plan_staff_name")));
			consume.setUsedStaff(new Staff(rs.getLong("used_staff_id"), rs.getString("used_staff_name")));
			
			consume.setPlanTime(rs.getTimestamp("plan_time"));
			consume.setUsedTime(rs.getTimestamp("used_time"));
			consume.setRoomId(rs.getLong("room_id"));
			return consume;
		}
	};

	public void addOrEditConcume(Consume consume) {
		String sql = "INSERT INTO t_consume (" + T_CONSUME_COLUMN + ")"
				+ " VALUES (:orderId, :packageTimed, :planStaff.id, :planStaff.name, :planTime, :usedStaff.id, :usedStaff.name, :usedTime, :roomId)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " plan_staff_id = :planStaff.id,"
				+ " plan_staff_name = :planStaff.name,"
				+ " plan_time = :planTime,"
				+ " used_staff_id = :usedStaff.id,"
				+ " used_staff_name = :usedStaff.name,"
				+ " used_time = :usedTime,"
				+ " room_id = :roomId";
		template.update(sql, new BeanPropertySqlParameterSource(consume));
	}

	/**
	 * @return true 为预约时间不可用
	 */
	public boolean checkPlanTime(Consume consume) {
		String sql = "SELECT EXISTS(SELECT id FROM t_consume"
				+ " WHERE (plan_staff_id = :planStaff.id OR used_staff_id = :planStaff.id)"
				+ " AND (CURRENT_DATE = DATE(plan_time) OR CURRENT_DATE = DATE(used_time))"
				+ " AND (ADDDATE(plan_time, INTERVAL package_timed MINUTE) > :planTime"
					+ " OR ADDDATE(used_time, INTERVAL (package_timed - 10) MINUTE) > :planTime"
					+ " OR ADDDATE(:planTime, INTERVAL (:packageTimed + 10) MINUTE) > plan_time))";
		return template.queryForObject(sql, new BeanPropertySqlParameterSource(consume), Boolean.class);
	}
}
