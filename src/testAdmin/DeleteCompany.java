package testAdmin;

import javax.swing.JOptionPane;

import classes.Company;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class DeleteCompany {
	
	public static void main(String[] args) {

		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		long id = Integer.parseInt(JOptionPane.showInputDialog("Enter company id"));
		Company comp = new Company();
		comp.setId(id);
		System.out.println("deleting company where id = " + id + " from the system");
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			((AdminFacade)fcd).removeCompany(comp);
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
