package com.vikesh.hw3;

//Author :Vikesh Inbasekharan
//Date : 30 - 09 - 2016
//course : 08-672

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import com.vikesh.hw3JavaBeans.User;

public class UserDAO extends GenericDAO<User>{

	//User user = new User();
	public UserDAO(ConnectionPool cP,String tablename) throws DAOException {
		
		super(User.class, tablename, cP);
		// TODO Auto-generated constructor stub
	}

}
