package haniehsadatiacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
   WebDriver driver;
   
   // constructor
   public ConfirmationPage(WebDriver driver)
   {
	   super(driver);   // from child we send variable driver to the parent class AbstractComponent. this initializing parent constructor class
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
  
   
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   @FindBy(css=".hero-primary")
   WebElement confirmationMessage;
   
  
   
   public String getConfirmationMessage()
   {
	  CheckoutPage cp = new CheckoutPage(driver);	
	  return confirmationMessage.getText();
   }
   
  
   
}
