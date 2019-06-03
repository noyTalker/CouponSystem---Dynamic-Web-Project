package testCompany;

import java.util.HashSet;
import java.util.Set;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class ReadAllCoupons {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		Set<Coupon> set = new HashSet<>();
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((CompanyFacade) fcd).getAllCoupons();
			for (Coupon coupon : set) {
				System.out.println(coupon);
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
