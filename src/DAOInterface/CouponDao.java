package DAOInterface;

import java.util.Set;

import classes.Coupon;
import exceptions.CouponSystemException;

public interface CouponDao {

	/**
	 * Create a coupon in the system
	 * 
	 * @param coupon The coupon to be created in the system. This is a fully
	 *               initialized coupon object
	 * 
	 * @throws CouponSystemException throws a error massage if one of the values
	 *                               ​​received is incorrect
	 */
	void create(Coupon coupon) throws CouponSystemException;

	/**
	 * Read a coupon from the system
	 * 
	 * @param coupon The coupon wants to read from the system by id. This is a
	 *               coupon object with id initialized specifically.
	 * 
	 * @return The coupon specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Coupon read(Coupon coupon) throws CouponSystemException;

	/**
	 * Read a coupon from the system
	 * 
	 * @param coupon The coupon wants to read from the system by name. This is a
	 *               coupon object with id initialized specifically.
	 * 
	 * @return The coupon specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Coupon readName(Coupon coupon) throws CouponSystemException;

	/**
	 * get all expired in a list
	 * 
	 * @return list of coupons or null if not exist.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllExpired() throws CouponSystemException;

	/**
	 * update a coupon in the system
	 * 
	 * @param coupon The coupon to be updated in the system. This is a fully
	 *               initialized company object. The id initialized to the coupon to
	 *               be updated and the rest of the fields initialized as the
	 *               updated values.
	 * 
	 * @throws CouponSystemException throws a error massage If the resulting value
	 *                               does not exist or if connection to db was
	 *                               failed.
	 */
	void update(Coupon coupon) throws CouponSystemException;

	/**
	 * delete a coupon from the system
	 * 
	 * @param coupon The company to be deleted from the system. This is a coupon
	 *               object with id initialized specifically.
	 * 
	 * @throws CouponSystemException throws a error massage if The parameter
	 *                               received as the coupon's ID does not exist or
	 *                               if connection to db was failed.
	 */
	void delete(Coupon coupon) throws CouponSystemException;

	/**
	 * get all coupon from the system in a list
	 * 
	 * @return list of coupon or null if not exist.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> getAllCoupons() throws CouponSystemException;
}
