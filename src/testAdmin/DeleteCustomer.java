package testAdmin;

import javax.swing.JOptionPane;

import classes.Customer;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class DeleteCustomer {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id"));
		Customer cust = new Customer();
		cust.setId(id);
		System.out.println("deleting customer where id = " + id + " from the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).removeCustomer(cust);
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
