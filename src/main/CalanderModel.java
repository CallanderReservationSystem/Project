package main;

public class CalanderModel 
{
	public String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public String[] days = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public int numberOfDays;
	public String month;
	public String firstDay;
	
	public CalanderModel(int month, String lastDay, boolean isLeapYear)
	{
		this.month = months[month];
		
		for (int i = 1; i <= days.length; i++)
		{
			if (days[i] == lastDay)
			{
				if (i == days.length)
				{
					firstDay = days[1];
				}
				else
				{
					firstDay = days[i + 1];
				}
			}
		}
		
		if (month == 2 && isLeapYear)
		{
			numberOfDays = 29;
		}
		else
		{
			if (month == 2)
			{
				numberOfDays = 28;
			}
			else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			{
				numberOfDays = 31;
			}
			else
			{
				numberOfDays = 30;
			}
		}
	}
}