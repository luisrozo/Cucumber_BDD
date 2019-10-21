package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", 
				"/home/luis.gonzaga/Documentos/cursos/cursoCucumber/JARs/chromedriver");
		
		driver = new ChromeDriver();		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Given("the user is on the login page")
	public void userOnLoginPage() {
		System.out.println("Step 1: User is on login page");		
				
		driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");		
	}
	

	@When("the user enters valid credentials")
	public void userEntersValidCredentials() {
		System.out.println("Step 2: User enters valid credentials");
		
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys("tim@testemail.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("trpass");
		driver.findElement(By.id("MainContent_btnLogin")).click();
	}
	
	@Then("the user should be able to view account balance")
	public void userViewsAccountBalance() {
		System.out.println("Step 3: User sees balance account");
		
		String bodyText = driver.findElement(By.xpath("html/body")).getText();
		String welcomeMessage = "Logged in successfully";
		
		Assert.assertTrue(bodyText.contains(welcomeMessage));
	}
	
}
