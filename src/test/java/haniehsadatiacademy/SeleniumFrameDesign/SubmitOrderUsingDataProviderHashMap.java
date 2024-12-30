package haniehsadatiacademy.SeleniumFrameDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import haniehsadatiacademy.pageObjects.CartPage;
import haniehsadatiacademy.pageObjects.CheckoutPage;
import haniehsadatiacademy.pageObjects.ConfirmationPage;
import haniehsadatiacademy.pageObjects.OrdersPage;
import haniehsadatiacademy.pageObjects.ProductCatalog;
import testComponents.BaseTest;

public class SubmitOrderUsingDataProviderHashMap extends BaseTest{
	String productName2 = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"purchase3"})
	public void SubmitOrder(HashMap<String,String> input ) throws IOException, InterruptedException {

		String email = input.get("email");
		String password = input.get("password");
        String productName=input.get("productName");
        
        
        ProductCatalog productCatalog = landingPage.loginApplication(email,password);

        System.out.println("hhhhhhhhhhhhhhhhhhhh     "+productName);
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();


	 	// inside the card details get the title of all products to see if a product with passed name exists
	 	 // get list of all product name elements 
        Boolean match = cartPage.verifyProductDisplay(productName);

        // if product name exist in the card then test passes
        Assert.assertTrue(match);
        
        // click on check out button
        CheckoutPage checkoutPage =  cartPage.goToCheckout();
        
        
       
        checkoutPage.selectCountry("india");
        
        // after submit we get thank you page
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
		landingPage.loginApplication("sara@yahoo.com","Test1234");
	    OrdersPage orderspage = landingPage.goToOrdersPage();
	    orderspage.verifyOrderDisplay(productName2);
	}
	
	
	
	// we want to run this test case with 2 different data sets and we read data from Json file --> SubmitOrder
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		String dataFilePath = System.getProperty("user.dir") +"\\src\\test\\java\\data\\PurchaseOrder.json";
		// it returns the list of HashMaps
		List<HashMap<String,String>> data = getJsonDataToHashMap(dataFilePath);
		
		return new Object[][]  {{data.get(0)},{data.get(1)}};
		
	}
}
