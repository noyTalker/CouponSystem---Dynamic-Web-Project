package testCustomer;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CouponClientFacade;
import facade.CustomerFacade;

public class PurchaseCoupon {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaaaa";
		String password = "bbb";
		String clientType = "CustomerFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter coupon id"));
		Coupon coupon = new Coupon();
		coupon.setId(id);
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((CustomerFacade) fcd).purchaseCoupon(coupon);
			System.out.println("coupon aded");
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
