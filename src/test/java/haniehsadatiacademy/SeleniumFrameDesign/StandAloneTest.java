package haniehsadatiacademy.SeleniumFrameDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// by this code chrome driver will be automatically downloaded into your system based on your chrome driver version
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.get("https://rahulshettyacademy.com/client");
        
        
        driver.findElement(By.id("userEmail")).sendKeys("sara@yahoo.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test1234");
        driver.findElement(By.id("login")).click();
        
		// use explicit wait for this element to load. 
	    // wait for 5 seconds until the message is visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
		// wait until products are visible on page
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	 	
        // get list of all products (common elements all product have) 
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        
        // use javastream
        // "stream" allows you to iterate through each product in the list
        // when first product retrived, we apply "filter" to check one condition here
        // findFirst()    in our test we have just one product with name "ZARA COAT 3", retun the first one
        //"orElse(null)"   if you did not find any product with name "ZARA COAT 3" return null
        WebElement prod = products.stream().filter(product->
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        
        // this is the cssSelector  for all "Add to Card" buttons:        .card-body button:last-of-type
        // it will click on  "Add to Card" button that is belong to product name "ZARA COAT 3"
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        
        // after product got added to card we have to wait until loading icon disapears before we verify successful message
        

		// wait until message gets visible
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	 	
	 	
	 	
	 	
		 // wait until loading icon gets disapear
	 	// way1: it is slow    we use way2 code
	 	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	 	// way2 code
	 	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	 	
	 	
	 	// click on card icon
	 	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        
        
	 	// inside the card details get the title of all products to see if a product with name "ZARA COAT 3" exists
	 	 // get list of all product name elements 
        List<WebElement> cardProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cardProducts.stream().anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));
	
        // if procust name exist in the card then test passes
        Assert.assertTrue(match);
        
        // click on check out button
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        // select a country which is mandatory. There are lots of ways but here we use Actions class
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        // it takes few seconds to display the list of countries that match the text we enter, find element that shows all menu options
        // we wait until it is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        // now the options are displayed then select india which is second option in the list
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        // click on place order button
        driver.findElement(By.cssSelector(".action__submit")).click();
        // after submit we get thank you page
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();

        
        
        
	}

}
