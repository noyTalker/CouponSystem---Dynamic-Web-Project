package testCompany;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class ReadCoupon {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter coupon id"));
		Coupon coupon = new Coupon();
		coupon.setId(id);
		System.out.println("serching ID " + id + " in the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			System.out.println(((CompanyFacade) fcd).getCoupon(id));
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
