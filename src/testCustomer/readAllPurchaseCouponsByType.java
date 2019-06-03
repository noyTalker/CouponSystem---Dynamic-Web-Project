package testCustomer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CouponClientFacade;
import facade.CustomerFacade;

public class readAllPurchaseCouponsByType {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CustomerFacad";
		Coupon.Type type = Coupon.Type
				.valueOf(JOptionPane.showInputDialog("enter coupon type " + Arrays.toString(Coupon.Type.values())));
		Coupon coupon = new Coupon();
		coupon.setType(type);
		Set<Coupon> set = new HashSet<>();
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((CustomerFacade) fcd).getPurchaseCouponsByType(coupon);
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
