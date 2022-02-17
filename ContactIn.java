package Map;

public class ContactIn 
{
	String firstN;
	String lastN;
	String phoneNum;
	String email;
	
	public ContactIn(String firstN, String lastN, String phoneNum, String email)
	{
		this.firstN = firstN;
		this.lastN = lastN;
		this.phoneNum = phoneNum;
		this.email = email;
	}
	public String getFirstN()
	{
		return firstN;
	}
	public void setFirstN(String firstN)
	{
		this.firstN = firstN;
	}
	public String getLastN()
	{
		return lastN;
	}
	public void setLastN(String lastN)
	{
		this.lastN = lastN;
	}
	public String getPhoneNum()
	{
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
}
