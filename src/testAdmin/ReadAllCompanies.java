package testAdmin;

import java.util.HashSet;
import java.util.Set;

import classes.Company;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class ReadAllCompanies {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		Set<Company> set = new HashSet<>();
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((AdminFacade)fcd).getAllCompanies();
			for (Company company : set) {
				System.out.println(company);
			}
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
