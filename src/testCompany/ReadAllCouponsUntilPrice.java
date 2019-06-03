package testCompany;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class ReadAllCouponsUntilPrice {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		double price = Double.parseDouble(JOptionPane.showInputDialog("Enter coupon price"));
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setPrice(price);
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((CompanyFacade) fcd).getCouponsUntilPrice(coupon);
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
