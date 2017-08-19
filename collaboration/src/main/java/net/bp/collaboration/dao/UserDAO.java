package net.bp.collaboration.dao;

import net.bp.collaboration.dto.User;

public interface UserDAO {

	User getByParam(String param, String value);
	
	void add(User user);
	
	
}
