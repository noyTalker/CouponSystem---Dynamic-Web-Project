package testCustomer;

import java.util.HashSet;
import java.util.Set;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CouponClientFacade;
import facade.CustomerFacade;

public class readAllPurchaseCoupons {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaaaa";
		String password = "bbb";
		String clientType = "CustomerFacad";
		Set<Coupon> set = new HashSet<>();
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((CustomerFacade) fcd).getAllPurchaseCoupons();
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
