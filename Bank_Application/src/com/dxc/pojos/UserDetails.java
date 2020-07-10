package com.dxc.pojos;

public class UserDetails {
	
	private int accno;
	private String uname;
    private double accbalance;
    private int user_id;
    private String password;
    
 	public UserDetails() {
    	
    }

	public UserDetails(int accno, String uname, double accbalance,int user_id,String password) {
		super();
		this.accno = accno;
		this.uname = uname;
		this.accbalance = accbalance;
		this.user_id=user_id;
		this.password=password;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public double getAccbalance() {
		return accbalance;
	}

	public void setAccbalance(double accbalance) {
		this.accbalance = accbalance;
	}

	   public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
}
