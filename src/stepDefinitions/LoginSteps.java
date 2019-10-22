package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		System.out.println("Step 3: User sees account balance");
		
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
	
}
