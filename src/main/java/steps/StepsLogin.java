package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsLogin {
	WebDriver driver;	
	@Given ("I am on login page")
	public void I_am_on_login_page() throws InterruptedException 
	{
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@When ("I enter username and password into input fields")
    public void I_enter_username_and_password_into_input_fields()
    {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
    }
	
	 @When ("I click on login button")
	 public void I_click_on_login_button()
	 {		  
			driver.findElement(By.id("btnLogin")).click();
	 }
	 
	 @Then ("I verify user login successfully")
	 public void I_verify_user_login_successfully()
	 {
		 String message= driver.findElement(By.cssSelector(".orangehrm-dashboard-widget-icon")).getText();
		 //String message= driver.findElement(By.id("welcome")).getText();
		 //Assert.assertTrue(message.equalsIgnoreCase("Dashboard")); 
	 }
}
