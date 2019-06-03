package facade;

import java.util.HashSet;
import java.util.Set;

import DAO.CompanyCouponsDaoDb;
import DAO.CompanyDaoDb;
import DAO.CouponDaoDb;
import DAO.CustomerCouponsDaoDb;
import DAO.CustomerDaoDb;
import DAOInterface.CompanyCouponsDao;
import DAOInterface.CompanyDao;
import DAOInterface.CouponDao;
import DAOInterface.CustomerCuponsDao;
import DAOInterface.CustomerDao;
import classes.Company;
import classes.CompanyCoupon;
import classes.Coupon;
import classes.Customer;
import exceptions.CouponSystemException;

public class AdminFacade implements CouponClientFacade {

	private CouponDao couponDao = new CouponDaoDb();
	private CompanyDao companyDao = new CompanyDaoDb();
	private CustomerDao customerDao = new CustomerDaoDb();
	private CompanyCouponsDao cocd = new CompanyCouponsDaoDb();
	private CustomerCuponsDao cucd = new CustomerCouponsDaoDb();
	
	private String name = "admin";
	private String password = "123321";

	public void createCompany(Company company) throws CouponSystemException {
		if (companyDao.read(company) == null) {
			if (companyDao.readName(company) == null) {
				companyDao.create(company);
			} else {
				throw new CouponSystemException("The company name = '" + company.getCompName() + "' already exists");
			}
		} else {
			throw new CouponSystemException("The company ID = '" + company.getId() + "' already exists");
		}
	}

	public void removeCompany(Company company) throws CouponSystemException {
		companyDao.delete(getCompany(company.getId()));
		Set<CompanyCoupon> set = new HashSet<>();
		set = cocd.read(company);
		for (CompanyCoupon c : set) {
			cucd.deleteCoupons(c.getCouponId());
			Coupon coupon = new Coupon();
			coupon.setId(c.getCouponId());
			couponDao.delete(coupon);
		}
		cocd.deleteCompany(company);
	}

	public void updateCompany(Company company) throws CouponSystemException {
		Company comp = getCompany(company.getId());
		comp.setPassword(company.getPassword());
		comp.seteMail(company.geteMail());
		companyDao.update(comp);
	}

	public Company getCompany(long id) throws CouponSystemException {
		Company company = new Company();
		company.setId(id);
		company = companyDao.read(company);
		if (company != null) {
			return company;
		} else {
			throw new CouponSystemException("The company is not exist");
		}
	}

	public Set<Company> getAllCompanies() throws CouponSystemException {
		return companyDao.getAllCompanies();
	}

	public void createCustomer(Customer customer) throws CouponSystemException {
		if (customerDao.read(customer) == null) {
			if (customerDao.readName(customer) == null) {
				customerDao.create(customer);
			} else {
				throw new CouponSystemException("The customer name = '" + customer.getCustName() + "' already exists");
			}
		} else {
			throw new CouponSystemException("The customer ID = '" + customer.getId() + "' already exists");
		}
	}

	public void removeCustomer(Customer customer) throws CouponSystemException {
		customerDao.delete(getCustomer(customer.getId()));
		cucd.deleteCustomer(customer.getId());
	}

	public void updateCustomer(Customer customer) throws CouponSystemException {
		Customer cust = getCustomer(customer.getId());
		cust.setPassword(customer.getPassword());
		customerDao.update(cust);
	}

	public Customer getCustomer(long id) throws CouponSystemException {
		Customer customer = new Customer();
		customer.setId(id);
		customer = customerDao.read(customer);
		if (customer != null) {
			return customer;
		} else {
			throw new CouponSystemException("The customer is not exist");
		}
	}

	public Set<Customer> getAllCustomers() throws CouponSystemException {
		return customerDao.getAllCustomers();
	}
	
	public boolean login(String name,String password) {
		if(this.name.equals(name)&&this.password.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
}
