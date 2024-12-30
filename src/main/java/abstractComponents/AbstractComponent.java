package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import haniehsadatiacademy.pageObjects.CartPage;
import haniehsadatiacademy.pageObjects.OrdersPage;

public class AbstractComponent {
	// this is a parent class to all of the PageObject classes since it will hold all reusable codes
	WebDriver driver;
	 
	
	  @FindBy(css="[routerlink*='myorders']")
	  WebElement ordersHeader;   
	  
	  @FindBy(css="[routerlink*='cart']")
	  WebElement CartHeader;   
	   
	// constructor class
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy)
	{
		// use explicit wait for this element to load. 
	    // wait for 5 seconds until the message is visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    
		// wait until products are visible on page
	 	//convert it as below//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		// use explicit wait for this element to load. 
	    // wait for 5 seconds until the message is visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    
		// wait for web element to appear
	    wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(2000);
		// use explicit wait for this element to load. 
	    // wait for 5 seconds until the message is visible
	    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		CartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage()
	{
		ordersHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

}
