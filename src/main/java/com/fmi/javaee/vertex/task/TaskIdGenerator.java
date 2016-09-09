package com.fmi.javaee.vertex.task;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TaskIdGenerator implements IdentifierGenerator {

	private static final String TASK_PREFIX = "VERT-";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resSet = statement.executeQuery("select count(*) from tasks");
			return TASK_PREFIX + getNextIndex(resSet);
		} catch (SQLException e) {
			throw new HibernateException("Could not generate task id", e);
		}
	}

	private int getNextIndex(ResultSet resSet) throws SQLException {
		return resSet.next() ? resSet.getInt(1) + 1 : 1;
	}

}
