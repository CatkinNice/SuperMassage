package org.catkin.supermassage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.catkin.supermassage.entity.Roome;
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
public class RoomeRepository {
	
	@Autowired
	private MyJdbcTemplate template;
	
	private static final String T_ROOMES_COLUMN = " id, store_id, `name`, bed_num, remark, end_time, use_status ";
	private static final RowMapper<Roome> roomeMapper = new RowMapper<Roome>() {
		@Override
		public Roome mapRow(ResultSet rs, int rowNum) throws SQLException {
			Roome roome = new Roome();
			roome.setId(rs.getLong("id"));
			roome.setStoreId(rs.getLong("store_id"));
			roome.setName(rs.getString("name"));
			
			roome.setBedNum(rs.getInt("bed_num"));
			roome.setRemark(rs.getString("remark"));
			roome.setEndTime(rs.getTimestamp("end_time"));
			roome.setUseStatus(rs.getInt("use_status"));
			return roome;
		}
	};

	public void addRoome(Roome roome) {
		String sql = "INSERT INTO t_rooms (" + T_ROOMES_COLUMN + ")"
				+ " VALUES (:id, :storeId, :name, :bedNum, :remark, :endTime, :useStatus)";
		template.update(sql, new BeanPropertySqlParameterSource(roome));
	}

	public List<Roome> getRoomes(Long storeId, Integer status) {
		String sql = "SELECT " + T_ROOMES_COLUMN + " FROM t_rooms WHERE store_id = " + storeId;
		if (status != null) {
			sql += " AND use_status = " + status;
		}
		return template.query(sql, roomeMapper);
	}

	public void updateRoomeById(Roome roome) {
		String sql = "UPDATE t_rooms SET"
				+ " end_time = :endTime,"
				+ " use_status = :useStatus"
				+ " WHERE id = :id";
		template.update(sql, new BeanPropertySqlParameterSource(roome));
	}
	
}
