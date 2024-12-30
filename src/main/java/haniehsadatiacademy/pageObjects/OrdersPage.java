package haniehsadatiacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
   WebDriver driver;
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   @FindBy(css="tr td:nth-child(3)")
   private List<WebElement> productNames;
   
   
   // constructor
   public OrdersPage(WebDriver driver)
   {
	   super(driver);   // from child we send variable driver to the parent class AbstractComponent. this initializing parent constructor class
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
   

   public boolean verifyOrderDisplay(String productName)
   {
	   Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	   return match;
   }

  

}
