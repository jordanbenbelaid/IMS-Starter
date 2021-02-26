package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDaoTest {

	private OrderDAO DAO;
	
	@Before
	public void setup() {
		DAO = new OrderDAO();
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test     public void outputTableTest() {         
		try (Connection connection = DBUtils.getInstance().getConnection();                 
				Statement statement = connection.createStatement();                 
				ResultSet rs = statement.executeQuery("Show tables");) {             
			while (rs.next()) {                 
				System.out.println(rs.getString(1));             
				}         } 
		catch (Exception e) {             
			System.out.println(e.getMessage());         
			}     }
	
	@Test
	public void testReadLatest() {
		//given
		
		//when
		Order result = DAO.readLatest();
		
		//then
		Order expected = new Order(1L, new Customer(1L, "jordan", "harrison"), new ArrayList<Item>());
		
		assertEquals(expected, result);
	}
}
