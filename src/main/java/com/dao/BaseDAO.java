package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import com.util.HibernateUtil;

public class BaseDAO {

	protected Session session;

	protected Session getSession() {
		if (session == null || !session.isConnected()) {
			session = HibernateUtil.getSession();
			session.beginTransaction();
		}
		return session;
	}

	public BaseDAO() {
	}

	public void rollback() {
		if (getSession() != null || !getSession().isConnected()) {
			getSession().getTransaction().rollback();
		}
	}

	public void commit() {
		if (getSession() != null || !getSession().isConnected()) {
			getSession().getTransaction().commit();
		}
	}

	public void closeConnection() {
		if (getSession() != null && getSession().isOpen()) {
			session.close();
		}
	}

	public Connection getConnection() {
		return ((SessionImpl) getSession()).connection();
	}

	public ResultSet getResultSet(String sql) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement(sql);
			return ps.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps = null;
		}
		return null;
	}

	public void executeNonQuery(String sql) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = getConnection().prepareStatement(sql);
			pstmt.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			pstmt = null;
		}
	}
}