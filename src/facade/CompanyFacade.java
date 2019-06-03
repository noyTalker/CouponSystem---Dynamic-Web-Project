package facade;

import java.util.Set;

import DAO.CompanyCouponsDaoDb;
import DAO.CompanyDaoDb;
import DAO.CouponDaoDb;
import DAO.CustomerCouponsDaoDb;
import DAOInterface.CompanyCouponsDao;
import DAOInterface.CompanyDao;
import DAOInterface.CouponDao;
import DAOInterface.CustomerCuponsDao;
import classes.Company;
import classes.CompanyCoupon;
import classes.Coupon;
import exceptions.CouponSystemException;

public class CompanyFacade implements CouponClientFacade {

	private CompanyDao companyDao = new CompanyDaoDb();
	private CouponDao couponDao = new CouponDaoDb();
	private CompanyCouponsDao cocd = new CompanyCouponsDaoDb();
	private CustomerCuponsDao cucd = new CustomerCouponsDaoDb();

	private long idCompany;

	public void createCoupon(Coupon coupon) throws CouponSystemException {
		if (couponDao.read(coupon) == null) {
			if (couponDao.readName(coupon) == null) {
				couponDao.create(coupon);
				CompanyCoupon companycoupon = new CompanyCoupon(idCompany, coupon.getId());
				cocd.create(companycoupon);
			} else {
				throw new CouponSystemException("The coupon title = '" + coupon.getTitle() + "' already exists");
			}
		} else {
			throw new CouponSystemException("The coupon ID = '" + coupon.getId() + "' already exists");
		}
	}

	public void removeCoupon(Coupon coupon) throws CouponSystemException {
		couponDao.delete(getCoupon(coupon.getId()));
		cocd.deleteCoupons(coupon);
		cucd.deleteCoupons(coupon.getId());

	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon c = getCoupon(coupon.getId());
		c.setEndDate(coupon.getEndDate());
		c.setPrice(coupon.getPrice());
		couponDao.update(c);
	}

	public Coupon getCoupon(long id) throws CouponSystemException {
		Coupon coupon = new Coupon();
		coupon = cocd.readCoupon(idCompany, id);
		if (coupon != null) {
			return coupon;
		} else {
			throw new CouponSystemException("The coupon is not exist");
		}
	}

	public Set<Coupon> getAllCoupons() throws CouponSystemException {
		return cocd.readAllCompanyCoupons(idCompany);
	}

	public Set<Coupon> getCouponsByType(Coupon coupon) throws CouponSystemException {
		return cocd.readAllCompanyCouponsByType(idCompany, coupon.getType().toString());
	}

	public Set<Coupon> getCouponsUntilPrice(Coupon coupon) throws CouponSystemException {
		return cocd.readAllCompanyCouponsUntilPrice(idCompany, coupon.getPrice());
	}

	public Set<Coupon> getCouponsUntilDate(Coupon coupon) throws CouponSystemException {
		return cocd.readAllCompanyCouponsUntilDate(idCompany, coupon.getEndDate());
	}
	
	public boolean login(String name, String password) throws CouponSystemException {
		Company company = new Company();
		company.setCompName(name);
		company = companyDao.readName(company);
		String pass = "";
		if(company != null) {
			pass = company.getPassword();			
		}
		if (pass.equals(password)) {
			idCompany = company.getId();
			return true;
		} else {
			return false;
		}
	}
}
