package haniehsadatiacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
   WebDriver driver;
   
   // constructor
   public CheckoutPage(WebDriver driver)
   {
	   super(driver);   // from child we send variable driver to the parent class AbstractComponent. this initializing parent constructor class
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
   
   
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   @FindBy(css=".action__submit")
   WebElement submit;
  
   
   @FindBy(css="[placeholder='Select Country']")
   WebElement country;
   
   @FindBy(css=".ta-item:nth-of-type(2)")
   WebElement selectcountry;  
   
   
   
   By results = By.cssSelector(".ta-results");
   
   
   public void selectCountry(String countryName)
   {
       // select a country which is mandatory. There are lots of ways but here we use Actions class
       Actions a = new Actions(driver);
       a.sendKeys(country,countryName).build().perform();
       // it takes few seconds to display the list of countries that match the text we enter, find element that shows all menu options
       // we wait until it is visible
       ///////wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
       waitForElementToAppear(By.cssSelector(".ta-results"));
       // now the options are displayed then select india which is second option in the list
       //driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
       selectcountry.click();
   }

   public ConfirmationPage submitOrder()
   {
       // click on place order button
	   submit.click();
	   // create an object of type class conformationPage and return it
	   return new ConfirmationPage(driver);
   }

}
