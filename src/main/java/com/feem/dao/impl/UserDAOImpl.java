package com.feem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.feem.dao.UserDAO;
import com.feem.helper.Constants;
import com.feem.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	@Override
	public List<User> get() {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_SELECT);
		sql.append(" * ");
		sql.append(Constants.SQL_FROM);
		sql.append(Constants.TABLE_USER);
		
		logger.info("GET: {}", sql);
		return jdbcTemplate.query(sql.toString(), new UserMapper());
	}
	
	@Override
	public User findById(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_SELECT);
		sql.append(" * ");
		sql.append(Constants.SQL_FROM);
		sql.append(Constants.TABLE_USER);
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_user = ?  ");
		
		logger.info("FIND BY ID: {}", sql);
		return jdbcTemplate.queryForObject(sql.toString(), new UserMapper(), id);
	}

	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User model = new User();
			model.setIdUser(rs.getInt("id_user"));
			model.setName(rs.getString("name"));
			model.setLastName(rs.getString("last_name"));
			model.setCodeUser(rs.getString("code_user"));
			model.setStatus(rs.getInt("status"));
			model.setCreationDate(rs.getDate("creationDate"));
			model.setModificationDate(rs.getDate("modificationDate"));
			return model;
		}
	}

	@Override
	public void insert(User model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_INSERT_INTO);
		sql.append(Constants.TABLE_USER);
		sql.append(" ( ");
		sql.append(" name, ");
		sql.append(" last_name, ");
		sql.append(" code_user, ");
		sql.append(" status, ");
		sql.append(" creationDate, ");
		sql.append(" modificationDate ");
		sql.append(" ) ");
		sql.append(Constants.SQL_VALUES);
		sql.append(" (?, ?, ?, ?, SYSDATE(), SYSDATE()) ");
		
		Object[] params = { model.getName(), model.getLastName(), model.getCodeUser(), model.getStatus() };
		logger.info("INSERT: {}", sql);
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void update(User model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_USER);
		sql.append(Constants.SQL_SET);
		sql.append(" name = ?, ");
		sql.append(" last_name = ?, ");
		sql.append(" code_user = ?, ");
		sql.append(" status = ?, ");
		sql.append(" modificationDate = SYSDATE() ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_user = ? ");
		
		Object[] params = { model.getName(), model.getLastName(), model.getCodeUser(), model.getStatus(), model.getIdUser() };
		logger.info("UPDATE: {}", sql);
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		
		StringBuilder sqlStatus = new StringBuilder();
		sqlStatus.append("SELECT IF((SELECT status FROM " + Constants.TABLE_USER + " WHERE ID_USER = ?) != 1, 1, 0) status");
		Object[] paramsStatus = { id };
		@SuppressWarnings("deprecation")
		Integer status = jdbcTemplate.queryForObject(sqlStatus.toString(), paramsStatus, Integer.class);
		
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_USER);
		sql.append(Constants.SQL_SET);
		sql.append(" status = ? ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_user = ? ");
		
		Object[] params = { status, id };
		logger.info("DELETE: {}", sql);
		jdbcTemplate.update(sql.toString(), params);
	}

}
