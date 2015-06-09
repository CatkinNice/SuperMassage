package org.catkin.supermassage.service;

import org.catkin.supermassage.entity.User;
import org.catkin.supermassage.repository.UserRepository;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.ErrorType;
import org.catkin.supermassage.utils.LogicException;
import org.catkin.supermassage.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Catkin_nice
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;

	public void addOrEditUser(User user) {
		if (user.getId() == null) {
			if (ur.checkSameAccount(user.getAccount())) {
				throw new LogicException(ErrorType.errorSameAccount);
			}
			if (user.getType() == null) {
				user.setType(ConstantsStatus.User.TYPE_APP);
			}
			user.setId(Sequence.getNextId());
		}
		ur.addOrEditUser(user);
	}

	public User getUserById(Long id) {
		return ur.getUserById(id);
	}

	public User login(User user) {
		User dbUser = ur.getUserByAccount(user.getAccount());
		
		if (dbUser == null) {
			throw new LogicException(ErrorType.errorNotAccount);
		}
		
		if (!dbUser.getPwd().equals(user.getPwd())) {
			throw new LogicException(ErrorType.errorPassword);
		}
		
		//返回时清空密码
		dbUser.setPwd(null);
		return dbUser;
	}

	public void changepwd(User user) {
		User dbUser = ur.getUserById(user.getId(), true);
		if (!dbUser.getPwd().equals(user.getPwd())) {
			throw new LogicException(ErrorType.errorPassword);
		}
		ur.changePwd(user);
	}
	
}
