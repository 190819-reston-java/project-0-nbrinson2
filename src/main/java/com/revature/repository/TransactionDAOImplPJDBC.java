package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Transaction;
import com.revature.model.User;


public class TransactionDAOImplPJDBC implements TransactionDAO {

	@Override
	public Transaction getTransaction(long id) {
		Transaction transaction = null;
		final String query = "SELECT * FROM transactions WHERE id = ?;";

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next())
							transaction = createTransactionFromResultSet(resultSet);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}

	private Transaction createTransactionFromResultSet(ResultSet resultSet) throws SQLException {
		return new Transaction(
				resultSet.getLong("id"), 
				resultSet.getDate("dateof"), 
				resultSet.getDouble("amount"),
				resultSet.getLong("users_id"));
	}

	@Override
	public Transaction getTransaction(double amt) {
		Transaction transaction = null;
		final String query = "SELECT * FROM transactions WHERE amount = ?;";

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setDouble(1, amt);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next())
							transaction = createTransactionFromResultSet(resultSet);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transaction;
	}

	@Override
	public List<Transaction> getTransactions(User u) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		final String query = "SELECT * FROM transactions WHERE users_id = ?;";
		
		try (Connection conn = ConnectionUtil.getConnection()){
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, u.getId());
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						while (resultSet.next()) {
							transactions.add(createTransactionFromResultSet(resultSet));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return transactions;
	}

	@Override
	public boolean createTransaction(Transaction t) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "INSERT INTO transactions VALUES (DEFAULT,?,?,?);";
		
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setDate(1, t.getTransactionDate());;
			stmt.setDouble(2, t.getAmt());
			stmt.setLong(3, t.getUsers_id());
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
	public boolean deleteTransaction() {
		// Feature to be implemented in future update
		return false;
	}

	@Override
	public boolean updateTransaction() {
		// Feature to be implemented in future update
		return false;
	}

}
