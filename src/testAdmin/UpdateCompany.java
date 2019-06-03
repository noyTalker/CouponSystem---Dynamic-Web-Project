package testAdmin;

import javax.swing.JOptionPane;

import classes.Company;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class UpdateCompany {

	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter company id"));
		String pass = JOptionPane.showInputDialog("Enter new company password");
		String eMail = JOptionPane.showInputDialog("Enter new company e-mail");
		Company company = new Company();
		company.setId(id);
		company.setPassword(pass);
		company.seteMail(eMail);
		System.out.println("about to update " + company + " in the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).updateCompany(company);
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
