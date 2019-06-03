package classes;

public class CompanyCoupon {

	private long compId;
	private long couponId;
	
	
	public CompanyCoupon() {
		super();
	}

	public CompanyCoupon(long compId, long couponId) {
		super();
		this.compId = compId;
		this.couponId = couponId;
	
	}
	public long getCompId() {
		return compId;
	}
	public void setCompId(long compId) {
		this.compId = compId;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	@Override
	public String toString() {
		return "CompantCoupon [compId=" + compId + ", couponId=" + couponId + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (compId ^ (compId >>> 32));
		result = prime * result + (int) (couponId ^ (couponId >>> 32));
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
		CompanyCoupon other = (CompanyCoupon) obj;
		if (compId != other.compId)
			return false;
		if (couponId != other.couponId)
			return false;
		return true;
	}
	
	
}
