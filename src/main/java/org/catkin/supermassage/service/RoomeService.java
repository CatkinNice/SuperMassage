package org.catkin.supermassage.service;

import java.util.Date;
import java.util.List;

import org.catkin.supermassage.entity.Roome;
import org.catkin.supermassage.repository.RoomeRepository;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class RoomeService {
	@Autowired
	private RoomeRepository rr;

	public void addRoome(Roome roome) {
		if (roome.getId() == null) {
			roome.setId(Sequence.getNextId());
			roome.setUseStatus(0);
		}
		rr.addRoome(roome);
	}

	public List<Roome> getRoomes(Long storeid, Integer status) {
		return rr.getRoomes(storeid, status);
	}

	public void updateRoomeById(long id, int status, Long endTime) {
		Date time = endTime == null ? null : new Date(endTime);
		rr.updateRoomeById(new Roome(id, time, status));
	}
}
