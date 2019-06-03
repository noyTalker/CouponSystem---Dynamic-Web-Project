package dbCreatedTables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesInDB {

	public static void main(String[] args) {
		String url = "jdbc:derby://localhost:1527/project;create=true";
		String sql = "create table Company(ID bigint primary key,COMP_NAME varchar(20) not null,PASSWORD varchar(20),EMAIL varchar(20) not null)";
		try (Connection con = DriverManager.getConnection(url);Statement stmt = con.createStatement();) {
			stmt.executeUpdate(sql);
			sql = "create table Customer(ID bigint primary key,CUST_NAME varchar(20) not null,PASSWORD varchar(20))";
			stmt.executeUpdate(sql);
			sql = "create table Coupon(ID bigint primary key,TITLE varchar(20),START_DATE date,END_DATE date,AMOUNT int,TYPE varchar(20),MESSAGE varchar(50),PRICE float,IMAGE varchar(20))";
			stmt.executeUpdate(sql);
			sql = "create table Customer_Coupon(CUST_ID bigint,COUPON_ID bigint,primary key(CUST_ID,COUPON_ID))";
			stmt.executeUpdate(sql);
			sql = "create table Company_Coupon(COMP_ID bigint,COUPON_ID bigint,primary key(COMP_ID,COUPON_ID))";
			stmt.executeUpdate(sql);
			System.out.println("created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
