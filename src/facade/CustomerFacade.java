package facade;

import java.util.HashSet;
import java.util.Set;

import DAO.CouponDaoDb;
import DAO.CustomerCouponsDaoDb;
import DAO.CustomerDaoDb;
import DAOInterface.CouponDao;
import DAOInterface.CustomerCuponsDao;
import DAOInterface.CustomerDao;
import classes.Coupon;
import classes.Customer;
import classes.CustomerCoupon;
import exceptions.CouponSystemException;

public class CustomerFacade implements CouponClientFacade{

	private CustomerDao customerDao = new CustomerDaoDb();
	private CouponDao couponDao = new CouponDaoDb();
	private CustomerCuponsDao ccd = new CustomerCouponsDaoDb();
	
	private long idCustomer;
	
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		Coupon c = couponDao.read(coupon);
		if(c != null) {
			boolean exist = false;
			CustomerCoupon customercoupon = new CustomerCoupon(idCustomer, coupon.getId());
			Set<CustomerCoupon> set = new HashSet<>();
			set = ccd.read(customercoupon);
			for (CustomerCoupon customerCoupon2 : set) {
				if (customerCoupon2.getCouponId() == coupon.getId()) {
					exist = true;
					break;
				}
			}
			if (exist == false) {
				if (c.getAmount()>0) {
						ccd.create(customercoupon);
						c.setAmount(c.getAmount()-1);
						couponDao.update(c);
				} else {
					throw new CouponSystemException("The coupon with ID = '" + coupon.getId() + "' is out of stock");
				}
			} else {
				throw new CouponSystemException("The coupon with ID = '" + coupon.getId() + "' alredy used");
			}
		} else {
			throw new CouponSystemException("The coupon with ID = '" + coupon.getId() + "' is not exists");
		}
	}
	
	public Set<Coupon> getAllPurchaseCoupons() throws CouponSystemException{
		return ccd.readAllCustomerCoupons(idCustomer);
	}
	
	public Set<Coupon> getPurchaseCouponsByType(Coupon coupon) throws CouponSystemException{
		return ccd.readAllCustomerCouponsByType(idCustomer,coupon.getType().toString()); 
	}
	
	public Set<Coupon> getPurchaseCouponsUntilPrice(Coupon coupon) throws CouponSystemException{
		return ccd.readAllCustomerCouponsUntilPrice(idCustomer, coupon.getPrice());
	}
	
	public boolean login(String name, String password) throws CouponSystemException {
		Customer customer = new Customer();
		customer.setCustName(name);
		customer = customerDao.readName(customer);
		String pass = "";
		if(customer != null) {
			pass = customer.getPassword();			
		}
		if (pass.equals(password)) {
			idCustomer = customer.getId();
			return true;
		} else {
			return false;
		}
	}
}
