package testCompany;

import java.sql.Date;
import java.time.LocalDate;
//import java.util.Arrays;

import javax.swing.JOptionPane;

import classes.Coupon;
import classes.Coupon.Type;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import facade.CouponClientFacade;

public class CreateCoupon {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter coupon id"));
		String title = JOptionPane.showInputDialog("Enter the title of the coupon");
		Date startDate = Date.valueOf(LocalDate.now());
		Date endDate = Date.valueOf(LocalDate.of(2018, 01, 22));
//		int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity of coupons"));
		int amount = 10;
//		Coupon.Type type = Coupon.Type
//				.valueOf(JOptionPane.showInputDialog("enter coupon type " + Arrays.toString(Coupon.Type.values())));
		Coupon.Type type = Type.FOOD;
//		String message = JOptionPane.showInputDialog("Enter the message of the coupon");
		String message = "aaa";
		double price = Double.parseDouble(JOptionPane.showInputDialog("Enter coupon price"));
//		String image = JOptionPane.showInputDialog("Put a link to the image");
		String image = "aaa";
		Coupon coupon = new Coupon(id, title, startDate, endDate, amount, type, message, price,
				image);
		System.out.println("about to add " + coupon + " to the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((CompanyFacade) fcd).createCoupon(coupon);
			System.out.println("Created");
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
