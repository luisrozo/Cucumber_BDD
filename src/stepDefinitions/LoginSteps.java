package stepDefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	
	// Cucumber hooks class
	
	WebDriver driver = Hooks.driver;
	
	// Generic first step for multiple scenarios
	
	@Given("the user is on the login page")
	public void userOnLoginPage() {
		System.out.println("Step 1: User is on login page");		
				
		driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");		
	}
	
	// Scenario 1 specific steps: the user should be able to login with valid credentials

	@When("the user enters valid credentials")
	public void userEntersValidCredentials() {
		System.out.println("Step 2: User enters valid credentials");
		
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys("tim@testemail.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("trpass");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}
	
	@Then("the user should be able to view account balance")
	public void userViewsAccountBalance() {
		System.out.println("Last step: User sees account balance");
		
		String bodyText = driver.findElement(By.xpath("html/body")).getText();
		String welcomeMessage = "Logged in successfully";
		
		Assert.assertTrue(bodyText.contains(welcomeMessage));
	}
	
	// Scenario 2 specific steps: the user should not be able to login with bad credentials
	
	@When("the user enters bad credentials")
	public void userEntersBadCredentials() {
		System.out.println("Step 2: User enters bad credentials");
		
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys("badlogin@testemail.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("badpass");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}
	
	@Then("the user should not be able to login")
	public void userShouldNotViewAccountBalance() {
		System.out.println("Step 3: User should not see account balance");
		
		String bodyText = driver.findElement(By.xpath("html/body")).getText();
		String welcomeMessage = "Logged in successfully";
		
		Assert.assertTrue(!bodyText.contains(welcomeMessage));
	}
	
	@And("the user should get an invalid login message")
	public void userGetInvalidMessage() {
		System.out.println("Step 4: User gets an invalid login message");
		
		String bodyText = driver.findElement(By.xpath("html/body")).getText();
		String invalidUserNameMessage = "Invalid user name, try again!";
		String invalidPasswordMessage = "Invalid password, try again!";
		
		Assert.assertTrue(bodyText.contains(invalidUserNameMessage) || bodyText.contains(invalidPasswordMessage));
	}
	
	// Scenario 3 specific steps: the user should be able to login
	
	@When("^the user enters username as \"(.*)\"$")
	public void enterUserName(String userName) {
		System.out.println("Step 2: User enters user name");
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(userName);
	}
	
	@And("^the user enters password as \"(.*)\"$")
	public void enterPassword(String password) {
		System.out.println("Step 3: User enters password");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
	}
	
	@And("the user clicks on login")
	public void clickLogin() {
		System.out.println("Step 4: User clicks on login button");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}
	
	// Scenario Outline
	
	@When("^the user enters \"(.*)\" and \"(.*)\"$")
	public void userEntersUserNameAndPassword(String userName, String password) {
		System.out.println("Step 2: User enters credentials (" + userName + " ; " + password + ")");
		
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(userName);
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
		
		clickLogin();
	}
	
	// Scenario with local data table
	
	@When("the user enters set of username and password")
	public void userEntersCredentials(DataTable credentials) {
		System.out.println("Step 2: User enters credentials data table");
		
		// Extract data from DataTable to Map and iterate
		for(Map<String, String> data : credentials.asMaps(String.class, String.class)) {
			
			String userName = data.get("username");
			String password = data.get("password");
			System.out.println(userName + " | " + password);
			
			driver.findElement(By.id("MainContent_txtUserName")).clear();
			driver.findElement(By.id("MainContent_txtUserName")).sendKeys(userName);
			driver.findElement(By.id("MainContent_txtPassword")).clear();
			driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
			clickLogin();
			userOnLoginPage();
		}
			
	}
	
}
