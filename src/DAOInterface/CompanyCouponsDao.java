package DAOInterface;

import java.util.Date;
import java.util.Set;

import classes.Company;
import classes.CompanyCoupon;
import classes.Coupon;
import exceptions.CouponSystemException;

public interface CompanyCouponsDao {

	/**
	 * Create a company coupons in the system
	 * 
	 * @param companycoupon
	 * The coupons wants to created in the company object.
	 * 
	 * @throws CouponSystemException throws a error massage if one of the values
	 *                               ​​received is incorrect
	 */
	void create(CompanyCoupon companycoupon) throws CouponSystemException;

	/**
	 * delete a coupon from company
	 * 
	 * @param coupon
	 * The coupons to be deleted from the company by coupon ID.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	void deleteCoupons(Coupon coupon) throws CouponSystemException;
	
	/**
	 * delete a company from companyCoupon
	 * 
	 * @param company
	 * The coupons to be deleted from the companyCoupon by company ID.
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	void deleteCompany(Company company) throws CouponSystemException;

	/**
	 * Reads a company coupons from the system
	 * 
	 * @param company 
	 * The Coupons wants to read from the company.
	 * 
	 * @return The coupons of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<CompanyCoupon> read(Company company) throws CouponSystemException;
	
	/**
	 * Reads a coupon of company from the system
	 * 
	 * @param companyId 
	 * @param couponId 
	 * The Coupon wants to read from a specific company.
	 * 
	 * @return The coupon of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Coupon readCoupon(long companyId, long couponId) throws CouponSystemException;
	
	/**
	 * Reads all company coupons from the system
	 * 
	 * @param companyId 
	 * The Coupons wants to read from the company by company ID.
	 * 
	 * @return The coupons of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCompanyCoupons(long companyId) throws CouponSystemException;
	
	/**
	 * Reads all company coupons by type from the system
	 * 
	 * @param companyId 
	 * @param type
	 * The Coupons wants to read from the company by company ID and coupon type.
	 * 
	 * @return The coupons of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCompanyCouponsByType(long companyId, String type) throws CouponSystemException;
	
	/**
	 * Reads all company coupons until price from the system
	 * 
	 * @param companyId
	 * @param price
	 * The Coupons wants to read from the company by company ID and until coupon price.
	 * 
	 * @return The coupons of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCompanyCouponsUntilPrice(long companyId, double price) throws CouponSystemException;
	/**
	 * 
	 * @param companyId
	 * @param endDate
	 * The Coupons wants to read from the company by company ID and until coupon end date.
	 * 
	 * @return The coupons of the company specified or null if not found
	 * 
	 * @throws CouponSystemException throws a error massage if connection to db was
	 *                               failed.
	 */
	Set<Coupon> readAllCompanyCouponsUntilDate(long companyId, Date endDate) throws CouponSystemException;

}
