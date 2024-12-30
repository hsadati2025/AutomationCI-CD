package haniehsadatiacademy.SeleniumFrameDesign;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import haniehsadatiacademy.pageObjects.CartPage;
import haniehsadatiacademy.pageObjects.CheckoutPage;
import haniehsadatiacademy.pageObjects.ConfirmationPage;
import haniehsadatiacademy.pageObjects.OrdersPage;
import haniehsadatiacademy.pageObjects.ProductCatalog;
import testComponents.BaseTest;

public class SubmitOrderTestCase extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(groups= {"purchase"})
	public void SubmitOrder() throws IOException, InterruptedException {

		
        // in BaseTest class we defined public variable Landigpage and we used @beforeMethod to  land on the page automatically
		// therefore below code is not needed anymore
		///LandingPage landingPage = launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication("sara@yahoo.com","Test1234");
        

       // ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        
   



        
        
	 	// inside the card details get the title of all products to see if a product with name "ZARA COAT 3" exists
	 	 // get list of all product name elements 
        //CartPage cartPage= new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay(productName);

        // if procust name exist in the card then test passes
        Assert.assertTrue(match);
        
        // click on check out button
        CheckoutPage checkoutPage =  cartPage.goToCheckout();
        
        
        //CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("india");
        //1//checkoutPage.submitOrder();
        
 
        // after submit we get thank you page
        //2//ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        
        // instead of 1 & 2  ---> an object of type ConfirmationPage will be created inside method submitOrder()
        // this help to avoid create all object of type different classes
        ConfirmationPage confirmationPage =  checkoutPage.submitOrder();
       
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));   
	}

	
	// this test must be executed after SubmitOrderTestCase is executed
	// there is a dependency between this test and SubmitOrderTestCase test
	// we submit order for product "ZARA COAT 3" and then in order history page we verify that product with the same name exists
	@Test(dependsOnMethods= {"SubmitOrder"})   // it will make sure method SubmitOrder is ran before running OrderHistoryTest method
	public void OrderHistoryTest()
	{
		//login to application
		ProductCatalog productCatalog = landingPage.loginApplication("sara@yahoo.com","Test1234");
	    OrdersPage orderspage = productCatalog.goToOrdersPage();
	    orderspage.verifyOrderDisplay(productName);
	}
}
