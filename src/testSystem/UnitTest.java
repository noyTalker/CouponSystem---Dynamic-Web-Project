package testSystem;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import classes.Company;
import classes.Coupon;
import classes.Customer;
import classes.Coupon.Type;
import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CouponClientFacade;
import facade.CustomerFacade;

public class UnitTest {

	public static void main(String[] args) {

		CouponSystem system = null;
//		String name = "admin";
//		String password = "123321";
//		String clientType = "AdminFacad";
		String name = "aaa";
		String password = "bbb";
		String clientType = "CompanyFacad";
//		String name = "aaaaa";
//		String password = "bbb";
//		String clientType = "CustomerFacad";
		CouponClientFacade ccf;
		try {
			system = CouponSystem.getInstance();
			ccf = system.login(name, password, clientType);
			if (clientType.equals("AdminFacad")) {
				Set<Company> set = new HashSet<>();
				long id = 101;
				String cName = "aaa";
				String pass = "aaa";
				String eMail = "aaa";
				Company company = new Company(id, cName, pass, eMail);
				System.err.println("====== Create a company ======");
				((AdminFacade) ccf).createCompany(company);
				id = 202;
				cName = "bbb";
				company.setId(id);
				company.setCompName(cName);
				System.err.println("====== Create another company ======");
				((AdminFacade) ccf).createCompany(company);
				set = ((AdminFacade) ccf).getAllCompanies();
				System.err.println("====== Show all companies ======");
				for (Company c : set) {
					System.out.println(c);
				}
				System.err.println("====== Delete a company ======");
				((AdminFacade) ccf).removeCompany(company);
				set = ((AdminFacade) ccf).getAllCompanies();
				System.err.println("====== Show all companies after deletion ======");
				for (Company c : set) {
					System.out.println(c);
				}
				id = 101;
				pass = "bbb";
				eMail = "bbb";
				company.setId(id);
				company.setPassword(pass);
				company.seteMail(eMail);
				System.err.println("====== Update a company ======");
				((AdminFacade) ccf).updateCompany(company);
				System.err.println("====== Read a company ======");
				System.out.println(((AdminFacade) ccf).getCompany(id));
				System.err.println("=============================================================");
				id = 103;
				cName = "aaaaa";
				pass = "aaa";
				Customer customer = new Customer(id, cName, pass);
				System.err.println("====== Create a customer ======");
				((AdminFacade) ccf).createCustomer(customer);
				id = 202;
				cName = "bbb";
				customer.setId(id);
				customer.setCustName(cName);
				System.err.println("====== Create another customer ======");
				((AdminFacade) ccf).createCustomer(customer);
				Set<Customer> set2 = new HashSet<>();
				set2 = ((AdminFacade) ccf).getAllCustomers();
				System.err.println("====== Show all customers ======");
				for (Customer c : set2) {
					System.out.println(c);
				}
				System.err.println("====== Delete a customer ======");
				((AdminFacade) ccf).removeCustomer(customer);
				set2 = ((AdminFacade) ccf).getAllCustomers();
				System.err.println("====== Show all customers after deletion ======");
				for (Customer c : set2) {
					System.out.println(c);
				}
				id = 103;
				pass = "bbb";
				customer.setId(id);
				customer.setPassword(pass);
				System.err.println("====== Update a customer ======");
				((AdminFacade) ccf).updateCustomer(customer);
				System.err.println("====== Read a customer ======");
				System.out.println(((AdminFacade) ccf).getCustomer(id));
			}
			if (clientType.equals("CompanyFacad")) {
				Set<Coupon> set = new HashSet<>();
				long id = 1;
				String title = "aaa";
				Date startDate = Date.valueOf(LocalDate.now());
				Date endDate = Date.valueOf(LocalDate.of(2019, 02, 20));
				int amount = 1;
				Coupon.Type type = Type.FOOD;
				String message = "aaaaa";
				double price = 50.5;
				String image = "aaaaa";
				Coupon coupon = new Coupon(id, title, startDate, endDate, amount, type, message, price, image);
				System.err.println("====== Create a coupon ======");
				((CompanyFacade) ccf).createCoupon(coupon);
				id = 3;
				title = "ccc";
				endDate = Date.valueOf(LocalDate.of(2019, 01, 10));
				coupon.setId(id);
				coupon.setTitle(title);
				coupon.setEndDate(endDate);
				System.err.println("====== Create another coupon ======");
				((CompanyFacade) ccf).createCoupon(coupon);
				id = 2;
				title = "bbb";
				type = Type.MOVIES;
				price = 30;
				endDate = Date.valueOf(LocalDate.of(2019, 01, 30));
				coupon.setId(id);
				coupon.setTitle(title);
				coupon.setType(type);
				coupon.setPrice(price);
				coupon.setEndDate(endDate);
				System.err.println("====== Create another coupon ======");
				((CompanyFacade) ccf).createCoupon(coupon);
				set = ((CompanyFacade) ccf).getAllCoupons();
				System.err.println("====== Show all coupons ======");
				System.err.println("====== And look that Coupon with ID 3 was removed because of the Thread ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
				Coupon coupon2 = new Coupon();
				coupon2.setType(type);
				set = ((CompanyFacade) ccf).getCouponsByType(coupon2);
				System.err.println("====== Show all company coupons by type ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
				coupon2.setPrice(price);
				set = ((CompanyFacade) ccf).getCouponsUntilPrice(coupon2);
				System.err.println("====== Show all company coupons until price ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
				coupon2.setEndDate(endDate);
				set = ((CompanyFacade) ccf).getCouponsUntilPrice(coupon2);
				System.err.println("====== Show all company coupons until date ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
				System.err.println("====== Delete a coupon ======");
				((CompanyFacade) ccf).removeCoupon(coupon);
				set = ((CompanyFacade) ccf).getAllCoupons();
				System.err.println("====== Show all company coupons after deletion ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
				id = 1;
				endDate = Date.valueOf(LocalDate.of(2019, 02, 15));
				price = 100;
				coupon.setId(id);
				coupon.setEndDate(endDate);
				coupon.setPrice(price);
				System.out.println("====== Update a coupon ======");
				((CompanyFacade) ccf).updateCoupon(coupon);
				System.err.println("====== Read a coupon ======");
				System.out.println(((CompanyFacade) ccf).getCoupon(coupon.getId()));
			}
			if (clientType.equals("CustomerFacad")) {
				Set<Coupon> set = new HashSet<>();
				long id = 1;
				Coupon coupon = new Coupon();
				coupon.setId(id);
				System.err.println("====== Purchase a coupon ======");
				((CustomerFacade) ccf).purchaseCoupon(coupon);
				set = ((CustomerFacade) ccf).getAllPurchaseCoupons();
				System.err.println("====== Show all customer purchase coupons deletion ======");
				for (Coupon c : set) {
					System.out.println(c);
				}
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				system.shutDown();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		}

	}

}
