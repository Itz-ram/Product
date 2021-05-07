package com.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.logutil.Logger;
import com.model.Product;

public class DataBaseUtil {
	
	public static List<Product> initProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			Connection conn = DataSourceUtil.getDataSource().getConnection();
			Statement stmt = conn.createStatement();

		      String sql = "SELECT * FROM PRODUCT";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		         Product product = new Product(
		        		 rs.getInt(1),
		        		 rs.getString(2),
		        		 rs.getString(3),
		        		 rs.getDouble(4),
		        		 rs.getInt(5)
		        		 );
		         products.add(product);

		      }
		      rs.close();
		      conn.close();
		} catch (SQLException e) {
			Logger.log(e.getMessage());
		}
		
		
		
		return products;
	}
	
	public static void create(Product product) {
		
		try {
			Connection conn = DataSourceUtil.getDataSource().getConnection();
			PreparedStatement stmt=conn.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?,?)");  
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setDouble(4, product.getUnitPrice());
			stmt.setInt(5, product.getInStock());
			stmt.execute();
			conn.close();
		} catch (SQLException e) {
			Logger.log(e.getMessage());
		}
	}
	
	public static void delete(Product product) {
		try {
			Connection conn = DataSourceUtil.getDataSource().getConnection();
			PreparedStatement stmt=conn.prepareStatement("DELETE FROM PRODUCT WHERE ID = ?");
			stmt.setInt(1, product.getId());
			stmt.execute();
			conn.close();
		} catch (SQLException e) {
			Logger.log(e.getMessage());
		}
	}
	
	public static void update(Product product) {
		try {
			Connection conn = DataSourceUtil.getDataSource().getConnection();
			PreparedStatement stmt=conn.prepareStatement("UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, UNITPRICE = ?, INSTOCK =? WHERE ID = ?");  
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getDescription());
			stmt.setDouble(3, product.getUnitPrice());
			stmt.setInt(4, product.getInStock());
			stmt.setInt(5, product.getId());
			stmt.execute();
			conn.close();
		} catch (SQLException e) {
			Logger.log(e.getMessage());
		}
	}

}
