package com.dxc.service;

import java.util.List;

import com.dxc.pojos.BankAdmin;
import com.dxc.pojos.UserDetails;

public interface IAdminService {
	
	public boolean authenticate(String admin_id,String password);
	
	public void adduser(UserDetails us);

	public UserDetails finduser(int accno);

	public boolean searchUsers(int accno);
	
	public void updateusers(UserDetails us);
	
	public double getBalance(int accno);
	
	public void removeuser(int accno);
	
	public List<UserDetails> getAllUsers();

	
	
	
	

}
