package testCompany;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class DeleteCoupon {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter company id"));
		Coupon coupon = new Coupon();
		coupon.setId(id);
		System.out.println("deleting company where id = " + id + " from the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((CompanyFacade) fcd).removeCoupon(coupon);
			System.out.println("done!");
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
