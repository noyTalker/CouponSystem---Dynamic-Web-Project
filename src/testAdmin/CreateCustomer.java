package testAdmin;

import javax.swing.JOptionPane;

import classes.Customer;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class CreateCustomer {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter your id"));
		String custName = JOptionPane.showInputDialog("Enter customer name");
		String pass = JOptionPane.showInputDialog("Enter your password");
		Customer cust = new Customer(id, custName, pass);
		System.out.println("about to add " + cust + " to the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).createCustomer(cust);
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
