package com.db.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.logutil.Logger;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;



public class DataSourceUtil {
	public static DataSource ds ;
	static {
		try {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("db.properties");
		props.load(fis);
		MysqlDataSource mysqlDS = new MysqlConnectionPoolDataSource();
		mysqlDS.setURL(props.getProperty("db_url"));
		mysqlDS.setUser(props.getProperty("db_username"));
		mysqlDS.setPassword(props.getProperty("db_password"));
//		DataBase connection log
//		Logger.log("DataBase connected with the following properties \n");
//		Logger.log("url:" + props.getProperty("db_url")+"\n");
//		Logger.log("username " + props.getProperty("db_username")+"\n");
//		Logger.log("password " + props.getProperty("db_password")+"\n");
		ds = mysqlDS;
		} catch (Exception e) {
			Logger.log("Exception while creating data source message" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		
		return ds;
	}
}
