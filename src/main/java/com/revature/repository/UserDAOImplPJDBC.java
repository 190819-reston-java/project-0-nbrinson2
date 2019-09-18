package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.User;


public class UserDAOImplPJDBC implements UserDAO {

	@Override
	public User getUser(long id) {
		User user = null;
		final String query = "SELECT * FROM users WHERE id = ?;";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromResultSet(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
		return new User(
				resultSet.getLong("id"),
				resultSet.getString("username"),
				resultSet.getString("pword"),
				resultSet.getDouble("balance"));
	}

	@Override
	public User getUser(String username) {
		User user = null;
		final String query = "SELECT * FROM users WHERE username = ?;";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromResultSet(resultSet);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> getUsers() {
		// Feature to be implemented in future update
		return null;
	}

	@Override
	public boolean createUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "INSERT INTO users VALUES (DEFAULT,?,?,?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getUserPassword());
			stmt.setDouble(3, u.getBalance());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(rs);
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
			
		}
		return true;
	}

	@Override
	public boolean updateUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String query = "UPDATE users SET username=?, pword=?, balance=? WHERE id=?;";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getUserPassword());
			stmt.setDouble(3, u.getBalance());
			stmt.setLong(4, u.getId());
			stmt.execute();		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(stmt);
			StreamCloser.close(conn);
		}
		
		return true;
	}

	@Override
	public boolean deleteUser(User u) {
		// Feature to be implemented in future update
		return false;
	}

}
