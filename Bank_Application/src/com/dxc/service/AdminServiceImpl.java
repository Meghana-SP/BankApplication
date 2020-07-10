package com.dxc.service;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.UserDetails;


public class AdminServiceImpl implements IAdminService {

	IAdminDao dao=new AdminDaoImpl();
	
	@Override
	public boolean authenticate(String admin_id,String password)
	{
		return dao.authenticate(admin_id, password);
	}

	@Override
	public void adduser(UserDetails us) {
		
		dao.adduser(us);
		
	}
	
	@Override
	public UserDetails finduser(int accno) {
		
		return dao.finduser(accno);
	}
	
	@Override
	public boolean searchUsers(int accno) {
		return dao.searchUsers(accno);
	}
	
	public void updateusers(UserDetails us)
	{
		dao.updateusers(us);
	}
	
	@Override
	public double getBalance(int accno)
	{
		return dao.getBalance(accno);
	}
	
	@Override
	public void removeuser(int accno)
	{
		dao.removeuser(accno);
	}
	
	@Override
	public List<UserDetails> getAllUsers() {
		
		return dao.getAllUsers();
	}

}
