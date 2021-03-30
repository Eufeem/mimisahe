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

import com.feem.dao.RoleDAO;
import com.feem.helper.Constants;
import com.feem.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);
	
	@Override
	public List<Role> get() {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_SELECT);
		sql.append(" * ");
		sql.append(Constants.SQL_FROM);
		sql.append(Constants.TABLE_ROLE);
		
		logger.info("Get => {}", sql);

		return jdbcTemplate.query(sql.toString(), new RoleMapper());
	}

	private class RoleMapper implements RowMapper<Role> {
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role model = new Role();
			model.setIdRole(rs.getInt("id_role"));
			model.setName(rs.getString("name"));
			model.setCode(rs.getString("code"));
			model.setStatus(rs.getInt("status"));
			model.setCreationDate(rs.getDate("creationDate"));
			model.setModificationDate(rs.getDate("modificationDate"));
			return model;
		}
	}

	@Override
	public void insert(Role model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_INSERT_INTO);
		sql.append(Constants.TABLE_ROLE);
		sql.append(" ( ");
		sql.append(" name, ");
		sql.append(" code, ");
		sql.append(" status, ");
		sql.append(" creationDate, ");
		sql.append(" modificationDate ");
		sql.append(" ) ");
		sql.append(Constants.SQL_VALUES);
		sql.append(" (?, ?, ?, SYSDATE(), SYSDATE()) ");
		
		Object[] params = { model.getName(), model.getCode(), model.getStatus() };
		
		logger.info("Insert => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void update(Role model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_ROLE);
		sql.append(Constants.SQL_SET);
		sql.append(" name = ?, ");
		sql.append(" code = ?, ");
		sql.append(" status = ?, ");
		sql.append(" modificationDate = SYSDATE() ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_role = ? ");
		
		Object[] params = { model.getName(), model.getCode(), model.getStatus(), model.getIdRole() };
		
		logger.info("Update => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		
		StringBuilder sqlStatus = new StringBuilder();
		sqlStatus.append("SELECT IF((SELECT status FROM " + Constants.TABLE_ROLE + " WHERE ID_ROLE = ?) != 1, 1, 0) status");
		Object[] paramsStatus = { id };
		@SuppressWarnings("deprecation")
		Integer status = jdbcTemplate.queryForObject(sqlStatus.toString(), paramsStatus, Integer.class);
		
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_ROLE);
		sql.append(Constants.SQL_SET);
		sql.append(" status = ? ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_role = ? ");
		
		Object[] params = { status, id };
		
		logger.info("Delete => {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}
	
}
