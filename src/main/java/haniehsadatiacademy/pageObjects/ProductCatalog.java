package haniehsadatiacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
   WebDriver driver;
   
   // constructor
   public ProductCatalog(WebDriver driver)
   {
	   super(driver);
	   // initializing driver
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
	
   
   
   //PageFactory - it is a design pattern, using that you can reduce the syntax of creating web element
   //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
   @FindBy(css=".mb-3")
   List<WebElement> products;
   
   @FindBy(css=".ng-animating")
   WebElement spinner;
   
   @FindBy(css="[routerlink*='cart']")
   WebElement AddToCartButton;   
   
   
   
   By ProductsBy = By.cssSelector(".mb-3");
   By addToCartBy = By.cssSelector(".card-body button:last-of-type");
   By toastMessageBy = By.id("toast-container");
   
   public List<WebElement> getProductList()
   {
	   waitForElementToAppear(ProductsBy);
	   return products;
   }
   
   
   
   
   public WebElement getProductByName(String productName)
   {
       // use javastream
       // "stream" allows you to iterate through each product in the list
       // when first product retrived, we apply "filter" to check one condition here
       // findFirst()    in our test we have just one product with name "ZARA COAT 3", retun the first one
       //"orElse(null)"   if you did not find any product with name "ZARA COAT 3" return null
       ///////WebElement prod = products.stream().filter(product->
       ///////product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	   WebElement prod = getProductList().stream().filter(product->
	   product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	   
	   return prod;
   }
  
   
   public void addProductToCart(String productName) throws InterruptedException
   {
	     // this is the cssSelector  for all "Add to Card" buttons:        .card-body button:last-of-type
       // it will click on  "Add to Card" button that is belong to product name "ZARA COAT 3"
       ///////////prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	   WebElement prod = getProductByName(productName);
	   prod.findElement(addToCartBy).click();
	   
	   
       // after product got added to card we have to wait until loading icon disapears before we verify successful message
       // wait until message gets visible
	 	/////wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	   waitForElementToAppear(toastMessageBy);
	   //wait until spinner disappears
	   //////wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	   waitForElementToDisappear(spinner);
	   
	 	// click on cart icon
	 	/////driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	   AddToCartButton.click();
   }
  
}
