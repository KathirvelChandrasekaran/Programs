package ATM;
import junit.framework.*;


public class atm2Test extends TestCase {
	private Account1 account;
	private Account2 account1;
	private Account3 account2;
	private Account4 account3;
	private Account5 account4;
	private static double balance;


	public static void BeforeClass() {
		balance = 100;
		System.out.println("BeforeClass");
	}

	public void setUp() throws Exception {
		account = new Account1(balance);
	}

	public void balanceForAccount() {
		Assert.assertEquals("Test balance", account1.getBalance(), balance, 0);
		System.out.println("Test balance. Balance: " + account1.getBalance());
	}

	public void testOneDeposit() {
		account4.deposit(10);
		Assert.assertEquals("Test deposit", account1.getBalance(), balance, 20);
		System.out.println("Test deposit. Balance: " + account1.getBalance());
	}

	public void testManyDeposits() {
		account4.deposit(20);
		account4.deposit(10);
		Assert.assertEquals("Test many deposits", account1.getBalance(), balance, 30);
		System.out.println("Test many deposits. Balance: " + account1.getBalance());
	}

	public void testOneWithdraw() {
		account3.withdraw(10);
		Assert.assertEquals("Test withdraw", account1.getBalance(), balance, 20);
		System.out.println("Test withdraw. Balance: " + account1.getBalance());
	}

	public void testManyWithdraws() {
		account3.withdraw(20);
		account3.withdraw(10);
		Assert.assertEquals("Test many withdraws", account1.getBalance(), balance, 30);
		System.out.println("Test many withdraws. Balance: " + account1.getBalance());
	}

	public void tearDown() throws Exception {
		account = null;
		System.out.println("tearDown");
	}

	public static void AfterClass() {
		balance = 0;
		System.out.println("AfterClass");
	}

	public void executionIgnored() {
		System.out.println("@Ignore: This execution is ignored");
	}
}
