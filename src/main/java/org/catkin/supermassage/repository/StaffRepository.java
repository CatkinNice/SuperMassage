package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

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
public class StaffRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private static final String T_STAFF_COLUMN = " id, store_id, `name`, icon, sex, age, experience, job, remark, woke_status ";
	private static final RowMapper<Staff> staffMapper = new RowMapper<Staff>() {
		@Override
		public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
			Staff staff = new Staff();
			staff.setId(rs.getLong("id"));
			staff.setStoreId(rs.getLong("store_id"));
			
			staff.setName(rs.getString("name"));
			staff.setIcon(rs.getString("icon"));
			staff.setSex(rs.getBoolean("sex"));
			
			staff.setAge(rs.getInt("age"));
			staff.setExperience(rs.getInt("experience"));
			staff.setJob(rs.getString("job"));
			
			staff.setRemark(rs.getString("remark"));
			staff.setWokeStatus(rs.getInt("woke_status"));
			return staff;
		}
	};
	
	public void addOrEditStaff(Staff staff) {
		String sql = "INSERT INTO t_staff (" + T_STAFF_COLUMN + ") "
				+ "VALUES (:id, :storeId, :name, :icon, :sex, :age, :experience, :job, :remark, :wokeStatus) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "icon = :icon, "
				+ "sex = :sex, "
				+ "age = :age, "
				+ "experience = :experience, "
				+ "job = :job, "
				+ "remark = :remark, "
				+ "woke_status = :wokeStatus";
		template.update(sql, new BeanPropertySqlParameterSource(staff));
	}
	
	public List<Staff> getStaffs(Staff staff) {
		String key = staff.getKey();
		Integer from = staff.getFrom();
		Integer size = staff.getSize();
		Integer wokeStatus = staff.getWokeStatus();
		
		String sql = "SELECT " + T_STAFF_COLUMN + " FROM t_staff "
				+ "WHERE store_id = " + staff.getStoreId() + " ";
		
		if (key != null && key.trim().length() > 0) {
			sql += "AND `name` LIKE '%" + key + "%' OR job LIKE '%" + key + "%' ";
		}
		if (wokeStatus != null){
			sql += "AND woke_status = " + wokeStatus + " " ;
		}
		if (size != null && size > 0){
			sql += "LIMIT " + (from == null ? 0 : from) + "," + size;
		}
		
		return template.query(sql, staffMapper);
	}

	public Staff getStaffById(String id) {
		String sql = "SELECT " + T_STAFF_COLUMN + " FROM t_staff WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), staffMapper);
	}
	
}
