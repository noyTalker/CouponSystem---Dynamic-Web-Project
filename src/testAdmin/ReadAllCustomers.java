package testAdmin;

import java.util.HashSet;
import java.util.Set;

import classes.Customer;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CouponClientFacade;

public class ReadAllCustomers {

	public static void main(String[] args) {
		
		CouponSystem system = null;
		String name = "admin";
		String password = "123321";
		String clientType = "AdminFacad";
		Set<Customer> set = new HashSet<>();
		CouponClientFacade fcd;
		try {
			system = CouponSystem.getInstance();
			fcd = system.login(name, password, clientType);
			set = ((AdminFacade)fcd).getAllCustomers();
			for (Customer customer : set) {				
				System.out.println(customer);
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
