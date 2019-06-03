package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import DAOInterface.CustomerCuponsDao;
import classes.Coupon;
import classes.CustomerCoupon;
import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;

public class CustomerCouponsDaoDb implements CustomerCuponsDao {


	@Override
	public void create(CustomerCoupon customercoupon) throws CouponSystemException {
		String sql = "insert into Customer_Coupon values(?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setLong(1, customercoupon.getCustId());
			pstmt.setLong(2, customercoupon.getCouponId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void deleteCoupons(long couponId) throws CouponSystemException {
		String sql = "DELETE FROM Customer_Coupon WHERE Coupon_ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, couponId);
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
	
	@Override
	public void deleteCustomer(long customerId) throws CouponSystemException {
		String sql = "DELETE FROM Customer_Coupon WHERE Cust_ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customerId);
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<CustomerCoupon> read(CustomerCoupon customercoupon) throws CouponSystemException {
		String sql = "select * from Customer_Coupon where Cust_ID = ?";
		Set<CustomerCoupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customercoupon.getCustId());
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					CustomerCoupon c = new CustomerCoupon();
					c.setCustId(rs.getLong("CUST_ID"));
					c.setCouponId(rs.getLong("COUPON_ID"));
					set.add(c);
				}
				return set;
			}
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<Coupon> readAllCustomerCoupons(long customerId) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Customer_Coupon INNER JOIN Coupon ON Customer_Coupon.Coupon_ID = Coupon.ID WHERE Cust_ID = ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Coupon c = new Coupon();
					c.setId(rs.getLong("ID"));
					c.setTitle(rs.getString("TITLE"));
					c.setSrartDate(rs.getDate("START_DATE"));
					c.setEndDate(rs.getDate("END_DATE"));
					c.setAmount(rs.getInt("AMOUNT"));
					c.setType(Coupon.Type.valueOf(rs.getString("TYPE")));
					c.setMessage(rs.getString("MESSAGE"));
					c.setPrice(rs.getInt("PRICE"));
					c.setImage(rs.getString("IMAGE"));
					set.add(c);
				}
				return set;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<Coupon> readAllCustomerCouponsByType(long customerId, String type) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Customer_Coupon INNER JOIN Coupon ON Customer_Coupon.Coupon_ID = Coupon.ID WHERE Cust_ID = ? AND Type = ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customerId);
			pstmt.setString(2, type);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Coupon c = new Coupon();
					c.setId(rs.getLong("ID"));
					c.setTitle(rs.getString("TITLE"));
					c.setSrartDate(rs.getDate("START_DATE"));
					c.setEndDate(rs.getDate("END_DATE"));
					c.setAmount(rs.getInt("AMOUNT"));
					c.setType(Coupon.Type.valueOf(rs.getString("TYPE")));
					c.setMessage(rs.getString("MESSAGE"));
					c.setPrice(rs.getInt("PRICE"));
					c.setImage(rs.getString("IMAGE"));
					set.add(c);
				}
				return set;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
	
	@Override
	public Set<Coupon> readAllCustomerCouponsUntilPrice(long customerId, double price) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Customer_Coupon INNER JOIN Coupon ON Customer_Coupon.Coupon_ID = Coupon.ID WHERE Cust_ID = ? AND Price <= ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, customerId);
			pstmt.setDouble(2, price);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Coupon c = new Coupon();
					c.setId(rs.getLong("ID"));
					c.setTitle(rs.getString("TITLE"));
					c.setSrartDate(rs.getDate("START_DATE"));
					c.setEndDate(rs.getDate("END_DATE"));
					c.setAmount(rs.getInt("AMOUNT"));
					c.setType(Coupon.Type.valueOf(rs.getString("TYPE")));
					c.setMessage(rs.getString("MESSAGE"));
					c.setPrice(rs.getInt("PRICE"));
					c.setImage(rs.getString("IMAGE"));
					set.add(c);
				}
				return set;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
}
