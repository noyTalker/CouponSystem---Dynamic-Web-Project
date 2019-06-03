package DAOInterface;

import java.util.Set;

import classes.Company;
import exceptions.CouponSystemException;

public interface CompanyDao {

	/**
	 * Create a company in the system
	 * 
	 * @param company The company to be created in the system. This is a fully
	 *                initialized company object
	 * @throws CouponSystemException throws a error massage if one of the values
	 *                               ​​received is incorrect
	 */
	void create(Company company) throws CouponSystemException;

	/**
	 * Read a company from the system
	 * 
	 * @param company The company wants to read from the system by id. This is a
	 *                company object with id initialized specifically.
	 * 
	 * @return The company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Company read(Company company) throws CouponSystemException;

	/**
	 * Read a company from the system
	 * 
	 * @param company The company wants to read from the system by name. This is a
	 *                company object with id initialized specifically.
	 * 
	 * @return The company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Company readName(Company company) throws CouponSystemException;

	/**
	 * update a company in the system
	 * 
	 * @param company The company to be updated in the system. This is a fully
	 *                initialized company object. The id initialized to the company
	 *                to be updated and the rest of the fields initialized as the
	 *                updated values.
	 * 
	 * @throws CouponSystemException throws a error massage If the resulting value
	 *                               does not exist or if connection to db was
	 *                               failed.
	 */
	void update(Company company) throws CouponSystemException;

	/**
	 * delete a company from the system
	 * 
	 * @param company The company to be deleted from the system. This is a company
	 *                object with id initialized specifically.
	 * 
	 * @throws CouponSystemException throws a error massage if The parameter
	 *                               received as the company's ID does not exist or
	 *                               if connection to db was failed.
	 */
	void delete(Company company) throws CouponSystemException;

	/**
	 * get all companies from the system in a list
	 * 
	 * @return list of companies or null if not exist.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Company> getAllCompanies() throws CouponSystemException;

}
