package DAO;

import java.sql.Date;

public class ConvertDate {
	
	public static Date convert(java.util.Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

}
