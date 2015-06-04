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
	
	private static final String T_STAFF_COLUMN = " id, store_id, `name`, icon, gender, age, experience, job, remark, woke_status ";
	private static final RowMapper<Staff> staffMapper = new RowMapper<Staff>() {
		@Override
		public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
			Staff staff = new Staff();
			staff.setId(rs.getLong("id"));
			staff.setStoreId(rs.getLong("store_id"));
			
			staff.setName(rs.getString("name"));
			staff.setIcon(rs.getString("icon"));
			staff.setGender(rs.getBoolean("gender"));
			
			staff.setAge(rs.getInt("age"));
			staff.setExperience(rs.getInt("experience"));
			staff.setJob(rs.getString("job"));
			
			staff.setRemark(rs.getString("remark"));
			staff.setWokeStatus(rs.getInt("woke_status"));
			return staff;
		}
	};
	
	public void addOrEditStaff(Staff staff) {
		String sql = "INSERT INTO t_staff (" + T_STAFF_COLUMN + ")"
				+ " VALUES (:id, :storeId, :name, :icon, :gender, :age, :experience, :job, :remark, :wokeStatus)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " name = :name,"
				+ " icon = :icon,"
				+ " gender = :gender,"
				+ " age = :age,"
				+ " experience = :experience,"
				+ " job = :job,"
				+ " remark = :remark,"
				+ " woke_status = :wokeStatus";
		template.update(sql, new BeanPropertySqlParameterSource(staff));
	}
	
	public List<Staff> getStaffs(Staff staff) {
		Integer from = staff.getFrom();
		Integer size = staff.getSize();
		
		String sql = "SELECT " + T_STAFF_COLUMN + " FROM t_staff" + getStaffWhere(staff);
				
		if (size != null && size > 0){
			sql += " LIMIT " + (from == null ? 0 : from) + "," + size;
		}
		
		return template.query(sql, staffMapper);
	}
	
	public int getStaffsCount(Staff staff) {
		String sql = "SELECT COUNT(id) FROM t_staff" + getStaffWhere(staff);
		return template.queryForObject(sql, Integer.class);
	}
	
	private String getStaffWhere(Staff staff) {
		String key = staff.getKey();
		Integer wokeStatus = staff.getWokeStatus();
		String where = " WHERE store_id = " + staff.getStoreId();
		
		if (key != null && key.trim().length() > 0) {
			where += " AND `name` LIKE '%" + key + "%' OR job LIKE '%" + key + "%' ";
		}
		
		if (wokeStatus != null){
			where += " AND woke_status = " + wokeStatus + " " ;
		}
		return where;
	}

	public Staff getStaffById(Long id) {
		String sql = "SELECT " + T_STAFF_COLUMN + " FROM t_staff WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), staffMapper);
	}

	public Integer getStaffNumByStoreId(Long storeId) {
		String sql = "SELECT COUNT(id) FROM t_staff WHERE store_id = :storeId";
		return template.queryForObject(sql, Collections.singletonMap("storeId", storeId), Integer.class);
	}

	public void delStaffById(Long id) {
		String sql = "DELETE FROM t_staff WHERE id = :id";
		template.update(sql, Collections.singletonMap("id", id));
	}
	
}
