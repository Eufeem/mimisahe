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
		
		logger.info("Get => {}", sql);

		return jdbcTemplate.query(sql.toString(), new UserMapper());
	}

	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			u.setIdUser(rs.getInt("id_user"));
			u.setName(rs.getString("name"));
			u.setLastName(rs.getString("last_name"));
			u.setCodeUser(rs.getString("code_user"));
			u.setStatus(rs.getInt("status"));
			u.setCreationDate(rs.getDate("creationDate"));
			u.setModificationDate(rs.getDate("modificationDate"));
			return u;
		}
	}

	@Override
	public void insert(User user) {
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
		
		Object[] params = { user.getName(), user.getLastName(), user.getCodeUser(), user.getStatus() };
		
		logger.info("Insert => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void update(User user) {
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
		
		Object[] params = { user.getName(), user.getLastName(), user.getCodeUser(), user.getStatus(), user.getIdUser() };
		
		logger.info("Update => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void delete(Integer idUser) {
		StringBuilder sql = new StringBuilder();
		
		StringBuilder sqlStatus = new StringBuilder();
		sqlStatus.append("SELECT IF((SELECT status FROM user WHERE ID_USER = ?) != 1, 1, 0) status");
		Object[] paramsStatus = { idUser };
		@SuppressWarnings("deprecation")
		Integer status = jdbcTemplate.queryForObject(sqlStatus.toString(), paramsStatus, Integer.class);
		
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_USER);
		sql.append(Constants.SQL_SET);
		sql.append(" status = ? ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_user = ? ");
		
		Object[] params = { status, idUser };
		
		logger.info("Delete => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

}
