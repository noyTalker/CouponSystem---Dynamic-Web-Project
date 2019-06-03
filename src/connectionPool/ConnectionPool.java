package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import exceptions.CouponSystemException;

public class ConnectionPool {

	private Set<Connection> set = new HashSet<>();
	public static final int MAX = 10;
	private static ConnectionPool instance;
	private String url = "jdbc:derby://localhost:1527/project";
	private boolean poolUp;

	private ConnectionPool() throws CouponSystemException  {
		poolUp = true;
		String driverName = "org.apache.derby.jdbc.ClientDriver";
		try {
			Class.forName(driverName);
			for (int i = 0; i < MAX; i++) {
				set.add(DriverManager.getConnection(url));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new CouponSystemException("ConnectionPool create failed", e);
		}
	}

	public static ConnectionPool getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() throws CouponSystemException {
		if (!poolUp) {
			throw new CouponSystemException("getConnection failed - pool is closed");
		}
		while (set.isEmpty()) {
			try {
				System.out.println("Connection failed - pool is empty, pleas wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Connection> it = set.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	public synchronized void returnConnection(Connection con) {
		set.add(con);
		notify();
	}

	public synchronized void closeAllConnections() {
		poolUp = false;
		while (set.size() < MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Iterator<Connection> it = set.iterator();
		while (it.hasNext()) {
			Connection con = it.next();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			it.remove();
		}
		instance = null;
	}
}
