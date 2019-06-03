package testCompany;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class ReadAllCouponsUntilDate {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		Date endDate = Date.valueOf(LocalDate.of(2019, 12, 30));
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setEndDate(endDate);
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((CompanyFacade) fcd).getCouponsUntilDate(coupon);
			for (Coupon c : set) {
				System.out.println(c);
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}finally {
			try {
				system.shutDown();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		}

	}
}
