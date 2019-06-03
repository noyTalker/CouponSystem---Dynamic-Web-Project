package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import DAOInterface.CouponDao;
import classes.Coupon;
import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;

public class CouponDaoDb implements CouponDao {

	@Override
	public void create(Coupon coupon) throws CouponSystemException {
		String sql = "insert into Coupon values(?,?,?,?,?,?,?,?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, coupon.getId());
			pstmt.setString(2, coupon.getTitle());
			pstmt.setDate(3, ConvertDate.convert(coupon.getStartDate()));
			pstmt.setDate(4, ConvertDate.convert(coupon.getEndDate()));
			pstmt.setInt(5, coupon.getAmount());
			pstmt.setString(6, coupon.getType().toString());
			pstmt.setString(7, coupon.getMessage());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("create coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Coupon read(Coupon coupon) throws CouponSystemException {
		String sql = "select * from Coupon where id = ?";
		Coupon c = new Coupon();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, coupon.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setTitle(rs.getString("TITLE"));
					c.setSrartDate(rs.getDate("START_DATE"));
					c.setEndDate(rs.getDate("END_DATE"));
					c.setAmount(rs.getInt("AMOUNT"));
					c.setType(Coupon.Type.valueOf(rs.getString("TYPE")));
					c.setMessage(rs.getString("MESSAGE"));
					c.setPrice(rs.getInt("PRICE"));
					c.setImage(rs.getString("IMAGE"));
					return c;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Coupon readName(Coupon coupon) throws CouponSystemException {
		String sql = "select * from Coupon where TITLE = ?";
		Coupon c = new Coupon();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, (coupon.getTitle()));
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setTitle(rs.getString("TITLE"));
					c.setSrartDate(rs.getDate("START_DATE"));
					c.setEndDate(rs.getDate("END_DATE"));
					c.setAmount(rs.getInt("AMOUNT"));
					c.setType(Coupon.Type.valueOf(rs.getString("TYPE")));
					c.setMessage(rs.getString("MESSAGE"));
					c.setPrice(rs.getInt("PRICE"));
					c.setImage(rs.getString("IMAGE"));
					return c;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new CouponSystemException("read company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
	
	@Override
	public Set<Coupon> readAllExpired() throws CouponSystemException {
		Set<Coupon> set = new HashSet<>();
		String sql = "select * from coupon where end_date < current_date";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);ResultSet rs = pstmt.executeQuery()){
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
		} catch (SQLException e) {
			throw new CouponSystemException("get all coupons faled", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
	
	@Override
	public void update(Coupon coupon) throws CouponSystemException {
		String sql = "update Coupon set TITLE = ?, START_DATE = ?, END_DATE = ?, AMOUNT = ?, TYPE = ?, MESSAGE = ?, PRICE = ?, IMAGE = ? WHERE ID = " + coupon.getId();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(3, ConvertDate.convert(coupon.getStartDate()));
			pstmt.setDate(4, ConvertDate.convert(coupon.getEndDate()));
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().toString());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("update coupon failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void delete(Coupon coupon) throws CouponSystemException {
		String sql = "DELETE FROM coupon WHERE ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setLong(1, coupon.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("delete company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<Coupon> getAllCoupons() throws CouponSystemException {
		Set<Coupon> set = new HashSet<>();
		String sql = "select * from Coupon";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);ResultSet rs = pstmt.executeQuery()){
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
		} catch (SQLException e) {
			throw new CouponSystemException("get all coupons faled", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
}
