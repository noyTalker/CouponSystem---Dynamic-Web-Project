package DAOInterface;

import java.util.Set;

import classes.Customer;
import exceptions.CouponSystemException;

public interface CustomerDao {

	/**
	 * Create a customer in the system
	 * 
	 * @param customer The customer to be created in the system. This is a fully
	 *                 initialized customer object
	 * 
	 * @throws CouponSystemException throws a error massage if one of the values
	 *                               ​​received is incorrect
	 */
	void create(Customer customer) throws CouponSystemException;

	/**
	 * Read a customer from the system
	 * 
	 * @param customer The customer wants to read from the system by id. This is a
	 *                 customer object with id initialized specifically.
	 * 
	 * @return The customer specified or null if not found.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Customer read(Customer customer) throws CouponSystemException;

	/**
	 * Read a customer from the system
	 * 
	 * @param customer The customer wants to read from the system by name. This is a
	 *                 customer object with id initialized specifically.
	 * 
	 * @return The customer specified or null if not found.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Customer readName(Customer customer) throws CouponSystemException;

	/**
	 * update a customer in the system
	 * 
	 * @param customer The customer to be updated in the system. This is a fully
	 *                 initialized customer object. The id initialized to the
	 *                 customer to be updated and the rest of the fields initialized
	 *                 as the updated values.
	 * 
	 * @throws CouponSystemException throws a error massage If the resulting value
	 *                               does not exist or if connection to db was
	 *                               failed.
	 */
	void update(Customer customer) throws CouponSystemException;

	/**
	 * delete a customer from the system
	 * 
	 * @param customer The customer to be deleted from the system. This is a
	 *                 customer object with id initialized specifically.
	 * 
	 * @throws CouponSystemException throws a error massage if The parameter
	 *                               received as the customer's ID does not exist or
	 *                               if connection to db was failed.
	 */
	void delete(Customer customer) throws CouponSystemException;

	/**
	 * get all customers from the system in a list
	 * 
	 * @return list of customers or null if not exist.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Customer> getAllCustomers() throws CouponSystemException;

}
