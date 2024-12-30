package haniehsadatiacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
   WebDriver driver;
   
   // constructor
   public CartPage(WebDriver driver)
   {
	   super(driver);   // from child we send variable driver to the parent class AbstractComponent. this initializing parent constructor class
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
   
   
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   @FindBy(css=".cartSection h3")
   private List<WebElement> productTitles;
   
   @FindBy(css=".totalRow button")
   WebElement checkoutEle;
   

   
   public boolean verifyProductDisplay(String productName)
   {
	   Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	   return match;
   }

   public CheckoutPage goToCheckout()
   {
	   checkoutEle.click();
	   return new CheckoutPage(driver);
   }

}
