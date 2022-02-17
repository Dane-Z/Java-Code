package Map;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class ContactList 
{
	public static void main(String [] args) throws IOException
	{
		TreeMap< String, ContactIn > contacts = new TreeMap< String, ContactIn >(); 
		Scanner in = new Scanner(System.in);
		System.out.println("------Contact List information------\nPlease input name of file where information is stored: ");
		String fileName = in.next();
		File fileOut = new File(fileName);
		PrintWriter output = new PrintWriter(new FileWriter(fileOut, true));
		if(fileOut.exists())
		{
			System.out.println("Appending File: ");
		}
		else 
		{
			System.out.println("New File will be created!");
		}
		int options = 0;
		while(options == 0 && options != 3)
		{
			System.out.println("Input (1) to Add a contact, (2) to Delete a contact, or (3) to Display the Contact List");
			options = in.nextInt();
			while(options != 3)
			{
				//add contact (first, last, phone number, email) to object then to tree map
				if(options == 1)
				{
					System.out.println("Please input: First name, Last name, Phone Number, and Email Address.");
					String firstN = in.next();
					String lastN = in.next();
					String phoneNum = in.next();
					String email = in.next();
					ContactIn in1 = new ContactIn(firstN, lastN, phoneNum, email);
					contacts.put(in1.getLastN(), in1);
					options = 0;
					break;
				}
				//delete a specified contact
				else if(options == 2)
				{
					for(Map.Entry contact : contacts.entrySet())
					{
						System.out.println("Contact List:");
						for(Map.Entry<String, ContactIn> entries : contacts.entrySet())
						{
						ContactIn i = contacts.get(entries.getKey() );
						System.out.println(i.getLastN() + ", " + i.getFirstN() + " : " + i.getPhoneNum() + "   " + i.getEmail());
						}
					}
					System.out.println("Please input the Last name of the specific contact you wish to remove");
					String lastRem = in.next();
					contacts.remove(lastRem);
					if(lastRem != null)
					{
						System.out.println("Contact has been removed.");
					}
					else
					{
						System.out.println("Contact doesn't exist!");
					}
					options = 0;
					break;
				}
				//catches and returns user to prompt if input is anything other than 1-3
				else
				{
					options = 0;
					break;
				}
			}
		}
		//print and write to specific file 
		for(Map.Entry<String, ContactIn> contact : contacts.entrySet())
		{
			System.out.println("Contact List:\n");
			output.println("Contact List:\n");
			ContactIn i = contacts.get(contact.getKey() );
			output.println(i.getLastN() + ", " + i.getFirstN() + " : " + i.getPhoneNum() + "   " + i.getEmail());
			System.out.println(i.getLastN() + ", " + i.getFirstN() + " : " + i.getPhoneNum() + "   " + i.getEmail());
			output.flush();
		}	
		output.close();
	}
}
