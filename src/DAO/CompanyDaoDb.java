package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import DAOInterface.CompanyDao;
import classes.Company;
import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;

public class CompanyDaoDb implements CompanyDao {

	@Override
	public void create(Company company) throws CouponSystemException {
		String sql = "insert into Company values(?,?,?,?)";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setLong(1, company.getId());
			pstmt.setString(2, company.getCompName());
			pstmt.setString(3, company.getPassword());
			pstmt.setString(4, company.geteMail());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("create company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Company read(Company company) throws CouponSystemException {
		String sql = "select * from Company where id = ?";
		Company c = new Company();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, company.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setCompName(rs.getString("COMP_NAME"));
					c.setPassword(rs.getString("PASSWORD"));
					c.seteMail(rs.getString("EMAIL"));
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
	public Company readName(Company company) throws CouponSystemException {
		String sql = "select * from Company where COMP_NAME = ?";
		Company c = new Company();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, company.getCompName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c.setId(rs.getLong("ID"));
					c.setCompName(rs.getString("COMP_NAME"));
					c.setPassword(rs.getString("PASSWORD"));
					c.seteMail(rs.getString("EMAIL"));
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
	public void update(Company company) throws CouponSystemException {
		String sql = "update Company set Comp_Name = ?, Password = ?, Email = ? WHERE ID = " + company.getId();
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.geteMail());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			throw new CouponSystemException("update company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public void delete(Company company) throws CouponSystemException {
		String sql = "DELETE FROM Company WHERE ID = ?";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
			pstmt.close();
		} catch (SQLException e) {
			throw new CouponSystemException("delete company failed", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}

	@Override
	public Set<Company> getAllCompanies() throws CouponSystemException {
		Set<Company> set = new HashSet<>();
		String sql = "select * from Company";
		Connection con = ConnectionPool.getInstance().getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				Company c = new Company();
				c.setId(rs.getLong("ID"));
				c.setCompName(rs.getString("COMP_NAME"));
				c.setPassword(rs.getString("PASSWORD"));
				c.seteMail(rs.getString("EMAIL"));
				set.add(c);
			}
			return set;
		} catch (SQLException e) {
			throw new CouponSystemException("get all companies faled", e);
		} finally {
			ConnectionPool.getInstance().returnConnection(con);
		}
	}
}
