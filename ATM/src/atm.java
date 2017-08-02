import java.util.Scanner;


public class atm 
{
	
    public static void main(String[] args)
    {
         Scanner keyboard = new Scanner(System.in);
         
         int amount = 0;
         int choice = 0;
         int [] transactions = new int[10];
         int sum;
         int balance = 0;
         
         while (choice != 4)
         {
         	choice = menu();
         	switch(choice)
         	{
         		case 1:
         			System.out.print("Enter amount to deposit? :");
         			sum = keyboard.nextInt();
         				if(sum == 0)
         				{
         				System.out.print("Wrong amount.");
         				System.out.println();
         				System.out.println();
         				}
         				else
         				{
         				amount = (int) + sum;
         				makeTransactions(amount, transactions);
         				}
         				   break;
         		
         		case 2:
         				System.out.print("Hoe much do you want to withdrawl?: ");
         				sum = keyboard.nextInt();
         					if(sum == 0)
         					{
         						System.out.print("Wrong amount.");
         						System.out.println();
         						System.out.println();
         					}
         						else
         						{
         							amount = (int) - sum;
         							makeTransactions(amount, transactions);
         						}
         						break;
         		
         		case 3:
         				showTransactions(transactions, balance);
         				break;
         				
         		case 4:
         				System.out.println("You chosed to end ");
         				break;
         	}	
         	
         }
    }
    
    
    	public static int menu()
    	{
    		Scanner keyboard = new Scanner(System.in);
    		int choice = 0;
    		
    		System.out.println("Simple ATM");
    		System.out.println();
    		System.out.println("1. Deposit ");
    		System.out.println("2. Withdrawl ");
    		System.out.println("3. transactions ");
    		System.out.println("4. End ");
    		System.out.println();
    		System.out.println("Your choice ");
    		
    		choice = keyboard.nextInt();
    		return choice;
    	}
    	
		public static void showTransactions(int [] transactions, int balance)
		{
			System.out.println();
			System.out.println("Last transactions");
			System.out.println();
			
				for(int i = 0; i < transactions.length-1; i++)
				{
					if(transactions[i] == 0)
					{
						System.out.print("");
					}
					
						else
						{
							System.out.print(transactions[i] + "\n");
							balance = balance + transactions[i];
						
						}
			
				}
			System.out.println();
			System.out.println("Balance: " + balance + " kr" + "\n" );
			System.out.println();		

		}
		
		public static void makeTransactions(int amount, int [] transactions)
		{
			int position = findNr(transactions);
			if(position == -1)
			{
				moveTrans(transactions);
					position = findNr(transactions);
					transactions[position] = amount;
			}
			else
			{
				transactions[position] = amount;
			}	
				
		}
		
		public static int findNr(int [] transactions)
		{
			int position = -1;
			
			for(int i = 0; i < transactions.length-1; i++)
			{
				if(transactions[i] == 0)
				{
					position = i;
					break;
				}	
			}
			return position;
		}
		
		public static void moveTrans(int [] transactions)
		{
			for(int i = 0; i < transactions.length-1; i++)
				
				transactions[0] = transactions[i + 1] ;
				
		}
		

}