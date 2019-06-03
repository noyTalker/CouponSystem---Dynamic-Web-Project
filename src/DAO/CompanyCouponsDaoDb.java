package DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import DAOInterface.CompanyCouponsDao;
import classes.Company;
import classes.CompanyCoupon;
import classes.Coupon;
import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;

public class CompanyCouponsDaoDb implements CompanyCouponsDao {

	@Override
	public void create(CompanyCoupon companycoupon) throws CouponSystemException {
		String sql = "insert into Company_Coupon values(?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setLong(1, companycoupon.getCompId());
			pstmt.setLong(2, companycoupon.getCouponId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void deleteCoupons(Coupon coupon) throws CouponSystemException {
		String sql = "DELETE FROM Company_Coupon WHERE Coupon_ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, coupon.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
	@Override
	public void deleteCompany(Company company) throws CouponSystemException {
		String sql = "DELETE FROM Company_Coupon WHERE COMP_ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<CompanyCoupon> read(Company company) throws CouponSystemException {
		String sql = "select * from Company_Coupon where COMP_ID = ?";
		Set<CompanyCoupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, company.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					CompanyCoupon c = new CompanyCoupon();
					c.setCompId(rs.getLong("COMP_ID"));
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
	public Coupon readCoupon(long companyId, long couponId) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Company_Coupon INNER JOIN Coupon ON Company_Coupon.COUPON_ID = Coupon.ID WHERE Comp_ID = ? AND Coupon_ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, companyId);
			pstmt.setLong(2, couponId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
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
	public Set<Coupon> readAllCompanyCoupons(long companyId) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Company_Coupon INNER JOIN Coupon ON Company_Coupon.COUPON_ID = Coupon.ID WHERE Comp_ID = ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, companyId);
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
	public Set<Coupon> readAllCompanyCouponsByType(long companyId, String type) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Company_Coupon INNER JOIN Coupon ON Company_Coupon.COUPON_ID = Coupon.ID WHERE Comp_ID = ? AND Type = ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, companyId);
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
	public Set<Coupon> readAllCompanyCouponsUntilPrice(long companyId, double price) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Company_Coupon INNER JOIN Coupon ON Company_Coupon.COUPON_ID = Coupon.ID WHERE Comp_ID = ? AND Price <= ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, companyId);
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
	
	@Override
	public Set<Coupon> readAllCompanyCouponsUntilDate(long companyId, Date endDate) throws CouponSystemException {
		String sql = "select ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE from Company_Coupon INNER JOIN Coupon ON Company_Coupon.COUPON_ID = Coupon.ID WHERE Comp_ID = ? AND End_Date <= ?";
		Set<Coupon> set = new HashSet<>();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, companyId);
			pstmt.setDate(2, ConvertDate.convert(endDate));
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
