package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

		private final ItemDAO DAO = new ItemDAO();
		
		@Before
		public void setup() {
			DBUtils.connect();
			DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		}
		
		@Test
		public void testCreateException() {
			Item item = new Item();
			assertNull(DAO.create(item));
		}
		
		@Test
		public void testCreate() {
			final Item created = new Item(2L, "chocolate", 1.5);
			assertEquals(created, DAO.create(created));
		}

		@Test
		public void testReadAll() {
			List<Item> expected = new ArrayList<>();
			expected.add(new Item(1L, "crisps", 3.5));
			assertEquals(expected, DAO.readAll());
		}
		
		@Test
		public void testReadLatestException() {
			DAO.delete(1L);
			assertNull(DAO.readLatest());
		}

		@Test
		public void testReadLatest() {
			assertEquals(new Item(1L, "crisps", 3.5), DAO.readLatest());
		}

		@Test
		public void testReadException() {
			DAO.delete(1L);
			assertNull(DAO.read(1L));
		}
		
		@Test
		public void testRead() {
			final long ID = 1L;
			assertEquals(new Item(ID, "crisps", 3.5), DAO.read(ID));
		}
		
		@Test
		public void testUpdateException() {
			Item item = new Item();
			assertNull(DAO.update(item));
		}

		@Test
		public void testUpdate() {
			final Item updated = new Item(1L, "chocolate", 3.5);
			assertEquals(updated, DAO.update(updated));
		}
		
//		@Test
//		public void testDeleteException() {
//			assertEquals(0, DAO.delete(6));
//		}

		@Test
		public void testDelete() {
			assertEquals(1, DAO.delete(1));
		}
}
