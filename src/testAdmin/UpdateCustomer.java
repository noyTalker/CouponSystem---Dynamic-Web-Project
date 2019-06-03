package testAdmin;

import javax.swing.JOptionPane;

import classes.Customer;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class UpdateCustomer {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		long id = Integer.parseInt(JOptionPane.showInputDialog("Enter your id"));
		String pass = JOptionPane.showInputDialog("Enter your new password");
		Customer cust = new Customer();
		cust.setId(id);
		cust.setPassword(pass);
		System.out.println("about to update " + cust + " in the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).updateCustomer(cust);
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
