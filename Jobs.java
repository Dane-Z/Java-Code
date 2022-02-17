package FIFO;

public class Jobs 
{
	int Id;
	int jobTime;
	public Jobs()
	{
	}
	/**
	 * 
	 * @param Id
	 * @param jobTime
	 */
	public Jobs(int Id, int jobTime)
	{
		this.Id = Id;
		this.jobTime = jobTime;
	}
	public int getId()
	{
		return Id;
	}
	public void setId()
	{
		this.Id = Id;
	}
	public int getJobTime()
	{
		return jobTime;
	}
	public void setJobTime()
	{
		this.jobTime = jobTime;
	}
	@Override
	public String toString()
	{
		return "Queue ID : " + Id + ", Job Time : " + jobTime;
	}
}
