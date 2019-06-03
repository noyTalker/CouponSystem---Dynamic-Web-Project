package testAdmin;

import javax.swing.JOptionPane;

import classes.Company;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class CreateCompany {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		long id = Integer.parseInt(JOptionPane.showInputDialog("Enter company id"));
		String compName = JOptionPane.showInputDialog("Enter company name");
		String pass = JOptionPane.showInputDialog("Enter company password");
		String eMail = JOptionPane.showInputDialog("Enter company e-mail");
		Company company = new Company(id,compName,pass,eMail);
		System.out.println("about to add " + company + " to the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).createCompany(company);
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
