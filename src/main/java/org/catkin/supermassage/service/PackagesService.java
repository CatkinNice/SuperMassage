package org.catkin.supermassage.service;

import java.util.List;

import org.catkin.supermassage.entity.Packages;
import org.catkin.supermassage.entity.PageResult;
import org.catkin.supermassage.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class PackagesService {
	
	@Autowired
	private PackagesRepository pr;

	public void addOrEditPackage(Packages packages) {
		pr.addOrEditPackage(packages);
	}

	public PageResult getPackages(Packages packares) {
		List<Packages> data = pr.getPackages(packares);
		int totalSize = pr.getPackagesCount(packares);
		return new PageResult(data, totalSize);
	}

	public Packages getPackage(String id) {
		return pr.getPackage(id);
	}
	
	
}
