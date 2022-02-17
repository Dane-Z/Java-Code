package FIFO;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;

public class PrintJob 
{
	public static void main(String [] args)
	{
		LinkedList<Jobs> jobList = new LinkedList<Jobs>();
		Scanner in = new Scanner(System.in);
		Random rnGen = new Random();
		String userIn;
		int Id;
		int jobTime;
		while(true)
		{
			System.out.println("This is a print queue! To add a job please input an integer ID, or input 'close' to finish.");
			userIn = in.next();
			if(userIn.equalsIgnoreCase("close"))
			{
				break;
			}
			else
			{
				/**
				 * @param Id
				 * @param jobTime
				 * fed into new Jobs object, "jobby,"
				 * which is then added to the LinkedList
				 */
				Id = Integer.parseInt(userIn);
				jobTime = rnGen.nextInt(1000) + 10;
				Jobs jobby = new Jobs(Id, jobTime);
				jobList.add(jobby);
			}
		}
		/**
		 * prints out the content of jobList after userInput using the Jobs class toString
		 */
		System.out.println("Queue:");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println( jobList.get(i));
			System.out.println();
		}
		
	}
}
