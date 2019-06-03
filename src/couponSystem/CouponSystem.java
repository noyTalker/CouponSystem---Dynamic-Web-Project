package couponSystem;

import connectionPool.ConnectionPool;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CouponClientFacade;
import facade.CustomerFacade;
import thread.DailyCouponExpirationTask;

public class CouponSystem {

	private static CouponSystem instance = null;
	private DailyCouponExpirationTask t = new DailyCouponExpirationTask();
	private Thread task = new Thread(t);

	private CouponSystem() throws CouponSystemException {
		super();
		ConnectionPool.getInstance();
		task.start();
	}

	public static CouponSystem getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	public CouponClientFacade login(String name, String password, String clientType) throws CouponSystemException {
		boolean exist = false;
		switch (clientType) {
		case "AdminFacad":
			AdminFacade fcd1 = new AdminFacade();
			exist = fcd1.login(name, password);
			if (exist) {
				return fcd1;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		case "CompanyFacad":
			CompanyFacade fcd2 = new CompanyFacade();
			exist = fcd2.login(name, password);
			if (exist) {
				return fcd2;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		case "CustomerFacad":
			CustomerFacade fcd3 = new CustomerFacade();
			exist = fcd3.login(name, password);
			if (exist) {
				return fcd3;
			}else {
				throw new CouponSystemException("One of the values ​​set was incorrect");
			}
		default:
			break;
		}
		return null;
	}

	public synchronized void shutDown() throws CouponSystemException {
		t.stopTask();
		task.interrupt();
		try {
			task.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.closeAllConnections();
		instance = null;
	}

}
