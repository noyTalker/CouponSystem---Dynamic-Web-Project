package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import DAOInterface.CustomerDao;
import classes.Customer;
import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;

public class CustomerDaoDb implements CustomerDao {

	@Override
	public void create(Customer customer) throws CouponSystemException {
		String sql = "insert into Customer values(?,?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customer.getId());
			pstmt.setString(2, customer.getCustName());
			pstmt.setString(3, customer.getPassword());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("create customer failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Customer read(Customer customer) throws CouponSystemException {
		String sql = "select * from Customer where id = ?";
		Customer c = new Customer();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customer.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setCustName(rs.getString("CUST_NAME"));
					c.setPassword(rs.getString("PASSWORD"));
					return c;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read customer failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Customer readName(Customer customer) throws CouponSystemException {
		String sql = "select * from Customer where CUST_NAME = ?";
		Customer c = new Customer();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getCustName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setCustName(rs.getString("CUST_NAME"));
					c.setPassword(rs.getString("PASSWORD"));
					return c;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read customer failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void update(Customer customer) throws CouponSystemException {
		String sql = "update Customer set Cust_Name = ?, Password = ? WHERE ID = " + customer.getId();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("update customer failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void delete(Customer customer) throws CouponSystemException {
		String sql = "DELETE FROM Customer WHERE ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customer.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("delete customer failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<Customer> getAllCustomers() throws CouponSystemException {
		Set<Customer> set = new HashSet<>();
		String sql = "select * from Customer";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getLong("ID"));
				c.setCustName(rs.getString("CUST_NAME"));
				c.setPassword(rs.getString("PASSWORD"));
				set.add(c);
			}
			return set;
		} catch (SQLException e) {
			throw new CouponSystemException("get all customers faled", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
}
