package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.catkin.supermassage.entity.User;
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
public class UserRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	private static final String T_USER_COLUMN = " id, account, pwd, `name`, type, gender, age, icon, bank_id, remark ";
	private static final class UserMapper implements RowMapper<User> {
		private boolean displayPwd = false;
		
		public UserMapper(boolean displayPwd) {
			this.displayPwd = displayPwd;
		}

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setAccount(rs.getString("account"));
			if (displayPwd) user.setPwd(rs.getString("pwd"));
			
			user.setName(rs.getString("name"));
			user.setType(rs.getInt("type"));
			user.setGender(rs.getBoolean("gender"));
			user.setAge(rs.getInt("age"));
			
			user.setIcon(rs.getString("icon"));
			user.setBankId(rs.getString("bank_id"));
			user.setRemark(rs.getString("remark"));
			return user;
		}
		
	}
	
	
	public Boolean checkSameAccount(String account) {
		String sql = "SELECT EXISTS (SELECT id FROM t_user WHERE account = :account)";
		return template.queryForObject(sql, Collections.singletonMap("account", account), Boolean.class);
	}

	public void addOrEditUser(User user) {
		String sql = "INSERT INTO t_user (" + T_USER_COLUMN + ")"
				+ " VALUES(:id, :account, :pwd, :name, :type, :gender, :age, :icon, :bankId, :remark)"
				+ " ON DUPLICATE KEY UPDATE"
				+ " `name` = :name,"
				+ " gender = :gender,"
				+ " age = :age,"
				+ " icon = :icon,"
				+ " bank_id = :bankId,"
				+ " remark = :remark";
		
		template.update(sql, new BeanPropertySqlParameterSource(user));
	}

	public User getUserById(Long id) {
		return getUserById(id, false);
	}
	
	public User getUserById(Long id, boolean displayPwd) {
		String sql = "SELECT " + T_USER_COLUMN + " FROM t_user WHERE id = :id";
		return template.queryForObject(sql, Collections.singletonMap("id", id), new UserMapper(displayPwd));
	}

	public User getUserByAccount(String account) {
		String sql = "SELECT " + T_USER_COLUMN + " FROM t_user WHERE account = :account";
		return template.queryForObject(sql, Collections.singletonMap("account", account), new UserMapper(true));
	}
	
	public void changePwd(User user) {
		String sql = "UPDATE t_user SET pwd = :newPwd WHERE id = :id";
		template.update(sql, new BeanPropertySqlParameterSource(user));
	}

}
