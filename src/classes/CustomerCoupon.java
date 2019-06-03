package classes;

public class CustomerCoupon {

	private long custId;
	private long couponId;
	
	public CustomerCoupon() {
		super();
	}

	public CustomerCoupon(long custId, long couponId) {
		super();
		this.custId = custId;
		this.couponId = couponId;
	}
	
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	@Override
	public String toString() {
		return "CustomerCoupon [custId=" + custId + ", couponId=" + couponId + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (couponId ^ (couponId >>> 32));
		result = prime * result + (int) (custId ^ (custId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerCoupon other = (CustomerCoupon) obj;
		if (couponId != other.couponId)
			return false;
		if (custId != other.custId)
			return false;
		return true;
	}
	
	
}
