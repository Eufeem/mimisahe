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

import com.feem.dao.FunctionDAO;
import com.feem.helper.Constants;
import com.feem.model.Function;

@Repository
public class FunctionDAOImpl implements FunctionDAO {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LogManager.getLogger(FunctionDAOImpl.class);

	@Override
	public List<Function> get() {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_SELECT);
		sql.append(" * ");
		sql.append(Constants.SQL_FROM);
		sql.append(Constants.TABLE_FUNCTION);
		
		logger.info("GET: {}", sql);

		return jdbcTemplate.query(sql.toString(), new FunctionMapper());
	}
	
	private class FunctionMapper implements RowMapper<Function> {
		public Function mapRow(ResultSet rs, int rowNum) throws SQLException {
			Function model = new Function();
			model.setIdFunction(rs.getInt("id_function"));
			model.setName(rs.getString("name"));
			model.setCode(rs.getString("code"));
			model.setStatus(rs.getInt("status"));
			model.setCreationDate(rs.getDate("creationDate"));
			model.setModificationDate(rs.getDate("modificationDate"));
			return model;
		}
	}

	@Override
	public void insert(Function model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_INSERT_INTO);
		sql.append(Constants.TABLE_FUNCTION);
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
		
		logger.info("INSERT: {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void update(Function model) {
		StringBuilder sql = new StringBuilder();
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_FUNCTION);
		sql.append(Constants.SQL_SET);
		sql.append(" name = ?, ");
		sql.append(" code = ?, ");
		sql.append(" status = ?, ");
		sql.append(" modificationDate = SYSDATE() ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_role = ? ");
		
		Object[] params = { model.getName(), model.getCode(), model.getStatus(), model.getIdFunction() };
		
		logger.info("UPDATE: {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public void delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		
		StringBuilder sqlStatus = new StringBuilder();
		sqlStatus.append("SELECT IF((SELECT status FROM " + Constants.TABLE_FUNCTION + " WHERE ID_FUNCTION = ?) != 1, 1, 0) status");
		Object[] paramsStatus = { id };
		@SuppressWarnings("deprecation")
		Integer status = jdbcTemplate.queryForObject(sqlStatus.toString(), paramsStatus, Integer.class);
		
		sql.append(Constants.SQL_UPDATE);
		sql.append(Constants.TABLE_FUNCTION);
		sql.append(Constants.SQL_SET);
		sql.append(" status = ? ");
		sql.append(Constants.SQL_WHERE);
		sql.append(" id_function = ? ");
		
		Object[] params = { status, id };
		
		logger.info("DELETE: {}", sql);
		
		jdbcTemplate.update(sql.toString(), params);
	}

}
