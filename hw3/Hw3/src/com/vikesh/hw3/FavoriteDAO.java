package com.vikesh.hw3;
//Author :Vikesh Inbasekharan
//Date : 30 - 09 - 2016
//course : 08-672

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.vikesh.hw3JavaBeans.Favorite;




public class FavoriteDAO extends GenericDAO<Favorite> {

	public FavoriteDAO(ConnectionPool cP, String tableName) throws DAOException {
		super(Favorite.class, tableName, cP);
		// TODO Auto-generated constructor stub
	}
	public Favorite[] getUserFavorites(int u) throws RollbackException {

		// Calls GenericDAO's match() method.
		
		Favorite[] items = match(MatchArg.equals("userId", u));

		return items;
	}
	
}
