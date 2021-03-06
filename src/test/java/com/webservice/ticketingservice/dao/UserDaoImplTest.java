package com.webservice.ticketingservice.dao;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webservice.ticketingservice.model.User;

@Transactional
public class UserDaoImplTest extends EntityDaoImplTest {

	@Autowired
	UserDao userDao;
	
//	@Override
//	protected IDataSet getDataSet() throws Exception {
//	  IDataSet[] datasets = new IDataSet[] {
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Status.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Severity.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Ticket.xml")),
//			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Correspondence.xml"))
//	  };
//	  return new CompositeDataSet(datasets);
//	}
	
	@Test
	public void createUserTest() {
		userDao.createUser(getSampleUser());
		Assert.assertEquals(userDao.getUsers().size(), 4);
	}
	
	@Test
	public void updateUserTest() {
		User user = getSampleUser();
		userDao.createUser(user);
		user.setName("New Arvind");
		userDao.updateUser(user);
		Assert.assertEquals(userDao.getUsers().size(), 4);
	}
	
	@Test
	public void getUserByIdTest() {
		User user = getSampleUser();
		userDao.createUser(user);
		Assert.assertEquals(userDao.getUserById(user.getId()), user);
	}
	
	@Test
	public void getUserByIdTestWithNonExistantId() {
		User user = getSampleUser();
		userDao.createUser(user);
		Assert.assertNotEquals(userDao.getUserById(100), user);
	}
	
	@Test
	public void getUserByHandleTest() {
		User user = getSampleUser();
		userDao.createUser(user);
		
		Assert.assertEquals(userDao.getUserByHandle(user.getHandle()), user);
	}
	
	@Test
	public void getUsersTest() {
		Assert.assertEquals(userDao.getUsers().size(), 3);
	}
	
	private User getSampleUser() {
		User user = new User();
		user.setName("Arvind");
		user.setTitle("CEO");
		user.setHandle("arvind.2389");
		return user;
	}


}
