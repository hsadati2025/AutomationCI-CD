package haniehsadatiacademy.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
   WebDriver driver;
   WebDriverWait wait;
   
   // constructor
   public LandingPage(WebDriver driver)
   {
	   super(driver);   // from child we send variable driver to the parent class AbstractComponent. this initializing parent constructor class
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
	   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
   }
	
   
   /* instead of wringg these code we can use pageFactory to have a simpler code
   WebElement userEmail = driver.findElement(By.id("userEmail"));
   WebElement userPassword = driver.findElement(By.id("userPassword"));
   WebElement login = driver.findElement(By.id("login"));
   */
   
   
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   @FindBy(id="userEmail")
   WebElement userEmail;
   
   @FindBy(id="userPassword")
   WebElement userPassword;
   
   @FindBy(id="login")
   WebElement submit;
   
   @FindBy(css="[class*='flyInOut']")
   WebElement errorMessage;
   
   public ProductCatalog  loginApplication(String email, String password)
   {
	   userEmail.sendKeys(email);
	   userPassword.sendKeys(password);
	   submit.click();
	   ProductCatalog productCatalogue = new ProductCatalog(driver);
	   return productCatalogue;
   }
   
   public void goTo()
   {
	   driver.get("https://rahulshettyacademy.com/client");
   }
   
   public String getErrorMessage()
   {
	   waitForWebElementToAppear(errorMessage);
	   return errorMessage.getText();
   }
   
}
