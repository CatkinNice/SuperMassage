package org.catkin.supermassage.service;

import java.util.Collections;
import java.util.List;

import org.catkin.supermassage.entity.StaffPackages;
import org.catkin.supermassage.utils.MyJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Catkin_nice
 *
 */
@Repository
public class StaffPackagesRepository {
	@Autowired
	private MyJdbcTemplate template;
	
	public void addStaffPackages(List<StaffPackages> staffPackages) {
		String sql = "INSERT INTO t_staff_packages (staff_id, package_id) VALUES(:staffId, :packagesId)";
		SqlParameterSource[] batchValues = new SqlParameterSource[staffPackages.size()];
		for (int i = 0; i < batchValues.length; i++) {
			batchValues[i] = new BeanPropertySqlParameterSource(staffPackages.get(i));
		}
 		template.batchUpdate(sql, batchValues);
	}
	
	public void delStaffPackagesByStaffId(Long staffId) {
		String sql = "DELETE FROM t_staff_packages WHERE staff_id = :staffId";
		template.update(sql, Collections.singletonMap("staffId", staffId));
	}
	
	public void delStaffPackagesByPackageId(Long packageId) {
		String sql = "DELETE FROM t_staff_packages WHERE package_id = :packageId";
		template.update(sql, Collections.singletonMap("packageId", packageId));
	}
}
