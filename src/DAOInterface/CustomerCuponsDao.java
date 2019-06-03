package DAOInterface;

import java.util.Set;

import classes.Coupon;
import classes.CustomerCoupon;
import exceptions.CouponSystemException;

public interface CustomerCuponsDao {

	/**
	 * Create a customer coupons in the system
	 * 
	 * @param customercoupon 
	 * The Coupons wants to created in the customer object.
	 * 
	 * @throws CouponSystemException throws a error massage if one of the values
	 *                               ​​received is incorrect
	 */
	void create(CustomerCoupon customercoupon) throws CouponSystemException;

	/**
	 * delete a coupon from customer
	 * 
	 * @param couponId 
	 * The coupons to be deleted from the customer by coupon ID.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	void deleteCoupons(long couponId) throws CouponSystemException;
	
	/**
	 * delete a customer from customerCoupon
	 * 
	 * @param couponId 
	 * The coupons to be deleted from the customerCoupon by customer ID.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	void deleteCustomer(long customerId) throws CouponSystemException;

	/**
	 * Reads a customer coupons from the system
	 * 
	 * @param customercoupon 
	 * The Coupons wants to read from the customer.
	 * 
	 * @return The coupons of the customer specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<CustomerCoupon> read(CustomerCoupon customercoupon) throws CouponSystemException;

	/**
	 * Reads all customer coupons from the system
	 * 
	 * @param customerId 
	 * The Coupons wants to read from the customer by customer ID.
	 * 
	 * @return The coupons of the customer specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCustomerCoupons(long customerId) throws CouponSystemException;
	
	/**
	 * Reads all customer coupons from the system
	 * 
	 * @param customerId 
	 * @param type
	 * The Coupons wants to read from the customer by customer ID and coupon type.
	 * 
	 * @return The coupons of the customer specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCustomerCouponsByType(long customerId, String type) throws CouponSystemException;
	
	/**
	 * Reads all customer coupons from the system
	 * 
	 * @param customerId 
	 * @param price
	 * The Coupons wants to read from the customer by customer ID and until price.
	 * 
	 * @return The coupons of the customer specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCustomerCouponsUntilPrice(long customerId, double price) throws CouponSystemException;
}
