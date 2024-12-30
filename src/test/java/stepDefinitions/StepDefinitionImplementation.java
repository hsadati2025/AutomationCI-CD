package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import haniehsadatiacademy.pageObjects.CartPage;
import haniehsadatiacademy.pageObjects.CheckoutPage;
import haniehsadatiacademy.pageObjects.ConfirmationPage;
import haniehsadatiacademy.pageObjects.LandingPage;
import haniehsadatiacademy.pageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page()  throws IOException
	{
		landingPage = launchApplication();
	}
	
	@When("Logged in with username {string} and password {string}")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username,password);	
    }
	
	@When ("I add product {string} to Cart")
	 public void I_add_productName_to_cart(String productName) throws InterruptedException
	 {		  
			List<WebElement> products = productCatalog.getProductList();
			productCatalog.addProductToCart(productName);
	 }
	 
	 @When ("Checkout {string} and submit the order")
	 public void checkout_submit_order(String productName)
	 {
		 CartPage cartPage = productCatalog.goToCartPage();
	     Boolean match = cartPage.verifyProductDisplay(productName);
	     Assert.assertTrue(match);
	     // click on check out button
	     CheckoutPage checkoutPage = cartPage.goToCheckout();
	     checkoutPage.selectCountry("india");
	     confirmationPage =  checkoutPage.submitOrder();
	 }
	 
	 @Then ("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
	     Assert.assertTrue(confirmMessage.equalsIgnoreCase(string)); 
	     driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 public void something_message_is_displayed(String strArg1) throws Throwable {
	   Assert.assertEquals(strArg1, landingPage.getErrorMessage());
	   driver.close();
	 }	
}
