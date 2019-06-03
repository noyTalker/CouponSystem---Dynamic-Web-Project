package thread;

import java.util.HashSet;
import java.util.Set;

import DAO.CompanyCouponsDaoDb;
import DAO.CouponDaoDb;
import DAO.CustomerCouponsDaoDb;
import DAOInterface.CompanyCouponsDao;
import DAOInterface.CouponDao;
import DAOInterface.CustomerCuponsDao;
import classes.Coupon;
import exceptions.CouponSystemException;

public class DailyCouponExpirationTask implements Runnable {

	private CouponDao couponDao = new CouponDaoDb();
	private CompanyCouponsDao cocd = new CompanyCouponsDaoDb();
	private CustomerCuponsDao cucd = new CustomerCouponsDaoDb();
	private boolean quit = false;

	public DailyCouponExpirationTask() {
		super();
	}

	@Override
	public void run() {
		while (!quit) {
			Set<Coupon> set = new HashSet<>();
			try {
				set = couponDao.readAllExpired();
				for (Coupon coupon : set) {
					couponDao.delete(coupon);
					cocd.deleteCoupons(coupon);
					cucd.deleteCoupons(coupon.getId());
				}
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public void stopTask() {
		quit = true;
	}
}
