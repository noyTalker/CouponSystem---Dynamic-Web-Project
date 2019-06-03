package testCompany;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import classes.Coupon;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class UpdateCoupon {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		long id = Integer.parseInt(JOptionPane.showInputDialog("Enter coupon id"));
		Date endDate = Date.valueOf(LocalDate.of(2019, 01, 31));
		double price = Double.parseDouble(JOptionPane.showInputDialog("Enter coupon price"));
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((CompanyFacade) fcd).updateCoupon(coupon);
			System.out.println("done");
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
